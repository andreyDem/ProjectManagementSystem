package com.management.project.dao.hibernate;

import com.management.project.factory.HibFactoryDao;
import com.management.project.models.Customer;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * @author Вадим
 */
public class HibCustomerDaoTest {

    private HibCustomerDao hibCustomerDao = (HibCustomerDao) HibFactoryDao.getCustomerDAO();

    @Test
    public void save() throws Exception {
        Customer customer = new Customer("customerTest");
        hibCustomerDao.save(customer);
        assertEquals(hibCustomerDao.findByName("customerTest"), customer);
        hibCustomerDao.delete(hibCustomerDao.findByName("customerTest"));
    }

    @Test
    public void findById() throws Exception {
        Customer customer = new Customer("customerTest");
        Long id = hibCustomerDao.save(customer);
        assertEquals(hibCustomerDao.findById(id), customer);
        hibCustomerDao.delete(hibCustomerDao.findById(id));
        assertEquals(new Customer(id, ""), hibCustomerDao.findById(id));
    }

    @Test
    public void update() throws Exception {
        Customer customer = new Customer("customerTest");
        Long id = hibCustomerDao.save(customer);
        Customer customerUpdate = new Customer(id, "customerUpdate");
        hibCustomerDao.update(customerUpdate);
        assertEquals(new Customer(0, "customerTest"), hibCustomerDao.findByName("customerTest"));
        assertEquals(customerUpdate, hibCustomerDao.findByName("customerUpdate"));
        hibCustomerDao.delete(hibCustomerDao.findByName("customerUpdate"));
    }

    @Test
    public void delete() throws Exception {
        Customer customer = new Customer("customerTest");
        Long id = hibCustomerDao.save(customer);
        assertEquals(customer, hibCustomerDao.findById(id));
        hibCustomerDao.delete(hibCustomerDao.findById(id));
        assertEquals(new Customer(0, ""), hibCustomerDao.findById(id));
    }

    @Test
    public void findAll() throws Exception {
        Customer customerTest1 = new Customer();
        customerTest1.setName("Test1");
        Customer customerTest2 = new Customer();
        customerTest2.setName("Test2");
        Long id1 = hibCustomerDao.save(customerTest1);
        Long id2 = hibCustomerDao.save(customerTest2);
        List<Customer> customerList1 = hibCustomerDao.findAll();
        assertFalse(customerList1.isEmpty());
        assertNotNull(customerList1);
        assertTrue(customerList1.contains(customerTest1)&&customerList1.contains(customerTest2));
        hibCustomerDao.delete(hibCustomerDao.findById(id1));
        List<Customer> customerList2 = hibCustomerDao.findAll();
        hibCustomerDao.delete(hibCustomerDao.findById(id2));
        assertNotEquals(customerList1,customerList2);
    }

    @Test
    public void findByName() throws Exception {
        Customer customer = new Customer("customerTest");
        hibCustomerDao.save(customer);
        assertEquals(customer, hibCustomerDao.findByName("customerTest"));
        hibCustomerDao.delete(hibCustomerDao.findByName("customerTest"));
        assertEquals(new Customer("customerTest"), hibCustomerDao.findByName("customerTest"));
    }
}