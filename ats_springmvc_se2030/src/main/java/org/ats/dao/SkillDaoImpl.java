package org.ats.dao;

import lombok.RequiredArgsConstructor;
import org.ats.entities.Skill;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillDaoImpl implements  SkillDao {
    private final SessionFactory sessionFactory;

    @Override
    public List<Skill> findAll() {
        return sessionFactory.openSession().createQuery("FROM Skill", Skill.class).list();
    }
}
