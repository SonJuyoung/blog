package com.jy.blog.service;

import com.jy.blog.model.RoleType;
import com.jy.blog.model.User;
import com.jy.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Transactional(readOnly = true)
    public User searchUser(String username) {

        User user = userRepository.findByUsername(username).orElseGet(()-> {
            return new User();
        });
        return user;
    }

    @Transactional
    public void join(User user) {
            String rawPassword = user.getPassword(); //원래 비번
            String encPassword = encoder.encode(rawPassword); //비번 hash화
            user.setPassword(encPassword);
            user.setRole(RoleType.USER);
            userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        //Select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다
        User persistance = userRepository.findById(user.getId())
                .orElseThrow(()-> {
                    return new IllegalArgumentException("회원 찾기 실패");
                });

        //Validation 체크 => oauth 필드에 값이 없으면 수정 가능
        if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }

        //회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 자동으로 됨
        //영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
    }

//    @Transactional(readOnly = true)//select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
