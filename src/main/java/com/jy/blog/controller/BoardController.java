package com.jy.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index() {
        return "index"; //WEB-INF/views/index.jsp
    }
}
