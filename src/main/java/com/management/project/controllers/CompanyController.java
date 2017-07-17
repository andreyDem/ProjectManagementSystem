package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.models.Company;

import java.util.Scanner;

/**
 * An controller for working with Company entity
 *
 * @author Вадим
 */
public class CompanyController extends AbstractModelController<Company> {

    /**
     * Constructor
     *
     * @param dao An instance of GenericDAO<Company, Long> for working with database with Project entity
     */
    public CompanyController(GenericDAO<Company, Long> dao) {
        super(dao);
    }

    /**
     * Method for creating new companies. The method asks user for company's name,
     * and return a new company with this name
     *
     * @return a new company with name that the user inputted
     */
    @Override
    protected Company getNewModel() {
        System.out.print("Input company name: ");
        String companyName = new Scanner(System.in).nextLine();
        return new Company(-100, companyName);
    }

    /**
     * The method prints menu of this controller
     */
    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("ACTIONS WITH COMPANIES:");
        super.printMenu();
    }
}
