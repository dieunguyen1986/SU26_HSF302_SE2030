package org.ats.dao;

import com.mss301.fe.edu.vn.entities.Skill;
import org.ats.entities.Department;
import org.ats.entities.Job;
import org.ats.entities.JobSkill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

class JobDaoImplTest {
    private static JobDao jobDao;

    @BeforeAll
    public static void setup() {
        jobDao = new JobDaoImpl();
    }

    @Test
    void createJob() {
        Job job = new Job();

        job.setTitle("Senior Java Backend Developer");
        job.setDescription("""
                We are looking for a Senior Java Backend Developer 
                with strong experience in Spring Boot, Microservices, 
                Docker, Kubernetes, and MySQL.
                
                Responsibilities:
                - Design and develop REST APIs
                - Optimize system performance
                - Collaborate with frontend and DevOps teams
                - Participate in code reviews and architecture discussions
                """);

        job.setLocation("Ho Chi Minh City, Vietnam");

        job.setSalaryMin(new BigDecimal("1500.00"));
        job.setSalaryMax(new BigDecimal("3000.00"));

        job.setStatus("OPEN");

        job.setUtmSource("linkedin");
        job.setUtmMedium("job_post");

        job.setDeadline(LocalDateTime.now().plusDays(30));
        job.setPublishedAt(LocalDateTime.now());

        Department department = new Department();
        department.setId(5L);

        // Set department to job
        job.setDepartment(department);

        // Skill
        Skill skill1 = new Skill();
        skill1.setId(1L);

        Skill skill2 = new Skill();
        skill2.setId(2L);

        // SkillJOb
        JobSkill jobSkill = new JobSkill();
        jobSkill.setJob(job);
        jobSkill.setSkill(skill1);

        JobSkill jobSkill2 = new JobSkill();
        jobSkill2.setJob(job);
        jobSkill2.setSkill(skill2);

        Set<JobSkill> jobSkills = new HashSet<>();
        jobSkills.add(jobSkill);
        jobSkills.add(jobSkill2);

//        skill1.setJobSkills(jobSkills);
//        skill2.setJobSkills(jobSkills);

        job.setJobSkills(jobSkills);

        Job actualResult = jobDao.createJob(job);

        Assertions.assertNotNull(actualResult);

    }


    @Test
    void createJob2() {
        Job job = new Job();

        job.setTitle("Frontend React Developer");

        job.setDescription("""
    We are seeking a Frontend React Developer 
    to build modern web applications using ReactJS, TypeScript, 
    and RESTful APIs.

    Requirements:
    - Experience with ReactJS and Redux
    - Understanding responsive UI/UX
    - Familiar with Git and Agile/Scrum
    - Basic knowledge of backend APIs
    """);

        job.setLocation("Da Nang, Vietnam");

        job.setSalaryMin(new BigDecimal("1000.00"));
        job.setSalaryMax(new BigDecimal("2200.00"));

        job.setStatus("ACTIVE");

        job.setUtmSource("facebook");
        job.setUtmMedium("social_media");

        job.setDeadline(LocalDateTime.now().plusDays(20));
        job.setPublishedAt(LocalDateTime.now().minusDays(2));

        Department department = new Department();
        department.setId(5L);

        // Set department to job
        job.setDepartment(department);

        // Skill
        Skill skill = new Skill();
        skill.setId(1L);

        // SkillJOb
        JobSkill jobSkill = new JobSkill();
        jobSkill.setJob(job);
        jobSkill.setSkill(skill);

        Set<JobSkill> jobSkills = new HashSet<>();
        jobSkills.add(jobSkill);

        job.setJobSkills(jobSkills);

        Job actualResult = jobDao.createJob(job);

        Assertions.assertNotNull(actualResult);

    }
}