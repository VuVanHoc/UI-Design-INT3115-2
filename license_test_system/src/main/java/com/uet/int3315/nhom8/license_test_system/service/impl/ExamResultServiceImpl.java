package com.uet.int3315.nhom8.license_test_system.service.impl;

import com.uet.int3315.nhom8.license_test_system.error.ExamResultNotFoundException;
import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamResultDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamResultFullDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.Course;
import com.uet.int3315.nhom8.license_test_system.model.entity.ExamResult;
import com.uet.int3315.nhom8.license_test_system.model.entity.User;
import com.uet.int3315.nhom8.license_test_system.repository.CourseRepository;
import com.uet.int3315.nhom8.license_test_system.repository.ExamResultRepository;
import com.uet.int3315.nhom8.license_test_system.repository.UserRepository;
import com.uet.int3315.nhom8.license_test_system.service.CourseService;
import com.uet.int3315.nhom8.license_test_system.service.ExamResultService;
import com.uet.int3315.nhom8.license_test_system.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamResultServiceImpl implements ExamResultService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    private ExamResultRepository examResultRepository;

    public ExamResultServiceImpl(ExamResultRepository examResultRepository){
        this.examResultRepository = examResultRepository;
    }

    @Override
    public RestBody saveExamResult(ExamResultDTO examResultDTO) {
        ExamResult examResult = new ExamResult();
        BeanUtils.copyProperties(examResultDTO, examResult);
        examResultRepository.save(examResult);
        return RestBody.success(examResultDTO);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public RestBody getExamResult(Integer userId, Integer courseId) {
        List<ExamResultDTO> responeDTO = new ArrayList<>();
        if(userId == null){ //Kết quả thi tất cả user của 1 khóa học
            List<ExamResult> resultByCourse = examResultRepository.findByCourseId(courseId);
            if(resultByCourse == null){
                throw new ExamResultNotFoundException("Không tìm thấy kết quả");
            }
            resultByCourse.forEach(r -> {
               ExamResultDTO newDto = new ExamResultDTO();
               BeanUtils.copyProperties(r, newDto);
               responeDTO.add(newDto);
           });
        }else if(courseId == null){ //Kết quả thi của user trong Các khóa học
            List<ExamResult> resultByUser = examResultRepository.findByUserId(userId);
            if(resultByUser == null){
                throw new ExamResultNotFoundException("Không tìm thấy kết quả");
            }
            resultByUser.forEach(r -> {
                ExamResultDTO newDto = new ExamResultDTO();
                BeanUtils.copyProperties(r, newDto);
                responeDTO.add(newDto);
            });

        }else { //Kết quả thi của user trong Một khóa học
            List<ExamResult> result = examResultRepository.findByUserIdAndCourseId(userId, courseId);
            if(result == null){
                throw new ExamResultNotFoundException("Không tìm thấy kết quả");
            }
            result.forEach(r -> {
                ExamResultDTO newDto = new ExamResultDTO();
                BeanUtils.copyProperties(r, newDto);
                responeDTO.add(newDto);
            });
        }

        return RestBody.success(responeDTO);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public RestBody getAllResults() {
        List<ExamResultDTO> responeDTO = new ArrayList<>();
        List<ExamResult> resultByCourse = examResultRepository.findAllByDeletedIsFalse();
        if(resultByCourse == null){
            throw new ExamResultNotFoundException("Không tìm thấy kết quả");
        }
        resultByCourse.forEach(r -> {
            ExamResultDTO newDto = new ExamResultDTO();
            BeanUtils.copyProperties(r, newDto);
            responeDTO.add(newDto);
        });
        return RestBody.success(responeDTO);
    }

    @Override
    public RestBody getFullResults() {
        List<ExamResultFullDTO> responeDTO = new ArrayList<>();
        List<ExamResult> resultByCourse = examResultRepository.findAllByDeletedIsFalse();
        if(resultByCourse == null){
            throw new ExamResultNotFoundException("Không tìm thấy kết quả");
        }
        resultByCourse.forEach(r -> {
            ExamResultFullDTO newDto = new ExamResultFullDTO();
            User user = userRepository.getOne(r.getUserId());
            Course course = courseRepository.getOne(r.getCourseId());
            newDto.setFullName(user.getFullName());
            newDto.setUserName(user.getUsername());
            newDto.setCoursename(course.getCourseName());
            newDto.setScore(r.getScore());
            newDto.setStatus(r.isStatus() ? "Đạt" : "Không đạt");
            responeDTO.add(newDto);
        });
        return RestBody.success(responeDTO);
    }

    @Override
    public RestBody getTotalRecord() {
        return RestBody.success(examResultRepository.countAllByDeletedIsFalse());
    }

    @Override
    public RestBody getPass() {
        return  RestBody.success(examResultRepository.countAllByStatusIsTrue());
    }
	
	@Override
	public RestBody countPoint() {
    	List<Integer> points = new ArrayList<>();
    	
		for(int i=15; i<=30; i++){
			points.add(examResultRepository.countByScoreIs(i));
		}
		
		return RestBody.success(points);
	}
}
