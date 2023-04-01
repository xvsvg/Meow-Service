package org.myaukalki.implementations;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.myaukalki.contracts.BaseDao;
import org.myaukalki.domain.contracts.Owner;

import java.util.List;

import org.myaukalki.factories.HibernateFactory;

public class OwnerDaoImpl extends BaseDao<Owner> {
    @Override
    public Owner save(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        session.close();

        return owner;
    }

    @Override
    public Owner delete(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(owner);
        transaction.commit();
        session.close();

        return owner;
    }

    @Override
    public Owner update(Owner owner) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(owner);
        transaction.commit();
        session.close();

        return owner;
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
    public List<Owner> findAll(HibernateCriteriaQuery<Owner> predicate) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {

            return predicate.execute(session.createCriteria(Owner.class));
        }
    }
}
