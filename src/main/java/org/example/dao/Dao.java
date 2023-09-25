package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Data access object that creates, reads, updates and deletes models in database
 *
 * @param <T> defines model this DAO works with
 */
public interface Dao<T> {

    /**
     * Method is used to save a model in database
     *
     * @param object describes a model
     * @return true if committed otherwise false
     */
    boolean create(T object);

    /**
     * Method is used to retrieve all saved models from database
     *
     * @return List of all found models
     */
    Optional<List<T>> findAll();

    /**
     * Method is used to update a model in database
     *
     * @param object describes a model we want to replace with the same ID in the table
     * @return passed object
     */
    T update(T object);

    /**
     * Method is used to drop a model from table in database
     *
     * @param object describes a model we want to delete with the same ID in the table
     * @return passed object
     */
    T delete(T object) throws SQLException;
}
