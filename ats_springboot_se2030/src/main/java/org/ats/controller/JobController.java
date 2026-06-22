package org.ats.controller;

import lombok.RequiredArgsConstructor;
import org.ats.dto.JobRequest;
import org.ats.entities.Department;
import org.ats.entities.Job;
import org.ats.entities.Skill;
import org.ats.services.DepartmentService;
import org.ats.services.JobService;
import org.ats.services.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final DepartmentService departmentService;
    private final SkillService skillService;

    @RequestMapping(path = "/add", method = {RequestMethod.GET})
    public ModelAndView showCreateJob() {
        // Get Department
        List<Department> departments = departmentService.findAll();
        // Get Skill
        List<Skill> skills = skillService.findAll();

        // Add to model
        ModelAndView mv = new ModelAndView();
        mv.setViewName("job_detail");
        mv.addObject("departments", departments);
        mv.addObject("skills", skills);
        mv.addObject("job", new JobRequest());

        return mv;
    }

    @GetMapping("/{id}")
    public String getJobById(@PathVariable(name = "id") Long id, Model model) {
        JobRequest job = jobService.findById(id);

        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("skills", skillService.findAll());

        model.addAttribute("job", job);
        return "job_detail";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("job") JobRequest jobRequest, Model model) {

        if (jobRequest.getId() == null) {
            jobService.createJob(jobRequest);
            model.addAttribute("message", "Create job successful!");
        } else {
            jobService.updateJob(jobRequest);
            model.addAttribute("message", "Update job successful!");
        }

        return "redirect:/jobs/search";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "keyword", required = false) String keyword,
                         Model model) {

        List<Job> jobs = jobService.search(keyword);
        model.addAttribute("jobs", jobs);
        return "recruiter_manage_jobs";
    }

    @DeleteMapping
    public String deleteJob(@RequestParam(name = "id") Long id, Model model) {
        jobService.delete(id);

        model.addAttribute("message", "Delete job successful!");

        return "redirect:/jobs/search";
    }
}
