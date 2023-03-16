package org.myaukalki.contracts;

import org.myaukalki.domain.contracts.Owner;
import org.myaukalki.implementations.HibernateCriteriaQuary;

import java.util.List;

import javax.persistence.criteria.Predicate;

public abstract class BaseDao<T> {

    public abstract void save(T object);

    public abstract void delete(T object);

    public abstract void update(T object);

    public abstract T find(Long id);

    //    public abstract List<T> findAll(Predicate predicate);
    public abstract List<T> findAll(HibernateCriteriaQuary<T> predicate);

}
