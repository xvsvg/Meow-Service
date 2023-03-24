package org.myaukalki;

import org.myaukalki.domain.contracts.Owner;
import org.myaukalki.domain.contracts.Pet;
import org.myaukalki.dto.*;
import org.myaukalki.implementations.HibernateCriteriaQuery;
import org.myaukalki.implementations.OwnerDaoImpl;
import org.myaukalki.mapper.CatMapper;
import org.myaukalki.mapper.OwnerMapper;

import java.util.LinkedList;
import java.util.List;

public class OwnerService {

    private final OwnerDaoImpl ownerDao;

    public OwnerService(OwnerDaoImpl ownerDao) {
        this.ownerDao = ownerDao;
    }

    public OwnerAnswerDto save(OwnerRequestDto dto) {

        return OwnerMapper.mapToDto(
                ownerDao.save(
                        OwnerMapper.mapToEntity(dto)));
    }

    public OwnerAnswerDto delete(OwnerRequestDto dto) {

        return OwnerMapper.mapToDto(
                ownerDao.delete(
                        OwnerMapper.mapToEntity(dto)));
    }

    public OwnerAnswerDto update(OwnerRequestDto dto) {

        return OwnerMapper.mapToDto(
                ownerDao.update(
                        OwnerMapper.mapToEntity(dto)));
    }

    public OwnerAnswerDto find(Long id) {
        return OwnerMapper.mapToDto(ownerDao.find(id));
    }

    public List<Owner> findAll(HibernateCriteriaQuery<Owner> predicate) {
        return ownerDao.findAll(predicate);
    }

    public PetAnswerDto addPet(OwnerRequestDto target, PetRequestDto pet) {
        var targetEntity = OwnerMapper.mapToEntity(target);
        var petEntity = CatMapper.mapToEntity(pet);

        petEntity.setOwner(targetEntity);

        LinkedList<Pet> newList = new LinkedList<>(targetEntity.getPets());
        newList.add(petEntity);
        targetEntity.setPets(newList);

        ownerDao.update(targetEntity);

        return CatMapper.mapToDto(petEntity);
    }

    public PetAnswerDto removePet(OwnerRequestDto target, PetRequestDto pet) {
        var targetEntity = OwnerMapper.mapToEntity(target);
        var petEntity = CatMapper.mapToEntity(pet);

        petEntity.setOwner(null);
        targetEntity.getPets().remove(petEntity);

        ownerDao.update(targetEntity);

        return CatMapper.mapToDto(petEntity);
    }

    public OwnerAnswerDto createOwner(CreateOwnerDto dto) {
        var entity = OwnerMapper.mapToEntity(dto);

        ownerDao.save(entity);

        return OwnerMapper.mapToDto(entity);
    }

    public OwnerAnswerDto removeOwner(OwnerRequestDto dto) {
        var entity = OwnerMapper.mapToEntity(dto);

        ownerDao.delete(entity);

        return OwnerMapper.mapToDto(entity);
    }
}
