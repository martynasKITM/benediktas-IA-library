package com.benedict.library.dao;

import java.util.List;

/**
 * Generic Data Access Object (DAO) interface that provides basic CRUD operations for entities.
 *
 * This interface defines the basic operations required for a DAO (Data Access Object) that interacts with
 * a persistent storage, such as a database. It provides generic methods for creating, reading, updating,
 * and deleting entities of type `T`. Implementations of this interface should define the specific behavior
 * for each operation.
 *
 * @param <T> the type of entity the DAO will manage (e.g., `Author`, `User`, etc.)
 */
public interface GenericDAO<T> {

    /**
     * Finds an entity by its unique identifier.
     *
     * @param id the unique identifier of the entity
     * @return the entity with the specified ID, or null if not found
     */
    T findById(int id);

    /**
     * Creates a new entity with the specified attributes.
     *
     * @param fName the first name of the entity (e.g., author or user)
     * @param lastName the last name of the entity
     * @param email the email address of the entity
     * @param city the city of the entity
     */
    void create(String fName, String lastName, String email, String city);

    /**
     * Updates an existing entity in the persistent storage.
     *
     * @param entity the entity to be updated
     */
    void update(T entity);

    /**
     * Deletes an entity from the persistent storage by its ID.
     *
     * @param id the unique identifier of the entity to be deleted
     */
    void delete(int id);

    /**
     * Finds all entities of type `T` from the persistent storage.
     *
     * @return a list of all entities of type `T`
     */
    List<T> findAll();
}
