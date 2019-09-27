package com.octopus.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class OctopusHandController {
    @Autowired
    private HomeClient homeClient;

    public String findAllUsers(){
        return homeClient.findAllUser();
    }
    public String findById(String id){
        return homeClient.findById(id);
    }

}