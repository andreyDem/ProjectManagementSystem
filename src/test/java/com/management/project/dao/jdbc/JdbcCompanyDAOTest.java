package com.management.project.dao.jdbc;

import com.management.project.dao.CompanyDAO;
import com.management.project.factory.FactoryDao;
import com.management.project.models.Company;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author Slava Makhinich
 */
public class JdbcCompanyDAOTest {

    private CompanyDAO companyDAO;
    private Company company;

    public JdbcCompanyDAOTest() throws SQLException {
        companyDAO = FactoryDao.getCompanyDAO();
    }

    @Test
    public void findByName() throws Exception {
        company = new Company(-1, "CompanyForTestFindByName");
        companyDAO.save(company);
        assertEquals(company, companyDAO.findByName("CompanyForTestFindByName"));
        companyDAO.delete(companyDAO.findByName("CompanyForTestFindByName"));
        assertTrue(companyDAO.findByName("CompanyForTestFindByName").getId() == 0);
    }

    @Test
    public void save() throws Exception {
        company = new Company(-1, "CompanyForTestSave");
        long id = companyDAO.save(company);
        assertTrue(0 < id);
        assertEquals(company, companyDAO.findById(id));
        companyDAO.delete(companyDAO.findById(id));
    }

    @Test
    public void findById() throws Exception {
        company = new Company(-1, "CompanyForTestFindById");
        long id = companyDAO.save(company);
        assertEquals(company, companyDAO.findById(id));
        companyDAO.delete(companyDAO.findById(id));
        assertTrue(companyDAO.findById(id).getName() == "");
    }

    @Test
    public void update() throws Exception {
        company = new Company(-1, "CompanyForTesUpdate");
        long id = companyDAO.save(company);
        Company newCompany = new Company(id, "NewCompanyForTestUpdate");
        companyDAO.update(newCompany);
        assertEquals(newCompany, companyDAO.findById(id));
        companyDAO.delete(companyDAO.findById(id));
    }

    @Test
    public void delete() throws Exception {
        company = new Company(-1, "CompanyForTesDelete");
        long id = companyDAO.save(company);
        assertNotNull(companyDAO.findById(id));
        companyDAO.delete(companyDAO.findById(id));
        assertTrue(companyDAO.findById(id).getName() == "");
    }

    @Test
    public void findAll() throws Exception {
        long listSize = companyDAO.findAll().size();
        long id1 = companyDAO.save(new Company(-1, "1 CompanyForTestFindAll"));
        long id2 = companyDAO.save(new Company(-1, "2 CompanyForTestFindAll"));
        assertTrue(companyDAO.findAll().size() - listSize == 2);
        companyDAO.delete(companyDAO.findById(id1));
        companyDAO.delete(companyDAO.findById(id2));
        assertEquals(listSize, companyDAO.findAll().size());
    }

}