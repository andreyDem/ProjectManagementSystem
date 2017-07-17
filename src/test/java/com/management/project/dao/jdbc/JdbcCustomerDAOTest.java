package com.management.project.dao.jdbc;

import com.management.project.dao.CustomerDAO;
import com.management.project.factory.FactoryDao;
import org.junit.Test;
import com.management.project.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Olenka on 3/9/2017.
 */
public class JdbcCustomerDAOTest {

    private CustomerDAO jdbcCustomerDAO = FactoryDao.getCustomerDAO();

    public JdbcCustomerDAOTest() throws SQLException {
    }

    @Test
    public void save() throws Exception {
        Customer testCustomer = new Customer();
        testCustomer.setName("testCustomer");
        Long testID = jdbcCustomerDAO.save(testCustomer);
        assertNotNull(testID);
        assertEquals(jdbcCustomerDAO.findByName("testCustomer") , testCustomer);
        jdbcCustomerDAO.delete(jdbcCustomerDAO.findById(testID));
    }

    @Test
    public void findById() throws Exception {
        Customer testCustomer = new Customer();
        testCustomer.setId(2);
        testCustomer.setName("Abbott Laboratories");
        assertEquals(jdbcCustomerDAO.findById((long)2), testCustomer);
    }

    @Test
    public void findByIdNull() throws Exception{
        assertTrue(jdbcCustomerDAO.findById((long)-500).getName() == "");
    }

     @Test
     public void update() throws Exception{
        Customer testCustomer = new Customer();
        testCustomer.setName("TestCustomer");
        Long id = jdbcCustomerDAO.save(testCustomer);
        testCustomer.setName("TestCustomer2");
        testCustomer.setId(id);
        jdbcCustomerDAO.update(testCustomer);
        assertEquals(jdbcCustomerDAO.findById(id),testCustomer);
        jdbcCustomerDAO.delete(jdbcCustomerDAO.findById(id));
    }

    @Test
    public void delete() throws Exception{
        Customer testCustomer = new Customer();
        testCustomer.setName("TestCustomer");
        Long id = jdbcCustomerDAO.save(testCustomer);
        testCustomer.setId(id);
        jdbcCustomerDAO.delete(testCustomer);
        assertFalse(jdbcCustomerDAO.findAll().contains(testCustomer));
    }

    @Test
    public void findAll() {
        Customer testCustomer = new Customer();
        Customer testCustomer2 = new Customer();
        testCustomer.setName("Customer1");
        testCustomer2.setName("Customer2");
        Long testID = jdbcCustomerDAO.save(testCustomer);
        Long testID2 = jdbcCustomerDAO.save(testCustomer2);
        testCustomer.setId(testID);
        testCustomer2.setId(testID2);
        List<Customer> testList;
        testList = jdbcCustomerDAO.findAll();
        assertTrue(testList.contains(testCustomer) && testList.contains(testCustomer2));
        jdbcCustomerDAO.delete(jdbcCustomerDAO.findById(testID));
        jdbcCustomerDAO.delete(jdbcCustomerDAO.findById(testID2));
    }

    @Test
    public void findByName() throws Exception{
        Customer testCustomer = new Customer();
        testCustomer.setId(2);
        testCustomer.setName("Abbott Laboratories");
        assertEquals(jdbcCustomerDAO.findByName("Abbott Laboratories"), testCustomer);
    }
}
