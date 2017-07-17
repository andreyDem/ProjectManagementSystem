package com.management.project.controllers;

import com.management.project.dao.CompanyDAO;
import com.management.project.dao.CustomerDAO;
import com.management.project.dao.GenericDAO;
import com.management.project.models.Company;
import com.management.project.models.Customer;
import com.management.project.models.Project;

import java.util.Scanner;

/**
 * An controller for working with Project entity
 *
 * @author Slava Makhinich
 */
public class ProjectController extends AbstractModelController<Project> {

    /**
     * An instance of CompanyDAO for working with database with Company entity
     */
    protected CompanyDAO companyDAO;

    /**
     * An instance of CustomerDAO for working with database with Customer entity
     */
    protected CustomerDAO customerDAO;

    /**
     * Constructor
     *
     * @param dao  An instance of GenericDAO<Project, Long> for working with database with Project entity
     * @param companyDAO  An instance of CompanyDAO for working with database with Company entity
     * @param customerDAO An instance of CustomerDAO for working with database with Customer entity
     */
    public ProjectController(
            GenericDAO<Project, Long> dao,
            CompanyDAO companyDAO,
            CustomerDAO customerDAO
    ) {
        super(dao);
        this.companyDAO = companyDAO;
        this.customerDAO = customerDAO;
    }

    /**
     * The method prints menu of this controller
     */
    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("ACTIONS WITH PROJECTS:");
        super.printMenu();
    }

    /**
     * Method for creating new projects. The method asks user for project's parameters,
     * and return a new project with these parameters
     *
     * @return a new project with parameters that the user inputted
     */
    @Override
    protected Project getNewModel() {
        System.out.print("Input project name: ");
        String projectName = new Scanner(System.in).nextLine();
        System.out.print("Input project cost: ");
        int projectCost = new Scanner(System.in).nextInt();
        System.out.print("Input company id: ");
        long companyId = new Scanner(System.in).nextLong();
        System.out.print("Input customer id: ");
        long customerId = new Scanner(System.in).nextLong();
        Company company = companyDAO.findById(companyId);
        Customer customer = customerDAO.findById(customerId);
        Project project = new Project(-100, projectName, projectCost, company, customer);
        return project;
    }
}
