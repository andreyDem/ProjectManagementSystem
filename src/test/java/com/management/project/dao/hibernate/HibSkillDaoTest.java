package com.management.project.dao.hibernate;

import com.management.project.factory.HibFactoryDao;
import com.management.project.models.Skill;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Slava Makhinich
 */
public class HibSkillDaoTest {

    private HibSkillDao hibSkillDao = (HibSkillDao) HibFactoryDao.getSkillDAO();

    @Test
    public void save() throws Exception {
        Skill skill = new Skill("for test save()");
        hibSkillDao.save(skill);
        assertEquals(skill, hibSkillDao.findByName("for test save()"));
        hibSkillDao.delete(hibSkillDao.findByName("for test save()"));
        assertTrue(hibSkillDao.findByName("for test save()").getId() == 0);
    }

    @Test
    public void findById() throws Exception {
        Skill skill = new Skill("for test findById()");
        long id = hibSkillDao.save(skill);
        assertEquals(skill, hibSkillDao.findById(id));
        hibSkillDao.delete(hibSkillDao.findById(id));
        assertTrue(hibSkillDao.findById(id).getName() == "");
    }

    @Test
    public void update() throws Exception {
        long id = hibSkillDao.save(new Skill("for test update()"));
        Skill skill = new Skill(id, "after update");
        hibSkillDao.update(skill);
        assertEquals(skill, hibSkillDao.findById(id));
        hibSkillDao.delete(hibSkillDao.findById(id));
        Skill skillNotExist = new Skill(id, "skillNotExist");
        hibSkillDao.update(skillNotExist);
    }

    @Test
    public void delete() throws Exception {
        Skill skill = new Skill("for test delete()");
        long id = hibSkillDao.save(skill);
        assertEquals(hibSkillDao.findById(id), skill);
        hibSkillDao.delete(hibSkillDao.findById(id));
        assertTrue(hibSkillDao.findById(id).getName() == "");
        Skill skillNotExist = new Skill(id, "skillNotExist");
        hibSkillDao.delete(skillNotExist);
    }

    @Test
    public void findAll() throws Exception {
        List<Skill> skillsBefore = hibSkillDao.findAll();
        Skill skill = new Skill("for test findAll()");
        long id = hibSkillDao.save(skill);
        List<Skill> skillsAfter = hibSkillDao.findAll();
        assertTrue(skillsAfter.size() - skillsBefore.size() == 1);
        assertTrue(skillsAfter.contains(skill));
        hibSkillDao.delete(hibSkillDao.findById(id));
    }

    @Test
    public void findByName() throws Exception {
        Skill skill = new Skill("for test findByName()");
        hibSkillDao.save(skill);
        assertEquals(skill, hibSkillDao.findByName("for test findByName()"));
        hibSkillDao.delete(hibSkillDao.findByName("for test findByName()"));
        assertTrue(hibSkillDao.findByName("for test findByName()").getId() == 0);
    }

}