package org.myaukalki;

import org.myaukalki.domain.contracts.*;
import org.myaukalki.domain.implementations.CatImpl;
import org.myaukalki.dto.CreatePetDto;
import org.myaukalki.dto.OwnerRequestDto;
import org.myaukalki.dto.PetAnswerDto;
import org.myaukalki.dto.PetRequestDto;
import org.myaukalki.implementations.HibernateCriteriaQuery;
import org.myaukalki.implementations.OwnerDaoImpl;
import org.myaukalki.implementations.PetDaoImpl;
import org.myaukalki.mapper.CatMapper;

import java.util.List;

public class PetService {

    private final PetDaoImpl petDao;
    private final OwnerService ownerService;

    public PetService(PetDaoImpl petDao) {
        this.petDao = petDao;
        this.ownerService = new OwnerService(new OwnerDaoImpl());
    }

    public PetAnswerDto save(PetRequestDto pet) {

        return CatMapper.mapToDto(
                (CatImpl) petDao.save(
                        CatMapper.mapToEntity(pet)));
    }

    public PetAnswerDto delete(PetRequestDto pet) {

        return CatMapper.mapToDto(
                (CatImpl) petDao.delete(
                        CatMapper.mapToEntity(pet)));
    }

    public PetAnswerDto update(PetRequestDto pet) {

        return CatMapper.mapToDto(
                (CatImpl) petDao.update(
                        CatMapper.mapToEntity(pet)));
    }

    public PetAnswerDto find(Long id) {
        return CatMapper.mapToDto((CatImpl) petDao.find(id));
    }

    public List<Pet> findAll(HibernateCriteriaQuery<Pet> predicate) {
        return petDao.findAll(predicate);
    }

    public PetAnswerDto addFriend(PetRequestDto target, PetRequestDto pet) {

        petDao.addFriend(target.getId(), pet.getId());

        return CatMapper.mapToDto(CatMapper.mapToEntity(pet));
    }

    public void removeFriend(PetRequestDto target, PetRequestDto pet) {
        petDao.removeFriend(target.getId(), pet.getId());
    }

    public PetAnswerDto createPet(CreatePetDto pet) {
        var entity = CatMapper.mapToEntity(pet);
        petDao.save(entity);

        var catDto = new PetRequestDto();
        catDto.setId(entity.getId());

        var ownerDto = new OwnerRequestDto();
        ownerDto.setId(entity.getOwner().getId());

        ownerService.addPet(ownerDto, catDto);

        return CatMapper.mapToDto(entity);
    }

    public PetAnswerDto removePet(PetRequestDto pet) {
        var entity = CatMapper.mapToEntity(pet);
        petDao.delete(entity);

        return CatMapper.mapToDto(entity);
    }
}
