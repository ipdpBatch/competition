package com.octopus.eureka.user.controller;

import com.octopus.common.bean.User;
import com.octopus.common.result.Result;
import com.octopus.common.result.ResultFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {


    @PostMapping(value = "/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String username = requestUser.getUsername();
        session.setAttribute("user", requestUser);
        return ResultFactory.buildSuccessResult(requestUser);
    }
}
