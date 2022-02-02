package com.jy.blog.controller.api;

import com.jy.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/api")
public class UserApiController {

    @PostMapping("/user")
    public int save(@RequestBody User user) {
        System.out.println("save 호출됨");
        return 1;
    }
}
