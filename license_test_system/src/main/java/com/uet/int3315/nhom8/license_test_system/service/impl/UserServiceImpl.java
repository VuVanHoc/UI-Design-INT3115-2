package com.uet.int3315.nhom8.license_test_system.service.impl;

import com.uet.int3315.nhom8.license_test_system.error.CourseNotFoundException;
import com.uet.int3315.nhom8.license_test_system.error.UserNotFoundException;
import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.TotalDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserDetailDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserFormRegistrationDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.Course;
import com.uet.int3315.nhom8.license_test_system.model.entity.User;
import com.uet.int3315.nhom8.license_test_system.repository.CourseRepository;
import com.uet.int3315.nhom8.license_test_system.repository.UserRepository;
import com.uet.int3315.nhom8.license_test_system.service.UserService;
import com.uet.int3315.nhom8.license_test_system.utils.Constant;
import com.uet.int3315.nhom8.license_test_system.utils.RoleCode;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
	
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
	DozerBeanMapper dozerBeanMapper;
    @Autowired
	CourseRepository courseRepository;
    
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RestBody registerAccount(UserFormRegistrationDTO userFormRegistrationDTO) {

        User existUser = userRepository.findByUsername(userFormRegistrationDTO.getUsername());
        if (existUser != null) {
            return RestBody.error("User Already Exists!");
        }

        User newUser = new User();
        dozerBeanMapper.map(userFormRegistrationDTO, newUser);
        newUser.setRoleId(userFormRegistrationDTO.getRoleId());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setEmail(newUser.getUsername());
//        try {
//            newUser.setBirthday(toDate(userFormRegistrationDTO.getBirthday()));
//        } catch (ParseException e) {
//            LOGGER.info(e.getMessage());
//        }

        userRepository.save(newUser);

        return RestBody.success(newUser);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public RestBody getAllUsers(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> userPage = userRepository.findAllByDeletedIsFalse(paging);

        List<User> userList = userPage.getContent();
        List<UserDetailDTO> userDetailDTOS = new ArrayList<>();
        int index = 1;
        for(User user : userList){
			UserDetailDTO userDetailDTO = new UserDetailDTO();
	        dozerBeanMapper.map(user, userDetailDTO);
	        userDetailDTO.setIndex(index);
	        index++;
	        if(user.getRoleId() == RoleCode.STUDENT_ROLE) {
	        	userDetailDTO.setRoleName("Học viên");
	        }
	        else {
	        	userDetailDTO.setRoleName("Quản trị viên");
	        }
	        userDetailDTOS.add(userDetailDTO);
        }
        
        return RestBody.success(userDetailDTOS);
        
    }

    @Override
    public RestBody getUser(Integer id) {
        User user = userRepository.findByIdAndDeletedIsFalse(id);
        if (user == null) {
            return RestBody.error("This account doesn't exist");
        }
        return RestBody.success(user);
    }

    @Override
    public RestBody getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return RestBody.error("This account doesn't exist");
        }
        return RestBody.success(user);
//        return null;
    }

    @Override
    public RestBody deleteUser(Integer id) {
        User deleteUser = userRepository.findByIdAndDeletedIsFalse(id);
        if (deleteUser == null) {
            return RestBody.error("This account doesn't exist");
        }
        deleteUser.setDeleted(true);
        userRepository.save(deleteUser);
        return RestBody.success(null);
    }

    @Override
    public RestBody updateInfoUser(UserDetailDTO userDetailDTO, Integer id) {

        User updateUser = userRepository.findByIdAndDeletedIsFalse(id);
        if (updateUser == null) {
            return RestBody.error("This account doesn't exist");
        }

        updateUser.setFullName(userDetailDTO.getFullName());
        updateUser.setRoleId(userDetailDTO.getRoleId());
//        try {
//            updateUser.setBirthday(toDate(userDetailDTO.getBirthday()));
//        } catch (ParseException e) {
//            LOGGER.info(e.getMessage());
//        }
        updateUser.setEmail(userDetailDTO.getEmail());
        updateUser.setPhone(userDetailDTO.getPhone());
//        updateUser.setPicture(userDetailDTO.getPicture());
        updateUser.setUpdatedDate(new Date());

        userRepository.save(updateUser);

        return RestBody.success(updateUser);
    }

    @Override
    public RestBody getCourses(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        Set<Course> courses =  user.getCourses();
        if(courses.isEmpty()){
            throw new CourseNotFoundException("Không có khóa học nào");
        }
        return RestBody.success(courses);
    }

    private Date toDate(String timeString) throws ParseException {
        Date date = new Date(Long.parseLong(timeString) * 1000);
        DateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_PATTERN);
        String dateString = formatter.format(date);
        return formatter.parse(dateString);
    }
    
    @Override
    public RestBody calculateTotal(){
	    TotalDTO totalDTO = new TotalDTO();
	    List<User> totalStudents = userRepository.findAllByRoleIdAndDeletedIsFalse(RoleCode.STUDENT_ROLE);
	    totalDTO.setTotalStudent(totalStudents.size());
	    
	    List<User> totalAdmin = userRepository.findAllByRoleIdAndDeletedIsFalse(RoleCode.ADMIN_ROLE);
	    totalDTO.setTotalAdmin(totalAdmin.size());
	    totalDTO.setTotalOnline(totalAdmin.size() + totalStudents.size());
	    
	    Integer courseList = courseRepository.countAllByDeletedIsFalse();
	    
	    totalDTO.setTotalCourse(courseList);
	    return RestBody.success(totalDTO);
    }
}

