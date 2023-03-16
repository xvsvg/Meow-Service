package org.myaukalki;

import org.myaukalki.domain.contracts.*;
import org.myaukalki.domain.implementations.*;
import org.myaukalki.implementations.HibernateCriteriaQuary;
import org.myaukalki.implementations.PetDaoImpl;

import java.util.List;
import javax.persistence.criteria.Predicate;

public class PetService {

    private final PetDaoImpl petDao;

    public PetService(PetDaoImpl petDao) {
        this.petDao = petDao;
    }

    public void save(Pet pet) {
        petDao.save(pet);
    }

    public void delete(Pet pet) {
        petDao.delete(pet);
    }

    public void update(Pet pet) {
        petDao.update(pet);
    }

    public Pet find(Long id) {
        return petDao.find(id);
    }

    public List<Pet> findAll(HibernateCriteriaQuary<Pet> predicate) {
        return petDao.findAll(predicate);
    }

    public Pet addFriend(Pet target, Pet pet) {
        petDao.addFriend(target.getId(), pet.getId());

        return pet;
    }

    public void removeFriend(Pet target, Pet pet) {
        petDao.removeFriend(target.getId(), pet.getId());
    }


}
