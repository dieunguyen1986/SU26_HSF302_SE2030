package org.ats.dao;

import org.ats.entities.Skill;

import java.util.List;

public interface SkillDao {
    List<Skill> findAll();
}
