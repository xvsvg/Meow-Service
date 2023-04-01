package org.myaukalki.implementations;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.myaukalki.contracts.BaseDao;
import org.myaukalki.domain.contracts.Pet;
import org.myaukalki.domain.implementations.CatImpl;
import org.myaukalki.factories.HibernateFactory;

import java.util.List;

public class PetDaoImpl extends BaseDao<Pet> {
    @Override
    public Pet save(Pet pet) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(pet);
        transaction.commit();
        session.close();

        return pet;
    }

    @Override
    public Pet delete(Pet pet) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(pet);
        transaction.commit();
        session.close();

        return pet;
    }

    @Override
    public Pet update(Pet pet) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(pet);
        transaction.commit();
        session.close();

        return pet;
    }

    @Override
    public Pet find(Long id) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            return session.get(Pet.class, id);
        }
    }

//    @Override
//    public List<Pet> findAll(Predicate predicate) {
//        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
//            var builder = session.getCriteriaBuilder();
//
//            var query = builder.createQuery(Pet.class)
//                    .where(predicate);
//
//            return session.createQuery(query).getResultList();
//        }
//    }

    @Override
    public List<Pet> findAll(HibernateCriteriaQuery<Pet> predicate) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {

            return predicate.execute(session.createCriteria(Pet.class));
        }
    }

    public Pet addFriend(Long petId, Long friendId) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Pet pet = session.get(Pet.class, petId);
            Pet friend = session.get(Pet.class, friendId);

            ((CatImpl) pet).getFriends().add(friend);
            transaction.commit();

            return pet;
        }
    }

    public void removeFriend(Long petId, Long friendId) {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            Pet pet = session.get(Pet.class, petId);
            Pet friend = session.get(Pet.class, friendId);

            ((CatImpl) pet).getFriends().remove(friend);
            transaction.commit();
        }
    }
}
