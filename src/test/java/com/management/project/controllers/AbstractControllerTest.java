package com.management.project.controllers;

import org.junit.Test;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Slava Makhinich
 */
public class AbstractControllerTest {

    private static class MyRuntimeException extends RuntimeException{

    }

    @Test (expected = MyRuntimeException.class)
    public void start() throws Exception {
        AbstractController abstractController = mock(AbstractController.class, Mockito.CALLS_REAL_METHODS);
        System.setIn(new ByteArrayInputStream("0".getBytes()));
        abstractController.start();
        verify(abstractController).printMenu();
        verify(abstractController, never()).action(anyInt());
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        doThrow(new MyRuntimeException()).when(abstractController).action(1);
        abstractController.start();
    }

    @Test
    public void readChoice() throws Exception {
        AbstractController abstractController = mock(AbstractController.class, Mockito.CALLS_REAL_METHODS);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        assertEquals(1, abstractController.readChoice());
        System.setIn(new ByteArrayInputStream("0".getBytes()));
        assertEquals(0, abstractController.readChoice());
        System.setIn(new ByteArrayInputStream("wrongInput".getBytes()));
        assertEquals(-1, abstractController.readChoice());
    }

}