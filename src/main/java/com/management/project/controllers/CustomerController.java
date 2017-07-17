package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.models.Customer;

import java.util.Scanner;

/**
 * An controller for working with Project entity
 *
 * @author Slava Makhinich
 */
public class CustomerController extends AbstractModelController<Customer> {

    /**
     * Constructor
     *
     * @param dao An instance of GenericDAO<Customer, Long> for working with database with Project entity
     */
    public CustomerController(GenericDAO<Customer, Long> dao) {
        super(dao);
    }

    /**
     * Method for creating new customers. The method asks user for customer's name,
     * and return a new customer with this name
     *
     * @return a new customer with name that the user inputted
     */
    @Override
    protected Customer getNewModel() {
        System.out.print("Input customer name: ");
        String customerName = new Scanner(System.in).nextLine();
        return new Customer(-100, customerName);
    }

    /**
     * The method prints menu of this controller
     */
    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("ACTIONS WITH CUSTOMERS:");
        super.printMenu();
    }
}
