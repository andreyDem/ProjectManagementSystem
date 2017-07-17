package com.management.project.controllers;

import com.management.project.dao.CompanyDAO;
import com.management.project.dao.GenericDAO;
import com.management.project.dao.ProjectDAO;
import com.management.project.dao.SkillDAO;
import com.management.project.models.*;
import org.junit.Test;

import java.io.*;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Вадим on 21.03.2017.
 */
public class DeveloperControllerTest extends AbstractModelControllerTest {

    GenericDAO dao = mock(GenericDAO.class);
    SkillDAO skillDAO = mock(SkillDAO.class);
    CompanyDAO companyDAO = mock(CompanyDAO.class);
    ProjectDAO projectDAO = mock(ProjectDAO.class);
    DeveloperController developerController = new DeveloperController(dao,skillDAO,companyDAO,projectDAO);

    @Test
    public void printMenu() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        developerController.printMenu();
        assertTrue(byteArrayOutputStream.toString().contains("ACTIONS WITH DEVELOPERS:"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void getNewModel() throws Exception {
        Skill skill = new Skill(1, "test skill");
        Company company = new Company(1, "test company");
        Project project = new Project(1, "test project", 11111, company, null);
        Developer ourDeveloper = new Developer(1, "1", company, project, 1);
        when(skillDAO.findById(1l)).thenReturn(skill).thenReturn(null);
        when(companyDAO.findById(1l)).thenReturn(company);
        when(projectDAO.findById(1l)).thenReturn(project);
        System.setIn(new Always1InputStream());
        Developer developerFromGetNewModel = developerController.getNewModel();
        assertEquals(developerFromGetNewModel, ourDeveloper);
        Set<Skill> skills = developerFromGetNewModel.getSkills();
        assertTrue((skills.size() == 1) && skills.contains(skill));
        Developer anotherDeveloper = developerController.getNewModel();
        assertTrue(anotherDeveloper.getSkills().size() == 0);
    }
}