package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.models.Customer;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Slava Makhinich
 */
public class CustomerControllerTest extends AbstractModelControllerTest {

    GenericDAO dao = mock(GenericDAO.class);
    CustomerController customerController = new CustomerController(dao);

    @Test
    public void getNewModel() throws Exception {
        System.setIn(new ByteArrayInputStream("new test model".getBytes()));
        Customer customer = customerController.getNewModel();
        assertEquals(customer, new Customer("new test model"));
    }

    @Test
    public void printMenu() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        customerController.printMenu();
        assertTrue(byteArrayOutputStream.toString().contains("ACTIONS WITH CUSTOMERS:"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

}