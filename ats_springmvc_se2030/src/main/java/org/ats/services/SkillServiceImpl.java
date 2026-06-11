package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.SkillDao;
import org.ats.entities.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillDao skillDao;

    @Override
    public List<Skill> findAll() {
        return skillDao.findAll();
    }

    @Override
    public Skill findById(int id) {
        return null;
    }
}
