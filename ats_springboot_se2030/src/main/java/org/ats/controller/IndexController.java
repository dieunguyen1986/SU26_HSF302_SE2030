package org.ats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(){

        // Logic OK


        // View
        return "login"; // prefix + index + suffix --> /WEB-INF/index.jsp
    }

}
