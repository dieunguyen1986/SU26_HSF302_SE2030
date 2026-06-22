package org.ats.dao;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.ats.entities.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class JobDaoImpl implements JobDao {

    private final SessionFactory sessionFactory;

    @Override
    public Job createJob(Job job) {

        // Try - resources
        Session session = sessionFactory.getCurrentSession();
        session.persist(job);
        return job;
    }

    @Override
    public boolean isExisted(String title) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Long> query = session.createQuery("SELECT COUNT(j) FROM Job j " +
                "WHERE j.title = :param", Long.class);
        query.setParameter("param", title);

        Long result = query.getSingleResult();
        return result > 0;

    }


    @Override
    public List<Job> search(String keyword) {
        Session session = sessionFactory.getCurrentSession();

        Query<Job> query = session.createQuery("FROM Job j WHERE j.title LIKE :param OR j.description LIKE :param", Job.class);
        query.setParameter("param", keyword);

        return query.getResultList();
    }

    @Override
    public List<Job> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Job", Job.class).getResultList();
    }

    @Override
    public Job findById(Long id) {
        return sessionFactory.getCurrentSession().get(Job.class, id);
    }

    @Override
    public Job updateJob(Job job) {
        Session session = sessionFactory.getCurrentSession();

        Job existedJob = session.get(Job.class, job.getId());

        if (existedJob != null) {
            return session.merge(existedJob);
        } else {
            throw new RuntimeException("Job not found");
        }
    }

    @Override
    public void delete(Long jobId) {
        Session session = sessionFactory.getCurrentSession();

        Job job = session.get(Job.class, jobId);

        if (job != null) {
            session.remove(job);
        }

    }
}
