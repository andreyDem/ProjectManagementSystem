package com.management.project.factory;

import com.management.project.dao.*;
import com.management.project.dao.hibernate.*;
import com.management.project.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The class implements the Factory pattern.
 * It creates and supplies of Hibernate implementations of DAOs
 *
 * @author Slava Makhinich
 */
public final class HibFactoryDao {

    /**
     * An instance of SessionFactory
     */
    private static SessionFactory sessionFactory;

    /**
     * An instance of CompanyDAO
     */
    private static CompanyDAO companyDAO;

    /**
     * An instance of CustomerDAO
     */
    private static CustomerDAO customerDAO;

    /**
     * An instance of DeveloperDAO
     */
    private static DeveloperDAO developerDAO;

    /**
     * An instance of ProjectDAO
     */
    private static ProjectDAO projectDAO;

    /**
     * An instance of SkillDAO
     */
    private static SkillDAO skillDAO;

    /**
     * Private constructor
     */
    private HibFactoryDao() {
    }

    /**
     * The method returns an instance of SessionFactory,
     * and creates it if it is not exist
     *
     * @return an instance of SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if ((sessionFactory == null) || (sessionFactory.isClosed())) {
            sessionFactory = new Configuration()
                    .configure("/META-INF/persistence.xml")
                    .addAnnotatedClass(Company.class)
                    .addAnnotatedClass(Developer.class)
                    .addAnnotatedClass(Skill.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Customer.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * The method returns an instance of CompanyDAO,
     * and creates it if it is not exist
     *
     * @return an instance of CompanyDAO
     */
    public static CompanyDAO getCompanyDAO() {
        if (companyDAO == null) {
            companyDAO = new HibCompanyDao(getSessionFactory());
        }
        return companyDAO;
    }

    /**
     * The method returns an instance of CustomerDAO,
     * and creates it if it is not exist
     *
     * @return an instance of CustomerDAO
     */
    public static CustomerDAO getCustomerDAO() {
        if (customerDAO == null) {
            customerDAO = new HibCustomerDao(getSessionFactory());
        }
        return customerDAO;
    }

    /**
     * The method returns an instance of DeveloperDAO,
     * and creates it if it is not exist
     *
     * @return an instance of DeveloperDAO
     */
    public static DeveloperDAO getDeveloperDAO() {
        if (developerDAO == null) {
            developerDAO = new HibDeveloperDao(getSessionFactory());
        }
        return developerDAO;
    }

    /**
     * The method returns an instance of ProjectDAO,
     * and creates it if it is not exist
     *
     * @return an instance of ProjectDAO
     */
    public static ProjectDAO getProjectDAO() {
        if (projectDAO == null) {
            projectDAO = new HibProjectDao(getSessionFactory());
        }
        return projectDAO;
    }

    /**
     * The method returns an instance of SkillDAO,
     * and creates it if it is not exist
     *
     * @return an instance of SkillDAO
     */
    public static SkillDAO getSkillDAO() {
        if (skillDAO == null) {
            skillDAO = new HibSkillDao(getSessionFactory());
        }
        return skillDAO;
    }

    /**
     * The method closes an instance of SessionFactory if it's exist and open
     */
    public static void disconnect() {
        if ((sessionFactory != null) && (sessionFactory.isOpen())) {
            sessionFactory.close();
        }
    }
}
