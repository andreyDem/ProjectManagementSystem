package com.management.project.controllers;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Abstract class, parent for all controllers
 *
 * @author Slava Makhinich
 */
public abstract class AbstractController {

    /**
     *The method must be called first when working with an instance of inherits class. This method invokes another
     * methods of this class.
     *
     * @throws SQLException in case of connection problems
     */
    public void start() throws SQLException {
        int choice;
        while (true) {
            printMenu();
            choice = readChoice();
            if (choice == 0) {
                break;
            }
            action(choice);
        }
    }

    /**
     * The method reads a choice of user
     *
     * @return integer, a choice of user
     */
    protected int readChoice() {
        int choice;
        System.out.println("Input your choice: ");
        try {
            choice = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
        }
        return choice;
    }

    /**
     *  An abstract method. Actions will depend on choice of user
     *
     * @param choice a choice of user, that we get from method readChoice()
     * @throws SQLException in case of connection problems
     */
    protected abstract void action(int choice) throws SQLException;

    /**
     * An abstract method. This method will print menu of inherit classes
     */
    protected abstract void printMenu();
}
