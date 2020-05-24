package com.uet.int3315.nhom8.license_test_system.service.impl;

import com.uet.int3315.nhom8.license_test_system.error.ExamNotFoundException;
import com.uet.int3315.nhom8.license_test_system.error.ExamScheduleExistException;
import com.uet.int3315.nhom8.license_test_system.error.ExamScheduleNotFoundException;
import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamScheduleDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.ExamSchedule;
import com.uet.int3315.nhom8.license_test_system.repository.ExamScheduleRepository;
import com.uet.int3315.nhom8.license_test_system.service.ExamScheduleService;
import com.uet.int3315.nhom8.license_test_system.utils.Constant;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExamScheduleServiceImpl implements ExamScheduleService {
    private ExamScheduleRepository examScheduleRepository;

    public ExamScheduleServiceImpl(ExamScheduleRepository examScheduleRepository) {
        this.examScheduleRepository = examScheduleRepository;
    }

    @Override
    public RestBody createExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            examSchedule = new ExamSchedule();
            //Create new exam schedule
            examSchedule.setCourseId(courseId);
            try {
                examSchedule.setStartTime(convertJsonTimestampToDate(examScheduleDTO.getStartTime()));
                examSchedule.setEndTime(convertJsonTimestampToDate(examScheduleDTO.getEndTime()));
            } catch (ParseException e) {
                return RestBody.error(e.getMessage());
            }
            examSchedule.setNote(examScheduleDTO.getNote());
            examScheduleRepository.save(examSchedule);
        } else {
            throw new ExamScheduleExistException("Đã tồm tại kỳ thi cho khóa học");
        }
        return RestBody.success(examSchedule);
    }

    @Override
    public RestBody updateExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            throw new ExamScheduleNotFoundException("Không tìm thấy kỳ thi");
        } else {
            examSchedule.setCourseId(courseId);
            try {
                examSchedule.setStartTime(convertJsonTimestampToDate(examScheduleDTO.getStartTime()));
                examSchedule.setEndTime(convertJsonTimestampToDate(examScheduleDTO.getEndTime()));
            } catch (ParseException e) {
                return RestBody.error(e.getMessage());
            }
            examSchedule.setNote(examScheduleDTO.getNote());
            examSchedule.setUpdatedDate(new Date());
            examScheduleRepository.save(examSchedule);
        }
        return RestBody.success(examSchedule);
    }

    @Override
    public RestBody getExamSchedule(Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            throw new ExamScheduleNotFoundException("Không tìm thấy kỳ thi");
        }
        return RestBody.success(examSchedule);
    }

    @Override
    public RestBody deleteExamSchedule(Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            throw new ExamScheduleNotFoundException("Không tìm thấy kỳ thi");
        } else {
            examScheduleRepository.delete(examSchedule);
        }
        return RestBody.success("Deleted exam schedule");
    }

    private Date convertJsonTimestampToDate(String jsonTimestamp) throws ParseException {
        Date date = new Date(Long.parseLong(jsonTimestamp) * 1000);
        DateFormat formatter = new SimpleDateFormat(Constant.DATE_TIME_SCHEDULE_FORMAT);
        String dateString = formatter.format(date);
        return formatter.parse(dateString);
    }
}
