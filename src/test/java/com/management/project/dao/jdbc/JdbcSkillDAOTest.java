package com.management.project.dao.jdbc;

import com.management.project.dao.SkillDAO;
import com.management.project.factory.FactoryDao;
import com.management.project.models.Skill;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 08.03.2017.
 */
public class JdbcSkillDAOTest {

    SkillDAO jdbcSkillDAO = FactoryDao.getSkillDAO();

    public JdbcSkillDAOTest() throws SQLException {
    }


    @Test
    public void save() throws Exception {
        Skill skill = new Skill();
        skill.setName("Test1");
        Long testid = jdbcSkillDAO.save(skill);
        assertNotNull(testid);
        Skill skill1 = jdbcSkillDAO.findById(testid);
        assertEquals(skill,skill1);
    }

    @Test
    public void findById() throws Exception {
        Skill skillTest = new Skill();
        skillTest.setName("Test2");
        Long id = jdbcSkillDAO.save(skillTest);
        assertEquals(skillTest,jdbcSkillDAO.findById(id));
        assertTrue(jdbcSkillDAO.findById(-1L).getName() == "");
    }

    @Test
    public void update() throws Exception {
        Skill skillTest1 = new Skill();
        skillTest1.setName("Test");
        Long id = jdbcSkillDAO.save(skillTest1);
        Skill skillTest2 = new Skill(id,"Test3");
        jdbcSkillDAO.update(skillTest2);
        assertNotEquals(skillTest1,jdbcSkillDAO.findById(id));
    }

    @Test
    public void delete() throws Exception {
        Skill skillTest = new Skill();
        skillTest.setName("Test4");
        Long id  = jdbcSkillDAO.save(skillTest);
        assertNotNull(jdbcSkillDAO.findById(id));
        jdbcSkillDAO.delete(jdbcSkillDAO.findById(id));
        assertTrue(jdbcSkillDAO.findById(id).getName() == "");
    }

    @Test
    public void findAll() throws Exception {
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        skill1.setName("Test5");
        skill2.setName("Test6");
        Long id1 = jdbcSkillDAO.save(skill1);
        Long id2 = jdbcSkillDAO.save(skill2);
        List<Skill> skillTest = new ArrayList<>();
        skillTest.add(jdbcSkillDAO.findById(id1));
        skillTest.add(jdbcSkillDAO.findById(id2));
        List<Skill> skillList1 = jdbcSkillDAO.findAll();
        assertTrue(skillList1.containsAll(skillTest));
        jdbcSkillDAO.delete(jdbcSkillDAO.findById(id1));
        List<Skill> skillList2 = jdbcSkillDAO.findAll();
        assertNotEquals(skillList1,skillList2);
    }

    @Test
    public void findByName() throws Exception {
        Skill skill1 = new Skill();
        skill1.setName("Test7");
        Long id1 = jdbcSkillDAO.save(skill1);
        Skill skill2 = jdbcSkillDAO.findByName("Test7");
        Skill skill3 = jdbcSkillDAO.findByName("EDR");
        assertTrue(skill3.getId() == 0);
        assertEquals(jdbcSkillDAO.findById(id1),skill2);
    }

}