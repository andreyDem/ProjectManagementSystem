package com.management.project.dao.jdbc;

import com.management.project.dao.CompanyDAO;
import com.management.project.dao.CustomerDAO;
import com.management.project.dao.ProjectDAO;
import com.management.project.factory.FactoryDao;
import com.management.project.models.Company;
import com.management.project.models.Customer;
import com.management.project.models.Project;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Slava Makhinich
 */
public class JdbcProjectDAOTest {

    private ProjectDAO projectDAO;
    private CompanyDAO companyDAO;
    private CustomerDAO customerDAO;
    private Company company;
    private Customer customer;
    private Project project;

    public JdbcProjectDAOTest() throws SQLException {
        projectDAO = FactoryDao.getProjectDAO();
        companyDAO = FactoryDao.getCompanyDAO();
        customerDAO = FactoryDao.getCustomerDAO();
        company = companyDAO.findById(1l);
        customer = customerDAO.findById(1l);
    }

    @Test
    public void findByName() throws Exception {
        project = new Project(-1, "newProjectForTestFindByName", 3333, company, customer);
        projectDAO.save(project);
        Project project1 = projectDAO.findByName("newProjectForTestFindByName");
        assertEquals(project1, project);
        projectDAO.delete(project1);
        assertTrue(projectDAO.findByName("newProjectForTestFindByName").getId() == 0);
    }

    @Test
    public void save() throws Exception {
        project = new Project(-1, "newProjectForTestSave", 3333, company, customer);
        Long id = projectDAO.save(project);
        assertNotNull(id);
        assertEquals(project, projectDAO.findById(id));
        projectDAO.delete(projectDAO.findById(id));
    }

    @Test
    public void findById() throws Exception {
        project = new Project(-1, "newProjectForTestFindById", 3333, company, customer);
        Long id = projectDAO.save(project);
        assertEquals(projectDAO.findById(id), project);
        projectDAO.delete(projectDAO.findById(id));
        assertTrue(projectDAO.findById(id).getName() == "");
    }

    @Test
    public void update() throws Exception {
        project = new Project(-1, "ProjectForTestUpdate", 3333, company, customer);
        Long id = projectDAO.save(project);
        Project project1 = new Project(id, "NEW!!! ProjectForTestUpdate", 7777, company, customer);
        projectDAO.update(project1);
        assertEquals(project1, projectDAO.findById(id));
        projectDAO.delete(project1);
        projectDAO.update(project1);
        assertNotEquals(project1, projectDAO.findById(id));
    }

    @Test
    public void delete() throws Exception {
        project = new Project(-1, "newProjectForTestDelete", 3333, company, customer);
        Long id = projectDAO.save(project);
        assertEquals(project, projectDAO.findById(id));
        projectDAO.delete(projectDAO.findById(id));
        assertNotEquals(project, projectDAO.findById(id));
    }

    @Test
    public void findAll() throws Exception {
        project = new Project(-1, "firstProjectForTestFindAll", 3333, company, customer);
        Project project1 = new Project(-100, "secondProjectForTestFindAll", 555, company, customer);
        long id1 = projectDAO.save(project1);
        long id = projectDAO.save(project);
        List<Project> projectList = projectDAO.findAll();
        assertTrue(projectList.contains(project1) && projectList.contains(project));
        projectDAO.delete(projectDAO.findById(id));
        projectDAO.delete(projectDAO.findById(id1));
    }
}