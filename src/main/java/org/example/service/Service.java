package org.example.service;

import java.util.List;
import java.util.Optional;

/**
 * Service that creates and reads data transfer object models
 *
 * @param <K> defines DTO model
 */
public interface Service<T, K> {
    /**
     * Method is used to validate model and save it
     * it database.
     *
     * @param object describes a model
     * @return true if committed otherwise false
     */
    boolean create(K object);

    /**
     * Method is used to validate model and save it
     * it database.
     *
     * @return Optional<List> of Data Transfer Objects of all found
     * models
     */
    Optional<List<K>> findAll();
}
