package org.myaukalki.implementations;

import org.hibernate.Criteria;

import java.util.List;

public interface HibernateCriteriaQuary<T> {
    List<T> execute(Criteria criteria);
}
