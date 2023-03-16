package org.myaukalki.implementations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.myaukalki.contracts.BaseDao;
import org.myaukalki.domain.contracts.Owner;

import java.util.List;
import javax.persistence.criteria.Predicate;

import org.myaukalki.factories.HibernateFactory;

public class OwnerDaoImpl extends BaseDao<Owner> {
    @Override
    public void save(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(owner);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(owner);
        transaction.commit();
        session.close();
    }

    @Override
    public Owner find(Long id) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            return session.get(Owner.class, id);
        }
    }

//    @Override
//    public List<Owner> findAll(Predicate predicate) {
//        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
//            var builder = session.getCriteriaBuilder();
//
//            var query = builder.createQuery(Owner.class)
//                    .where(predicate);
//
//            return session.createQuery(query).getResultList();
//        }
//    }

    @Override
    public List<Owner> findAll(HibernateCriteriaQuary<Owner> predicate) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {

            return predicate.execute(session.createCriteria(Owner.class));
        }
    }
}
