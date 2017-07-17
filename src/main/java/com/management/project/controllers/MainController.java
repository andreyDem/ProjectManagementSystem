package com.management.project.controllers;

import java.sql.SQLException;

/**
 * A class for working with models controllers. An instance of this class will be created in Main class
 *
 * @author Slava Makhinich
 */
public class MainController extends AbstractController {

    /**
     * An instance of CompanyController
     */
    private CompanyController companyController;

    /**
     * An instance of CustomerController
     */
    private CustomerController customerController;

    /**
     * An instance of DeveloperController
     */
    private DeveloperController developerController;

    /**
     * An instance of ProjectController
     */
    private ProjectController projectController;

    /**
     * An instance of SkillController
     */
    private SkillController skillController;

    /**
     *  Constructor
     *
     * @param companyController an instance of CompanyController
     * @param customerController an instance of CustomerController
     * @param developerController an instance of DeveloperController
     * @param projectController an instance of ProjectController
     * @param skillController an instance of SkillController
     */
    public MainController(
            CompanyController companyController,
            CustomerController customerController,
            DeveloperController developerController,
            ProjectController projectController,
            SkillController skillController
    ) {
        this.companyController = companyController;
        this.customerController = customerController;
        this.developerController = developerController;
        this.projectController = projectController;
        this.skillController = skillController;
    }

    /**
     * The method invokes the start() method of model controller, the choice of model controller depends on users
     * choice, that we get from method readChoice()
     *
     * @param choice a choice of user, that we get from method readChoice()
     * @throws SQLException in case of connection problems
     */
    @Override
    protected void action(int choice) throws SQLException {
        switch (choice) {
        case 1:
            companyController.start();
            break;
        case 2:
            customerController.start();
            break;
        case 3:
            developerController.start();
            break;
        case 4:
            projectController.start();
            break;
        case 5:
            skillController.start();
            break;
        }
    }

    /**
     * The method prints menu of this controller
     */
    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("MENU");
        System.out.println("1- actions with companies");
        System.out.println("2- actions with customers");
        System.out.println("3- actions with developers");
        System.out.println("4- actions with projects");
        System.out.println("5- actions with skills");
        System.out.println("0- exit");
    }
}
