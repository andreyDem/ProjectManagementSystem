package com.management.project.dao;

import com.management.project.models.Customer;

/**
 * The interface extends methods from GenericDAO for working with data base and entity
 * The interface will be implemented by DAO classes that will work with Customer entity
 *
 * @author Perevoznyk Pavel
 */
public interface CustomerDAO extends GenericDAO<Customer, Long> {

}
