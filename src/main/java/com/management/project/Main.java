package com.management.project;

import com.management.project.controllers.AbstractController;
import com.management.project.controllers.MainController;
import com.management.project.factory.FactoryController;
import com.management.project.factory.HibFactoryController;

import java.sql.SQLException;

/**
 * Class to run the program. The class extends AbstractController class and implements its abstract methods
 *
 * @author Slava Makhinich
 */
public class Main extends AbstractController {

    /**
     * The main method, method to run the program
     *
     * @param args an array of strings, arguments of the program
     */
    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye!");
    }

    /**
     * The method create an instance of MainController and runs its method start(). There could be two types of
     * MainController's instance, one of them uses JDBC DAO, and another uses Hibernate DAO.
     * The type of MainController's instance depends on user's choice, that we get from readChoice() method.
     *
     * @param choice an integer, the users choice, that we get from readChoice() method.
     *               If choice == 1 then program uses JDBC DAO, if choice == 2 then program uses Hibernate DAO.
     *
     * @throws SQLException in case of problem with connection to the database
     */
    @Override
    protected void action(int choice) throws SQLException {
        switch (choice) {
        case 1:
            new MainController(FactoryController.getCompanyController(),
                    FactoryController.getCustomerController(),
                    FactoryController.getDeveloperController(),
                    FactoryController.getProjectController(),
                    FactoryController.getSkillController())
                    .start();
            break;
        case 2:
            new MainController(HibFactoryController.getCompanyController(),
                    HibFactoryController.getCustomerController(),
                    HibFactoryController.getDeveloperController(),
                    HibFactoryController.getProjectController(),
                    HibFactoryController.getSkillController())
                    .start();
            break;
        }
    }

    /**
     * The method prints menu of this class
     */
    @Override
    protected void printMenu() {
        System.out.println("What DAO do you want to use?");
        System.out.println("1- JDBC DAO");
        System.out.println("2- Hibernate DAO");
        System.out.println("0- exit");
    }
}
