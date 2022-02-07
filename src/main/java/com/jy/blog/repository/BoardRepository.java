package com.jy.blog.repository;

import com.jy.blog.model.Board;
import com.jy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
