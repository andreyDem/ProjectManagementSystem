package com.management.project.dao.hibernate;

import com.management.project.dao.CompanyDAO;
import com.management.project.models.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implements a set of methods for working with
 * database including Hibernate framework, with Company entity
 *
 * @author Slava Makhinich
 */
public class HibCompanyDao implements CompanyDAO {

    /**
     * An instance of SessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param sessionFactory an instance of SessionFactory
     */
    public HibCompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * The method saves a new project in a database
     *
     * @param company a company, which must be save in a database
     * @return companies id if a company was add to database successfully
     */
    @Override
    public Long save(Company company) {
        Long id = null;
        try (Session session = sessionFactory.openSession()) {
            id = (Long) session.save(company);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to save company " + company);
            e.printStackTrace();
        }
        return id;
    }

    /**
     * The method finds a company in database by id of the company
     *
     * @param id an id of a company
     * @return a company with entered id
     * or new company with empty parameters if company with this id does not exist in the database
     */
    @Override
    public Company findById(Long id) {
        Company company = new Company(id, "");
        try (Session session = sessionFactory.openSession()) {
            Company companyFromDB = session.get(Company.class, id);
            if (companyFromDB != null) {
                company = companyFromDB;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find company with id: " + id);
            e.printStackTrace();
        }
        return company;
    }

    /**
     * The method updates a company in a database
     * (finds company in a database by id and overwrites other fields)
     *
     * @param company is a company with new parameters
     */
    @Override
    public void update(Company company) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Company companyFromDb = session.get(Company.class, company.getId());
            if (companyFromDb == null) {
                return;
            }
            companyFromDb.setName(company.getName());
            session.update(companyFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to update company " + company);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * The method removes a company from a database
     *
     * @param company is a company which must be removed
     */
    @Override
    public void delete(Company company) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Company companyFromDb = session.get(Company.class, company.getId());
            if (companyFromDb == null) {
                return;
            }
            session.delete(companyFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to delete company " + company);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * The method returns all companies from a database
     *
     * @return list of all companies from a database
     */
    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            companies = session.createQuery("from Company").list();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find all companies");
            e.printStackTrace();
        }
        return companies;
    }

    /**
     * Method finds a company in a database by name of the company
     *
     * @param name is a name of a company
     * @return a company with entered name
     * or new company with empty parameters if company with this name does not exist in the database
     */
    @Override
    public Company findByName(String name) {
        Company company = new Company(0, name);
        try (Session session = sessionFactory.openSession()) {
            List<Company> companies = session.createQuery("select c from Company c where c.name like :name")
                    .setParameter("name", name).list();
            if (companies.size() != 0) {
                company = companies.get(0);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find company with name: " + name);
            e.printStackTrace();
        }
        return company;
    }
}
