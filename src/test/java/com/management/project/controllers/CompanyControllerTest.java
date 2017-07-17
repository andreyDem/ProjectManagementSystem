package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.models.Company;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Misha on 17.03.2017.
 */
public class CompanyControllerTest extends AbstractModelControllerTest {

   GenericDAO dao = mock(GenericDAO.class);
   CompanyController controller = new CompanyController(dao);


    @Test
    public void getNewModel(){
       System.setIn(new Always1InputStream());
       Company company = new Company(-100,"1");
       Company companyFromGetNewModel = controller.getNewModel();
       assertEquals(companyFromGetNewModel, company);
    }

    @Test
    public void printMenu() {
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       System.setOut(new PrintStream(byteArrayOutputStream));
       controller.printMenu();
       assertTrue(byteArrayOutputStream.toString().contains("ACTIONS WITH COMPANIES:"));
       System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

}

