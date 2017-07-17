package com.management.project.factory;

import com.management.project.controllers.*;

/**
 * The class implements the Factory pattern.
 * It creates and supplies model's controllers,
 * and these controllers use Hibernate DAOs
 *
 * @author Slava Makhinich
 */
public final class HibFactoryController {

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
    private HibFactoryController() {
    }

    /**
     * The method returns an instance of CompanyController,
     * and creates it if it is not exist
     *
     * @return an instance of CompanyController
     */
    public static CompanyController getCompanyController() {
        if (companyController == null) {
            companyController = new CompanyController(HibFactoryDao.getCompanyDAO());
        }
        return companyController;
    }

    /**
     * The method returns an instance of CustomerController,
     * and creates it if it is not exist
     *
     * @return an instance of CustomerController
     */
    public static CustomerController getCustomerController() {
        if (customerController == null) {
            customerController = new CustomerController(HibFactoryDao.getCustomerDAO());
        }
        return customerController;
    }

    /**
     * The method returns an instance of DeveloperController,
     * and creates it if it is not exist
     *
     * @return an instance of DeveloperController
     */
    public static DeveloperController getDeveloperController() {
        if (developerController == null) {
            developerController = new DeveloperController(
                    HibFactoryDao.getDeveloperDAO(),
                    HibFactoryDao.getSkillDAO(),
                    HibFactoryDao.getCompanyDAO(),
                    HibFactoryDao.getProjectDAO()
            );
        }
        return developerController;
    }

    /**
     * The method returns an instance of ProjectController,
     * and creates it if it is not exist
     *
     * @return an instance of ProjectController
     */
    public static ProjectController getProjectController() {
        if (projectController == null) {
            projectController = new ProjectController(
                    HibFactoryDao.getProjectDAO(),
                    HibFactoryDao.getCompanyDAO(),
                    HibFactoryDao.getCustomerDAO()
            );
        }
        return projectController;
    }

    /**
     * The method returns an instance of SkillController,
     * and creates it if it is not exist
     *
     * @return an instance of SkillController
     */
    public static SkillController getSkillController() {
        if (skillController == null) {
            skillController = new SkillController(HibFactoryDao.getSkillDAO());
        }
        return skillController;
    }
}
