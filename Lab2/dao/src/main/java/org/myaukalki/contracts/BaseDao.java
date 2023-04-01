package org.myaukalki.contracts;

import org.myaukalki.implementations.HibernateCriteriaQuery;

import java.util.List;

public abstract class BaseDao<T> {

    public abstract T save(T object);

    public abstract T delete(T object);

    public abstract T update(T object);

    public abstract T find(Long id);

    //    public abstract List<T> findAll(Predicate predicate);
    public abstract List<T> findAll(HibernateCriteriaQuery<T> predicate);

}
