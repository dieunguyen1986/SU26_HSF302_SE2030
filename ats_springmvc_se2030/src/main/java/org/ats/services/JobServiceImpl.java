package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.JobDao;
import org.ats.entities.Job;
import org.ats.entities.JobStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobDao jobDao;  //= new JobDaoImpl(); // IS-A - Down casting

    @Override
    public Job createJob(Job job) {

        // Check existing by title
        // Throw

        if (jobDao.isExisted(job.getTitle())) {
            // Un-checked
            throw new RuntimeException("Job with title " + job.getTitle() + " already exists.");
        }

        if (job.getSalaryMin().compareTo(job.getSalaryMax()) > 0) {
            throw new RuntimeException("Salary exceeds maximum of salary minimum.");
        }

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
}
