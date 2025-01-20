package com.benedict.minibank.Services;

import java.util.List;

public interface GenericDAO<T> {
    T findById(int id);
    void create(T entity);
    void update (T entity);
    void delete(int id);
    List<T> findAll();
}
