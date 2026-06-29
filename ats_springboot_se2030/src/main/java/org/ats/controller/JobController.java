package org.ats.controller;

import lombok.RequiredArgsConstructor;
import org.ats.dto.JobRequest;
import org.ats.entities.Department;
import org.ats.entities.Job;
import org.ats.entities.Skill;
import org.ats.services.DepartmentService;
import org.ats.services.JobService;
import org.ats.services.SkillService;
import org.springframework.data.domain.Page;
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
        mv.setViewName("views/jobs/job_detail");
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
        return "views/jobs/job_detail";
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
    public String search(
            @RequestParam(name = "pageIndex",defaultValue = "0", required = false) Integer pageIndex,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(name = "keyword", required = false) String keyword,
                         Model model) {

        Page<Job> page = jobService.search(keyword, pageIndex, pageSize);
        model.addAttribute("jobs", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        return "views/jobs/recruiter_manage_jobs";
    }

    @GetMapping("/browse")
    public String browse(@RequestParam(name = "pageIndex",defaultValue = "0", required = false) Integer pageIndex,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(name = "keyword", required = false) String keyword,
                         Model model) {
        Page<Job> page = jobService.search(keyword, pageIndex, pageSize);

        model.addAttribute("jobs", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("currentPage", page.getNumber() + 1);
//        model.addAttribute("departments", departmentService.findAll());
        return "views/publics/browse_job";
    }

    @DeleteMapping
    public String deleteJob(@RequestParam(name = "id") Long id, Model model) {
        jobService.delete(id);

        model.addAttribute("message", "Delete job successful!");

        return "redirect:/jobs/search";
    }
}
