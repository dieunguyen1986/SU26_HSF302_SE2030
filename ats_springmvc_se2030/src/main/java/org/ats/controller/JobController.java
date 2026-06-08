package org.ats.controller;

import lombok.RequiredArgsConstructor;
import org.ats.entities.Job;
import org.ats.services.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @RequestMapping(method = {RequestMethod.POST})
    public String createJob() {
        return null;
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword,
                            Model model) {

        List<Job> jobs = jobService.search(keyword);
        model.addAttribute("jobs", jobs);
        return "recruiter_manage_jobs";
    }

    @GetMapping
    public List<Job> findAll(Model model) {
        return null;
    }
}
