package com.benedict.minibank.Services.dao;

import java.util.List;

public interface GenericDAO<T> {
    T findById(int id);
    void create(String fName, String lastName, String email, String city);
    void update (T entity);
    void delete(int id);
    List<T> findAll();
}
