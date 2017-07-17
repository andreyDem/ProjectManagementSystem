package com.management.project.controllers;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Вадим on 22.03.2017.
 */
public class MainControllerTest extends AbstractControllerTest {

    SkillController skillController = mock(SkillController.class);
    CompanyController companyController = mock(CompanyController.class);
    ProjectController projectController = mock(ProjectController.class);
    DeveloperController developerController = mock(DeveloperController.class);
    CustomerController customerController = mock(CustomerController.class);

    MainController controller = new MainController(companyController, customerController,
            developerController, projectController, skillController);


    @Test
    public void action() throws Exception {
        controller.action(1);
        verify(companyController).start();
        controller.action(2);
        verify(customerController).start();
        controller.action(3);
        verify(developerController).start();
        controller.action(4);
        verify(projectController).start();
        controller.action(5);
        verify(skillController).start();
    }

    @Test
    public void printMenu() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        controller.printMenu();
        assertTrue(byteArrayOutputStream.toString().contains("MENU")&&byteArrayOutputStream.toString().contains("0- exit"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

}