package com.management.project.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface with set of key methods for with data base and entities
 *
 * @author Perevoznyk Pavel
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Method for saving a new entity in a database
     *
     * @param obj an entity for saving in a database
     * @return id of saved entity
     */
    ID save(T obj);

    /**
     * Method for finding entity in a database by id
     *
     * @param id the id of an entity
     * @return founded entity
     */
    T findById(ID id);

    /**
     * Method for updating entity in a database
     *
     * @param obj an entity with new parameters for updating
     */
    void update(T obj);

    /**
     * Method for removing entity from a database
     *
     * @param obj entity that should be removed
     */
    void delete(T obj);

    /**
     * Method for getting all entities of a certain type
     *
     * @return list of entities
     */
    List<T> findAll();

    /**
     * Method for finding entity in a database by name
     *
     * @param name entity's name
     * @return founded entity
     */
    T findByName (String name);
}
