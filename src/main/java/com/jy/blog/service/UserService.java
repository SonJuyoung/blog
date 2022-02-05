package com.jy.blog.service;

import com.jy.blog.model.RoleType;
import com.jy.blog.model.User;
import com.jy.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록, ioc
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public int join(User user) {
        try {
            String rawPassword = user.getPassword(); //원래 비번
            String encPassword = encoder.encode(rawPassword); //비번 hash화
            user.setPassword(encPassword);
            user.setRole(RoleType.USER);
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } return 0;
    }

//    @Transactional(readOnly = true)//select할 때 트랜잭션 시작, 서브시ㅡ 종료시에 트랜잭션 종료(정합성 유지)
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
