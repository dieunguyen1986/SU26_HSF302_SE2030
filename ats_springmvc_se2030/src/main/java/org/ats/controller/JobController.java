package org.ats.controller;

import org.ats.entities.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @RequestMapping(method = {RequestMethod.POST})
    public String createJob(){
        return null;
    }

    @GetMapping("/search")
    public List<Job> search(){

        return null;
    }

    @GetMapping
    public List<Job> findAll(Model model){
        return null;
    }
}
