package org.xonely.repository;

import java.util.List;

public interface GenericRepo<T,ID> {
    List<T> getAll();
    T getById(ID id);
    T save(T t);
    T update(T t);

    void deleteById(ID id);
}
