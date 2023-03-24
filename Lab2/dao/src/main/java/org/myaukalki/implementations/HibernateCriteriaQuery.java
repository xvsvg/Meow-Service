package org.myaukalki.implementations;

import org.hibernate.Criteria;

import java.util.List;

public interface HibernateCriteriaQuery<T> {
    List<T> execute(Criteria criteria);
}
