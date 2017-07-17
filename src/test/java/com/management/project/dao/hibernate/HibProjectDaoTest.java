package com.management.project.dao.hibernate;

import com.management.project.factory.HibFactoryDao;
import com.management.project.models.Company;
import com.management.project.models.Customer;
import com.management.project.models.Project;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Slava Makhinich
 */
public class HibProjectDaoTest {

    private static HibCompanyDao hibCompanyDao = (HibCompanyDao) HibFactoryDao.getCompanyDAO();
    private static HibProjectDao hibProjectDao = (HibProjectDao) HibFactoryDao.getProjectDAO();
    private static HibCustomerDao hibCustomerDao = (HibCustomerDao) HibFactoryDao.getCustomerDAO();
    private static Company company;
    private static Customer customer;
    private static Long customerId;
    private static Long companyId;

    @BeforeClass
    public static void createData () {
        customer = new Customer("TestCustomer");
        customerId = hibCustomerDao.save(customer);
        customer.setId(customerId);
        company = new Company(-1,"TestCompany");
        companyId = hibCompanyDao.save(company);
        company.setId(companyId);
    }

    @AfterClass
    public static void cleanBase () {
        hibCustomerDao.delete(customer);
        hibCompanyDao.delete(company);
    }

    @Test
    public void save() throws Exception {
        Project project = new Project(-1, "for test save()", 11111, company, customer);
        hibProjectDao.save(project);
        assertEquals(project, hibProjectDao.findByName("for test save()"));
        hibProjectDao.delete(hibProjectDao.findByName("for test save()"));
        assertTrue(hibProjectDao.findByName("for test save()").getId() == 0);
    }

    @Test
    public void findById() throws Exception {
        Project project = new Project(-1, "for test findById()", 11111, company, customer);
        long id = hibProjectDao.save(project);
        assertEquals(project, hibProjectDao.findById(id));
        hibProjectDao.delete(hibProjectDao.findById(id));
        assertTrue(hibProjectDao.findById(id).getName() == "");
    }

    @Test
    public void update() throws Exception {
        Project project = new Project(-1, "for test update()", 11111, company, customer);
        long id = hibProjectDao.save(project);
        Project project1 = new Project(id, "new name for test update()", 5555, company, customer);
        hibProjectDao.update(project1);
        assertEquals(project1, hibProjectDao.findById(id));
        hibProjectDao.delete(hibProjectDao.findById(id));
        hibProjectDao.update(project1);
        assertTrue(hibProjectDao.findByName("new name for test update()").getId() == 0);
    }

    @Test
    public void delete() throws Exception {
        Project project = new Project(-1, "for test delete()", 11111, company, customer);
        hibProjectDao.delete(project);
        long id = hibProjectDao.save(project);
        assertNotNull(hibProjectDao.findById(id));
        hibProjectDao.delete(hibProjectDao.findById(id));
        assertTrue(hibProjectDao.findById(id).getName() == "");
    }

    @Test
    public void findAll() throws Exception {
        List<Project> projectsBefore = hibProjectDao.findAll();
        Project project = new Project(-1, "for test findAll()", 11111, company, customer);
        long id = hibProjectDao.save(project);
        List<Project> projectsAfter = hibProjectDao.findAll();
        assertTrue(projectsAfter.size() - projectsBefore.size() == 1);
        assertTrue(projectsAfter.contains(project));
        hibProjectDao.delete(hibProjectDao.findById(id));
    }

    @Test
    public void findByName() throws Exception {
        Project project = new Project(-1, "for test findByName()", 11111, company, customer);
        hibProjectDao.save(project);
        assertEquals(project, hibProjectDao.findByName("for test findByName()"));
        hibProjectDao.delete(hibProjectDao.findByName("for test findByName()"));
        assertTrue(hibProjectDao.findByName("for test findByName()").getId() == 0);
    }

}