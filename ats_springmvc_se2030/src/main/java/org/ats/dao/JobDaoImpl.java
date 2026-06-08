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
        Session session = sessionFactory.openSession();
        session.persist(job);
        return job;
    }

    @Override
    public boolean isExisted(String title) {
        Session session = sessionFactory.openSession();
        TypedQuery<Long> query = session.createQuery("SELECT COUNT(j) FROM Job j " +
                "WHERE j.title = :param", Long.class);
        query.setParameter("param", title);

        Long result = query.getSingleResult();
        return result > 0;

    }

    @Override
    public List<Job> search(String keyword) {
        Session session = sessionFactory.openSession();

        Query<Job> query = session.createQuery("FROM Job j WHERE j.title LIKE :param OR j.description LIKE :param", Job.class);
        query.setParameter("param", keyword);

        return query.getResultList();
    }

    @Override
    public List<Job> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM Job").getResultList();
    }
}
