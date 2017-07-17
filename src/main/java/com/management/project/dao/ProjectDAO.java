package com.management.project.dao;

import com.management.project.models.Project;

/**
 * The interface extends methods from GenericDAO for working with data base and entity
 * The interface will be implemented by DAO classes that will work with Project entity
 *
 * @author Perevoznyk Pavel
 */
public interface ProjectDAO extends GenericDAO<Project, Long> {

}
