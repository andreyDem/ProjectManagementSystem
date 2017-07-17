package com.management.project.factory;

import com.management.project.controllers.*;

import java.sql.SQLException;

/**
 * The class implements the Factory pattern.
 * It creates and supplies model's controllers,
 * and these controllers use JDBC DAOs
 *
 * @author Slava Makhinich
 */
public final class FactoryController {

    /**
     * An instance of CompanyController
     */
    private static CompanyController companyController;

    /**
     * An instance of CustomerController
     */
    private static CustomerController customerController;

    /**
     * An instance of DeveloperController
     */
    private static DeveloperController developerController;

    /**
     * An instance of ProjectController
     */
    private static ProjectController projectController;

    /**
     * An instance of SkillController
     */
    private static SkillController skillController;

    /**
     * Private constructor
     */
    private FactoryController() {
    }

    /**
     * The method returns an instance of CompanyController,
     * and creates it if it is not exist
     *
     * @return an instance of CompanyController
     * @throws SQLException in case of connection problems
     */
    public static CompanyController getCompanyController() throws SQLException {
        if (companyController == null) {
            companyController = new CompanyController(FactoryDao.getCompanyDAO());
        }
        return companyController;
    }

    /**
     * The method returns an instance of CustomerController,
     * and creates it if it is not exist
     *
     * @return an instance of CustomerController
     * @throws SQLException
     */
    public static CustomerController getCustomerController() throws SQLException {
        if (customerController == null) {
            customerController = new CustomerController(FactoryDao.getCustomerDAO());
        }
        return customerController;
    }

    /**
     * The method returns an instance of DeveloperController,
     * and creates it if it is not exist
     *
     * @return an instance of DeveloperController
     * @throws SQLException in case of connection problems
     */
    public static DeveloperController getDeveloperController() throws SQLException {
        if (developerController == null) {
            developerController = new DeveloperController(FactoryDao.getDeveloperDAO(), FactoryDao.getSkillDAO(),
                    FactoryDao.getCompanyDAO(), FactoryDao.getProjectDAO());
        }
        return developerController;
    }

    /**
     * The method returns an instance of ProjectController,
     * and creates it if it is not exist
     *
     * @return an instance of ProjectController
     * @throws SQLException in case of connection problems
     */
    public static ProjectController getProjectController() throws SQLException {
        if (projectController == null) {
            projectController = new ProjectController(FactoryDao.getProjectDAO(), FactoryDao.getCompanyDAO(),
                    FactoryDao.getCustomerDAO());
        }
        return projectController;
    }

    /**
     * The method returns an instance of SkillController,
     * and creates it if it is not exist
     *
     * @return an instance of SkillController
     * @throws SQLException in case of connection problems
     */
    public static SkillController getSkillController() throws SQLException {
        if (skillController == null) {
            skillController = new SkillController(FactoryDao.getSkillDAO());
        }
        return skillController;
    }
}
