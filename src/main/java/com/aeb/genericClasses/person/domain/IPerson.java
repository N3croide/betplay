package com.aeb.genericClasses.person.domain;

import java.util.List;

public interface IPerson<T> {
    List<T> getAll();
    void save(T entity);
    T getById(Integer id);
}
