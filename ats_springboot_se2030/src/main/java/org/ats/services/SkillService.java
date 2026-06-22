package org.ats.services;


import org.ats.entities.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();
    Skill findById(int id);
}
