package org.myaukalki;

import org.myaukalki.domain.contracts.Owner;
import org.myaukalki.domain.contracts.Pet;
import org.myaukalki.implementations.HibernateCriteriaQuary;
import org.myaukalki.implementations.OwnerDaoImpl;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class OwnerService {

    private final OwnerDaoImpl ownerDao;

    public OwnerService(OwnerDaoImpl ownerDao) {
        this.ownerDao = ownerDao;
    }

    public void save(Owner owner) {
        ownerDao.save(owner);
    }

    public void delete(Owner owner) {
        ownerDao.delete(owner);
    }

    public void update(Owner owner) {
        ownerDao.update(owner);
    }

    public Owner find(Long id) {
        return ownerDao.find(id);
    }

    public List<Owner> findAll(HibernateCriteriaQuary<Owner> predicate) {
        return ownerDao.findAll(predicate);
    }

    public Pet addPet(Owner target, Pet pet) {
        pet.setOwner(target);
        target.getPets().add(pet);

        return pet;
    }

    public Pet removePet(Owner target, Pet pet) {
        pet.setOwner(null);
        target.getPets().remove(pet);

        return pet;
    }
}
