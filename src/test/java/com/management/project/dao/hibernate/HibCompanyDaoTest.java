package com.management.project.dao.hibernate;

import com.management.project.factory.HibFactoryDao;
import com.management.project.models.Company;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Slava Makhinich
 */
public class HibCompanyDaoTest {

    private HibCompanyDao hibCompanyDao = (HibCompanyDao) HibFactoryDao.getCompanyDAO();

    @Test
    public void save() throws Exception {
        Company company = new Company(-1, "companyForTestSave");
        hibCompanyDao.save(company);
        assertEquals(hibCompanyDao.findByName("companyForTestSave"), company);
        hibCompanyDao.delete(hibCompanyDao.findByName("companyForTestSave"));
    }

    @Test
    public void findById() throws Exception {
        Company company = new Company(-1, "companyForTestFindById");
        Long id = hibCompanyDao.save(company);
        assertEquals(hibCompanyDao.findById(id), company);
        company.setId(id);
        hibCompanyDao.delete(company);
        assertEquals(new Company(id, ""), hibCompanyDao.findById(id));
    }

    @Test
    public void update() throws Exception {
        Company company = new Company(-1, "TestUpdate");
        Long id = hibCompanyDao.save(company);
        Company newCompany = new Company(id, "NewTestUpdate");
        hibCompanyDao.update(newCompany);
        assertEquals(new Company(0, "TestUpdate"), hibCompanyDao.findByName("TestUpdate"));
        assertEquals(newCompany, hibCompanyDao.findByName("NewTestUpdate"));
        hibCompanyDao.delete(hibCompanyDao.findByName("NewTestUpdate"));
        Company anotherCompany = new Company(id + 100, "anotherCompany");
        hibCompanyDao.update(anotherCompany);
        assertEquals(new Company(0, "anotherCompany"), hibCompanyDao.findByName("anotherCompany"));
    }

    @Test
    public void delete() throws Exception {
        Company company = new Company(-1, "for test delete");
        hibCompanyDao.delete(company);
        hibCompanyDao.save(company);
        assertEquals(company, hibCompanyDao.findByName("for test delete"));
        hibCompanyDao.delete(hibCompanyDao.findByName("for test delete"));
        assertEquals(new Company(0, "for test delete"), hibCompanyDao.findByName("for test delete"));
    }

    @Test
    public void findAll() throws Exception {
        long listSizeBefore = hibCompanyDao.findAll().size();
        Company company1 = new Company(-1,"company1 for test findAll");
        Company company2 = new Company(-1,"company2 for test findAll");
        hibCompanyDao.save(company1);
        hibCompanyDao.save(company2);
        List<Company> listAfter = hibCompanyDao.findAll();
        assertTrue((listAfter.size() - listSizeBefore) == 2);
        assertTrue(listAfter.contains(company1) && listAfter.contains(company2));
        hibCompanyDao.delete(hibCompanyDao.findByName("company1 for test findAll"));
        hibCompanyDao.delete(hibCompanyDao.findByName("company2 for test findAll"));
    }

    @Test
    public void findByName() throws Exception {
        Company company = new Company(-1, "findByNameTest");
        hibCompanyDao.save(company);
        assertEquals(company, hibCompanyDao.findByName("findByNameTest"));
        hibCompanyDao.delete(hibCompanyDao.findByName("findByNameTest"));
        assertEquals(new Company(0, "findByNameTest") , hibCompanyDao.findByName("findByNameTest"));
    }

}