package com.management.project.controllers;

import com.management.project.dao.CompanyDAO;
import com.management.project.dao.CustomerDAO;
import com.management.project.dao.GenericDAO;
import com.management.project.models.Company;
import com.management.project.models.Customer;
import com.management.project.models.Project;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Катя on 19.03.2017.
 */
public class ProjectControllerTest extends AbstractModelControllerTest {

    GenericDAO projectDAO = mock(GenericDAO.class);
    CompanyDAO companyDAO = mock(CompanyDAO.class);
    CustomerDAO customerDAO  = mock(CustomerDAO.class);
    ProjectController projectController = new ProjectController(projectDAO, companyDAO, customerDAO);

    @Test
    public void printMenu() throws SQLException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        projectController.printMenu();
        assertTrue(byteArrayOutputStream.toString().contains("ACTIONS WITH PROJECTS:"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }


    @Test
    public void getNewModel(){
        Company company = new Company(1, "test company");
        Customer customer = new Customer(1, "test customer");
        Project ourProject = new Project(1, "1", 1, company, customer);
        when(companyDAO.findById(1l)).thenReturn(company);
        when(customerDAO.findById(1l)).thenReturn(customer);
        System.setIn(new Always1InputStream());
        Project projectFromGetNewProject = projectController.getNewModel();
        assertEquals(projectFromGetNewProject, ourProject);
    }
}
