package com.uet.int3315.nhom8.license_test_system.service.impl;


import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserDetailDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserFormRegistrationDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.User;
import com.uet.int3315.nhom8.license_test_system.repository.UserRepository;
import com.uet.int3315.nhom8.license_test_system.service.UserService;
import com.uet.int3315.nhom8.license_test_system.utils.Constant;
import com.uet.int3315.nhom8.license_test_system.utils.RoleCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

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
        BeanUtils.copyProperties(userFormRegistrationDTO, newUser);
        newUser.setRoleId(RoleCode.STUDENT_ROLE);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        try {
            newUser.setBirthday(toDate(userFormRegistrationDTO.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        LOGGER.info(newUser.toString());
        userRepository.save(newUser);

        return RestBody.success(newUser);
    }

    @Override
    public RestBody getAllUsers() {
        List<User> users = userRepository.findAllByDeletedIsFalse();
        return RestBody.success(users);
    }

    @Override
    public RestBody getUser(Integer id) {
        User user = userRepository.findByIdAndDeletedIsFalse(id);
        if(user == null){
            return RestBody.error("This account doesn't exist");
        }
        return RestBody.success(user);
    }

    @Override
    public RestBody deleteUser(Integer id) {
        User deleteUser = userRepository.findByIdAndDeletedIsFalse(id);
        if(deleteUser == null){
            return RestBody.error("This account doesn't exist");
        }
        deleteUser.setDeleted(true);
        userRepository.save(deleteUser);
        return RestBody.success(null);
    }

    @Override
    public RestBody updateInfoUser(UserDetailDTO userDetailDTO, Integer id) {

        User updateUser = userRepository.findByIdAndDeletedIsFalse(id);
        if(updateUser == null){
            return RestBody.error("This account doesn't exist");
        }

        updateUser.setFullName(userDetailDTO.getFullName());
        try {
            updateUser.setBirthday(toDate(userDetailDTO.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        updateUser.setEmail(userDetailDTO.getEmail());
        updateUser.setPhone(userDetailDTO.getPhone());
        updateUser.setPicture(userDetailDTO.getPicture());
        updateUser.setUpdatedDate(new Date());

//        LOGGER.info(updateUser.toString());
        userRepository.save(updateUser);

        return RestBody.success(updateUser);
    }

    private Date toDate(String timeString) throws ParseException {
        Date date = new Date(Long.parseLong(timeString) * 1000);
        DateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_PATTERN);
        String dateString = formatter.format(date);
        return formatter.parse(dateString);
    }
}

