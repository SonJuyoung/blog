package com.jy.blog.repository;

import com.jy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO
//자동으로 bean등록이 된다.
//@Repository //생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
