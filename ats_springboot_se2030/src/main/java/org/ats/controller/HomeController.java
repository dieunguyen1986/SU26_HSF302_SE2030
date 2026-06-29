package org.ats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index(){

        // Logic OK


        // View
        return "views/publics/home"; // prefix + index + suffix --> /WEB-INF/index.jsp
    }

}
