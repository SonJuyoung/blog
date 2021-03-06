package com.jy.blog.repository;

import com.jy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//DAO
//자동으로 bean등록이 된다.
//@Repository //생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from user where username = 1?;
    Optional<User> findByUsername(String username); //<-네이밍 쿼리
}

//jpa naming 쿼리
//select * from user where username = ?1 and password = ?2;
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "select * from user where username = ?1 and password = ?2", nativeQuery = true)
//    User login(String username, String password);