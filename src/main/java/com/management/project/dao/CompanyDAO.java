package com.management.project.dao;

import com.management.project.models.Company;

/**
 * The interface extends methods from GenericDAO for working with data base and entity
 * The interface will be implemented by DAO classes that will work with Company entity
 *
 * @author Perevoznyk Pavel
 */
public interface CompanyDAO extends GenericDAO<Company, Long> {

}
