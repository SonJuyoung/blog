package com.jy.blog.controller;

import com.jy.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index(/*@AuthenticationPrincipal PrincipalDetail principal*/) { //컨트롤러에서 세션을 어떻게 찾지?
        return "index"; //WEB-INF/views/index.jsp
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
