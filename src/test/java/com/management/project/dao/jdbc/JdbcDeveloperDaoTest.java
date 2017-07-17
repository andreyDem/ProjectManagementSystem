package com.management.project.dao.jdbc;

import com.management.project.dao.CompanyDAO;
import com.management.project.dao.DeveloperDAO;
import com.management.project.dao.ProjectDAO;
import com.management.project.dao.SkillDAO;
import com.management.project.factory.FactoryDao;
import com.management.project.models.Developer;
import com.management.project.models.Skill;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 18.03.2017.
 */
public class JdbcDeveloperDaoTest {

    private DeveloperDAO developerDao = FactoryDao.getDeveloperDAO();
    private ProjectDAO projectDAO = FactoryDao.getProjectDAO();
    private CompanyDAO companyDAO = FactoryDao.getCompanyDAO();
    private SkillDAO skillDAO = FactoryDao.getSkillDAO();

    public JdbcDeveloperDaoTest() throws SQLException {
    }

    @Test
    public void findByName() throws Exception {
        Developer developerTest = new Developer();
        developerTest.setName("Test");
        developerTest.setCompany(companyDAO.findById(1L));
        developerTest.setProject(projectDAO.findById(1L));
        Long id = developerDao.save(developerTest);
        assertEquals(developerDao.findById(id), developerDao.findByName("Test"));
        assertTrue(developerDao.findByName("not exist").getId() == 0);
        developerDao.delete(developerDao.findByName("Test"));
    }

    @Test
    public void save() throws Exception {
        Developer developerTest = new Developer();
        developerTest.setName("Test");
        developerTest.setCompany(companyDAO.findById(1L));
        developerTest.setProject(projectDAO.findById(1L));
        Set<Skill> skills = new HashSet<>();
        skills.add(skillDAO.findById(1L));
        skills.add(skillDAO.findById(2L));
        developerTest.setSkills((HashSet<Skill>) skills);
        Long id = developerDao.save(developerTest);
        assertNotNull(id);
        assertEquals(developerTest,developerDao.findById(id));
        developerDao.delete(developerDao.findById(id));
    }

    @Test
    public void findById() throws Exception {
        Developer developerTest = new Developer();
        developerTest.setName("Test");
        developerTest.setCompany(companyDAO.findById(1L));
        developerTest.setProject(projectDAO.findById(1L));
        Long id = developerDao.save(developerTest);
        assertNotNull(developerDao.findById(id));
        assertTrue(developerDao.findById(-10L).getName() == "");
        assertEquals(developerTest,developerDao.findById(id));
        developerDao.delete(developerDao.findById(id));
    }

    @Test
    public void update() throws Exception {
        Developer developerTest = new Developer();
        developerTest.setName("Test");
        developerTest.setCompany(companyDAO.findById(1L));
        developerTest.setProject(projectDAO.findById(1L));
        Long id = developerDao.save(developerTest);
        Set<Skill> skills = new HashSet<>();
        skills.add(skillDAO.findById(1L));
        skills.add(skillDAO.findById(2L));
        Developer developerNew = new Developer(id,"Updated",companyDAO.findById(2L),projectDAO.findById(2L),1000,skills);
        assertEquals(developerTest,developerDao.findById(id));
        developerDao.update(developerNew);
        assertEquals(developerNew,developerDao.findByName("Updated"));
        assertNotEquals(developerTest,developerDao.findById(id));
        developerDao.delete(developerDao.findById(id));
    }

    @Test
    public void delete() throws Exception {
        Developer developerTest = new Developer();
        developerTest.setName("Test");
        developerTest.setCompany(companyDAO.findById(1L));
        developerTest.setProject(projectDAO.findById(1L));
        Long id = developerDao.save(developerTest);
        assertNotNull(developerDao.findById(id));
        developerDao.delete(developerDao.findById(id));
        assertTrue(developerDao.findById(id).getName() == "");
    }

    @Test
    public void findAll() throws Exception {
        Developer developerTest1 = new Developer();
        developerTest1.setName("Test1");
        developerTest1.setCompany(companyDAO.findById(1L));
        developerTest1.setProject(projectDAO.findById(1L));
        Developer developerTest2 = new Developer();
        developerTest2.setName("Test2");
        developerTest2.setCompany(companyDAO.findById(2L));
        developerTest2.setProject(projectDAO.findById(2L));
        Long id1 = developerDao.save(developerTest1);
        Long id2 = developerDao.save(developerTest2);
        List<Developer> developers1 = developerDao.findAll();
        assertFalse(developers1.isEmpty());
        assertNotNull(developers1);
        assertTrue(developers1.contains(developerTest1)&&developers1.contains(developerTest2));
        developerDao.delete(developerDao.findById(id1));
        List<Developer> developers2 = developerDao.findAll();
        developerDao.delete(developerDao.findById(id2));
        assertNotEquals(developers1,developers2);
    }

}