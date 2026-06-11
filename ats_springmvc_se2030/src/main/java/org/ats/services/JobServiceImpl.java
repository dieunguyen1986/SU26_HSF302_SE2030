package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.JobDao;
import org.ats.dto.JobRequest;
import org.ats.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobDao jobDao;  //= new JobDaoImpl(); // IS-A - Down casting

    @Override
    public Job createJob(JobRequest jobRequest) {

        // Check existing by title
        // Throw

        if (jobDao.isExisted(jobRequest.getTitle())) {
            // Un-checked
            throw new RuntimeException("Job with title " + jobRequest.getTitle() + " already exists.");
        }

        if (jobRequest.getMinSalary().compareTo(jobRequest.getMaxSalary()) > 0) {
            throw new RuntimeException("Salary exceeds maximum of salary minimum.");
        }
        // Convert DTO to Entity
        Job job = toEntity(jobRequest);
        job.setStatus(JobStatus.DRAFT.toString());


        // Call DAO
        return jobDao.createJob(job);

    }

    @Override
    public Job updateJob(Job job) {
        return null;
    }

    @Override
    public List<Job> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Job> search(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return jobDao.findAll();
        }

        return jobDao.search("%" + keyword + "%");
    }

    @Override
    public Job findById(Long id) {
        Job job = jobDao.findById(id);
        if (job == null) {
            throw  new RuntimeException("Job with id " + id + " not found.");
        }
        return job;
    }


    public Job toEntity(JobRequest req) {
        Job job = Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .location(req.getLocation())
                .salaryMin(req.getMinSalary())   // minSalary -> salaryMin
                .salaryMax(req.getMaxSalary())   // maxSalary -> salaryMax
//                .deadline(req.getDeadline())
                .build();

        // id: String -> Long (chỉ set khi update)
        if (req.getId() != null) {
            job.setId(Long.valueOf(req.getId()));
        }

        // departmentId -> Department (load qua repository hoặc tạo reference)
        if (req.getDepartmentId() != null) {
            Department dept = new Department();
            dept.setId(req.getDepartmentId());   // hoặc departmentRepository.getReferenceById(...)
            job.setDepartment(dept);
        }

        // skills (List<Long>) -> Set<JobSkill>
        if (req.getSkills() != null) {
            for (Long skillId : req.getSkills()) {
                Skill skill = new Skill();
                skill.setId(skillId);

                JobSkill jobSkill = new JobSkill();
                jobSkill.setJob(job);
                jobSkill.setSkill(skill);

                job.getJobSkills().add(jobSkill);
            }
        }

        return job;
    }

}
