package org.ats.services;

import org.ats.dao.JobDao;
import org.ats.dao.JobDaoImpl;
import org.ats.entities.Job;
import org.ats.entities.JobStatus;

import java.util.List;

public class JobServiceImpl implements JobService {

    private JobDao jobDao;  //= new JobDaoImpl(); // IS-A - Down casting

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

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
        return List.of();
    }
}
