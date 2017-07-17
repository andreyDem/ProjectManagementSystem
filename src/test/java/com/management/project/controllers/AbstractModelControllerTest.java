package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.models.Model;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Slava Makhinich
 */
public class AbstractModelControllerTest extends AbstractControllerTest {

    @Test
    public void showAll () throws Exception {
        GenericDAO dao = mock(GenericDAO.class);
        AbstractModelController abstractModelController = new AbstractModelController(dao) {
            @Override
            protected Model getNewModel() {
                return null;
            }
        };
        abstractModelController.showAll();
        verify(dao).findAll();
    }

    @Test
    public void printMenu() throws Exception {
        AbstractModelController abstractModelController =
                mock(AbstractModelController.class, Mockito.CALLS_REAL_METHODS);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        abstractModelController.printMenu();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String outputString = byteArrayOutputStream.toString();
        assertTrue(outputString.contains("1 - add new"));
        assertTrue(outputString.contains("2 - update"));
        assertTrue(outputString.contains("3 - find by id"));
        assertTrue(outputString.contains("4 - find by name"));
        assertTrue(outputString.contains("5 - delete"));
        assertTrue(outputString.contains("6 - show all"));
        assertTrue(outputString.contains("0 - go to main menu"));
    }

    @Test
    public void action() throws Exception {
        AbstractModelController abstractModelController =
                mock(AbstractModelController.class, Mockito.CALLS_REAL_METHODS);
        doNothing().when(abstractModelController).addNew();
        doNothing().when(abstractModelController).update();
        doNothing().when(abstractModelController).findByIdAndOutput();
        doNothing().when(abstractModelController).findByNameAndOutput();
        doNothing().when(abstractModelController).deleteById();
        doNothing().when(abstractModelController).showAll();
        abstractModelController.action(0);
        abstractModelController.action(7);
        abstractModelController.action(-1);
        verify(abstractModelController, never()).addNew();
        verify(abstractModelController, never()).update();
        verify(abstractModelController, never()).findByIdAndOutput();
        verify(abstractModelController, never()).findByNameAndOutput();
        verify(abstractModelController, never()).deleteById();
        verify(abstractModelController, never()).showAll();
        abstractModelController.action(1);
        verify(abstractModelController).addNew();
        abstractModelController.action(2);
        verify(abstractModelController).update();
        abstractModelController.action(3);
        verify(abstractModelController).findByIdAndOutput();
        abstractModelController.action(4);
        verify(abstractModelController).findByNameAndOutput();
        abstractModelController.action(5);
        verify(abstractModelController).deleteById();
        abstractModelController.action(6);
        verify(abstractModelController).showAll();
    }

    @Test
    public void addNew() throws Exception {
        GenericDAO dao = mock(GenericDAO.class);
        Model model = mock(Model.class);
        AbstractModelController abstractModelController = new AbstractModelController(dao) {
            @Override
            protected Model getNewModel() {
                return model;
            }
        };
        AbstractModelController spyAbstractModelController = spy(abstractModelController);
        spyAbstractModelController.addNew();
        verify(spyAbstractModelController).getNewModel();
        verify(dao).save(model);
    }

    @Test
    public void findByIdAndOutput() throws Exception {
        GenericDAO dao = mock(GenericDAO.class);
        Model model = mock(Model.class);
        AbstractModelController abstractModelController = new AbstractModelController(dao) {
            @Override
            protected Model getNewModel() {
                return null;
            }
        };
        when(dao.findById(1l)).thenReturn(model);
        when(model.toString()).thenReturn("test model 1");
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        abstractModelController.findByIdAndOutput();
        assertTrue(byteArrayOutputStream.toString().contains("test model 1"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void findByNameAndOutput() throws Exception {
        GenericDAO dao = mock(GenericDAO.class);
        Model model = mock(Model.class);
        AbstractModelController abstractModelController = new AbstractModelController(dao) {
            @Override
            protected Model getNewModel() {
                return null;
            }
        };
        when(dao.findByName("test model 2")).thenReturn(model);
        when(model.toString()).thenReturn("test model 2");
        System.setIn(new ByteArrayInputStream("test model 2".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        abstractModelController.findByNameAndOutput();
        assertTrue(byteArrayOutputStream.toString().contains("test model 2"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void deleteById() {
        GenericDAO dao = mock(GenericDAO.class);
        Model model = mock(Model.class);
        AbstractModelController abstractModelController = new AbstractModelController(dao) {
            @Override
            protected Model getNewModel() {
                return null;
            }
        };
        when(dao.findById(1l)).thenReturn(model).thenReturn(null);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        abstractModelController.deleteById();
        verify(dao).delete(model);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        abstractModelController.deleteById();
        verify(dao, never()).delete(null);
        assertTrue(byteArrayOutputStream.toString().contains("Model with this id is not found"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void update() {
        GenericDAO dao = mock(GenericDAO.class);
        Model newModel = mock(Model.class);
        Model oldModel = mock(Model.class);
        AbstractModelController abstractModelController = new AbstractModelController(dao) {
            @Override
            protected Model getNewModel() {
                return newModel;
            }
        };
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        when(dao.findById(1l)).thenReturn(oldModel).thenReturn(null);
        abstractModelController.update();
        verify(newModel).setId(1l);
        verify(dao).update(newModel);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        abstractModelController.update();
        verify(dao, never()).update(null);
        assertTrue(byteArrayOutputStream.toString().contains("Model with this id is not found"));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}