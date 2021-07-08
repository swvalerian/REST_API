package com.swvalerian.restapi.repository;

import java.util.List;

public interface GenericRepository<T,ID> {
    List<T> getAll();
    List<T> update(T t);
    T getId(ID id);
    T save(T t);
    void deleteById(ID id);
}
