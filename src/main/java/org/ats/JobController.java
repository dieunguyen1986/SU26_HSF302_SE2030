package org.ats;

import org.ats.config.AppConfig;
import org.ats.entities.Department;
import org.ats.entities.Job;
import org.ats.services.JobService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JobController {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        JobService jobService = context.getBean("jobService", JobService.class);

        // JOb
        Department department = new Department();
        department.setId(5L);

        Job job = Job.builder()
                .title("Fresher Java")
                .description("We are seeking a Fresher Java Developer ")
                .deadline(LocalDateTime.now().plusDays(20))
                .salaryMin(new BigDecimal(1000))
                .salaryMax(new BigDecimal(8000))
                .department(department)
                .location("Da Nang, Vietnam")
                .build();

        Job result = jobService.createJob(job);

        System.out.println(result);
    }
}
