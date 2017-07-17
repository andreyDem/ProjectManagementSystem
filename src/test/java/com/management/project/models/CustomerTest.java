package com.management.project.models;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
 * Created by Olenka on 3/3/2017.
 */
public class CustomerTest {

    private Customer testCustomer = new Customer(11111, "Pierre Fermat");

    @Test
    public void getId() {
        assertTrue(testCustomer.getId() == 11111);
    }

    @Test
    public void setId() {
        testCustomer.setId(5555);
        assertTrue(testCustomer.getId() == 5555);
    }

    @Test
    public void getName() {
        assertTrue(testCustomer.getName().equals("Pierre Fermat"));
    }

    @Test
    public void setName() {
        testCustomer.setName("Daniel Bernoulli");
        assertTrue(testCustomer.getName().equals("Daniel Bernoulli"));
    }

    @Test
    public void setNullName() {
        testCustomer.setName(null);
        assertNotNull(testCustomer.getName());
    }

    @Test
    public void equals() {
        assertEquals(testCustomer, testCustomer);
        assertFalse(testCustomer.equals(new Integer(1)));
    }

    @Test
    public void testToString() {
        assertTrue(testCustomer.toString().contains("Pierre Fermat"));
        assertTrue(testCustomer.toString().contains("11111"));
    }
}
