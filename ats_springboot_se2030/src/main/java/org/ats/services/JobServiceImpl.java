package org.ats.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ats.dao.JobDao;
import org.ats.dto.JobRequest;
import org.ats.entities.*;
import org.ats.repositoties.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {
    private  final JobRepository jobRepository;

    @Override
    public Job createJob(JobRequest jobRequest) {

        // Check existing by title
        // Throw

        if (jobRepository.existsByTitle(jobRequest.getTitle())) {
            // Un-checked
            throw new RuntimeException("Job with title " + jobRequest.getTitle() + " already exists.");
        }

        if ((jobRequest.getMinSalary() != null && jobRequest.getMaxSalary() != null) && jobRequest.getMinSalary().compareTo(jobRequest.getMaxSalary()) > 0) {
            throw new RuntimeException("Salary exceeds maximum of salary minimum.");
        }
        // Convert DTO to Entity
        Job job = toEntity(jobRequest);
        job.setStatus(JobStatus.DRAFT.toString());


        // Call DAO
        return jobRepository.save(job);

    }

    @Override
    public Job updateJob(JobRequest req) {
        // Load entity đang managed trong transaction hiện tại

        Job job = jobRepository.findById(req.getId()).orElseThrow(()->{
            return new EntityNotFoundException("Job with id " + req.getId() + " not found.");
        });

        // Validate salary (null-safe)
        if (req.getMinSalary() != null && req.getMaxSalary() != null
                && req.getMinSalary().compareTo(req.getMaxSalary()) > 0) {
            throw new RuntimeException("Salary minimum exceeds salary maximum.");
        }

        // Cập nhật các field cơ bản
        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setLocation(req.getLocation());
        job.setSalaryMin(req.getMinSalary());
        job.setSalaryMax(req.getMaxSalary());
        job.setDeadline(req.getDeadline() != null ? req.getDeadline().atStartOfDay() : null);

        // Department
        if (req.getDepartmentId() != null) {
            Department dept = new Department();
            dept.setId(req.getDepartmentId());
            job.setDepartment(dept);
        } else {
            job.setDepartment(null);
        }

        // Skills: xóa hết cũ rồi thêm lại mới (orphanRemoval = true sẽ xóa row cũ)
        job.getJobSkills().clear();
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

        // job đang managed -> Hibernate tự flush thay đổi khi commit (dirty checking)
        return job;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override

    public void delete(Long id) {
        if (id== null) {
            throw new EntityNotFoundException("Job with id " + id + " not found.");
        }
        jobRepository.deleteById(id);
    }

    @Override
    public Page<Job> search(String keyword, Integer pageIndex, Integer pageSize) {
        //
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        if (keyword == null || keyword.isEmpty()) {
            return jobRepository.findByStatus(pageable, "PUBLISHED");
        }

        return jobRepository.findJobByPage(pageable, "%" + keyword + "%");
    }

    @Override
    public JobRequest findById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(()->{
            return new EntityNotFoundException("Job with id " + id+ " not found.");
        });

        JobRequest jobRequest = JobRequest.builder()
                .id(job.getId())
                .title(job.getTitle())
                .minSalary(job.getSalaryMin())
                .maxSalary(job.getSalaryMax())
                .location(job.getLocation())
                .description(job.getDescription())
                .build();
        if (job.getDeadline() != null) {
            jobRequest.setDeadline(job.getDeadline().toLocalDate());
        }

        if (job.getDepartment() != null) {
            jobRequest.setDepartmentId(job.getDepartment().getId());
        }

        // Set<JobSkill> -> List<Long> skillIds (để tick lại đúng checkbox)
        if (job.getJobSkills() != null) {
            List<Long> skillIds = job.getJobSkills().stream()
                    .map(js -> js.getSkill().getId())
                    .toList();
            jobRequest.setSkills(skillIds);
        }

        return jobRequest;
    }


    public Job toEntity(JobRequest req) {
        Job job = Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .location(req.getLocation())
                .salaryMin(req.getMinSalary())   // minSalary -> salaryMin
                .salaryMax(req.getMaxSalary())   // maxSalary -> salaryMax
                .build();

        if (req.getDeadline() != null) {
            job.setDeadline(req.getDeadline().atStartOfDay());
        }
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

