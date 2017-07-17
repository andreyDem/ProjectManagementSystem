package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.dao.SkillDAO;
import com.management.project.models.Skill;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Катя on 19.03.2017.
 */
public class SkillControllerTest extends AbstractModelControllerTest {
    GenericDAO dao = mock(GenericDAO.class);
    SkillDAO skillDAO = mock(SkillDAO.class);

    SkillController skillController = new SkillController(skillDAO);
    @Test
    public void printMenu() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        skillController.printMenu();
        assertTrue(byteArrayOutputStream.toString().contains("ACTIONS WITH SKILLS:"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void getNewModel() throws Exception {
        Skill skill = new Skill(-100, "1");
        when(skillDAO.findById(1l)).thenReturn(skill).thenReturn(null);
        System.setIn(new Always1InputStream());
        Skill skillFromGetNewModel = skillController.getNewModel();
        assertEquals(skillFromGetNewModel, skill);
    }
}
