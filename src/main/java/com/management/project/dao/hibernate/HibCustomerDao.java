package com.management.project.dao.hibernate;

import com.management.project.dao.CustomerDAO;
import com.management.project.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implements a set of methods for working
 * with database including Hibernate framework, with Customer entity
 *
 * @author Slava Makhinich
 */
public class HibCustomerDao implements CustomerDAO {

    /**
     * An instance of SessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param sessionFactory an instance of SessionFactory
     */
    public HibCustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * The method saves a new project in a database
     *
     * @param customer a customer, which must be save in a database
     * @return customers id if a customer was add to database successfully
     */
    @Override
    public Long save(Customer customer) {
        Long id = null;
        try (Session session = sessionFactory.openSession()) {
            id = (Long) session.save(customer);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to save customer " + customer);
            e.printStackTrace();
        }
        return id;
    }

    /**
     * The method finds a customer in database by id of the customer
     *
     * @param id an id of a customer
     * @return a customer with entered id
     * or customer with empty parameters if customer with this id does not exist in the database
     */
    @Override
    public Customer findById(Long id) {
        Customer customer = new Customer(id, "");
        try (Session session = sessionFactory.openSession()) {
            Customer customerFromDB = session.get(Customer.class, id);
            if (customerFromDB != null) {
                customer = customerFromDB;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find customer with id: " + id);
            e.printStackTrace();
        }
        return customer;
    }

    /**
     * The method updates a customer in a database
     * (finds customer in a database by id and overwrites other fields)
     *
     * @param customer is a company with new parameters
     */
    @Override
    public void update(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Customer companyFromDb = session.get(Customer.class, customer.getId());
            if (companyFromDb == null) {
                return;
            }
            companyFromDb.setName(customer.getName());
            session.update(companyFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to update customer " + customer);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * The method removes a customer from a database
     *
     * @param customer is a customer which must be removed
     */
    @Override
    public void delete(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Customer companyFromDb = session.get(Customer.class, customer.getId());
            if (companyFromDb == null) {
                return;
            }
            session.delete(companyFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to delete customer " + customer);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * The method returns all customers from a database
     *
     * @return list of all customers from a database
     */
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            customers = session.createQuery("from Customer").list();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find all customers");
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Method finds a customer in a database by name of the customer
     *
     * @param name is a name of a customer
     * @return a customer with entered name
     * or customer with empty parameters if customer with this name does not exist in the database
     */
    @Override
    public Customer findByName(String name) {
        Customer customer = new Customer(0, name);
        try (Session session = sessionFactory.openSession()) {
            List<Customer> companies = session.createQuery("select c from Customer c where c.name like :name")
                    .setParameter("name", name).list();
            if (companies.size() != 0) {
                customer = companies.get(0);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find customer with name: " + name);
            e.printStackTrace();
        }
        return customer;
    }
}
