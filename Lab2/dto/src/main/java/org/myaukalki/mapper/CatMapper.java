package org.myaukalki.mapper;

import org.myaukalki.domain.implementations.CatImpl;
import org.myaukalki.domain.utils.Color;
import org.myaukalki.dto.CreatePetDto;
import org.myaukalki.dto.PetAnswerDto;
import org.myaukalki.dto.PetRequestDto;
import org.myaukalki.implementations.PetDaoImpl;

import java.time.LocalDate;

public class CatMapper {

    private static final PetDaoImpl petDao = new PetDaoImpl();

    private CatMapper() {}

    public static CatImpl mapToEntity(PetRequestDto dto) {
        var cat = petDao.find(dto.getId());
        return (CatImpl)cat;
    }

    public static CatImpl mapToEntity(CreatePetDto dto) {
        CatImpl cat = new CatImpl(dto.getName(),
                dto.getBirthDate(),
                OwnerMapper.mapToEntity(dto.getOwner()),
                dto.getColor(),
                dto.getBreed());

        return cat;
    }

    public static PetAnswerDto mapToDto(CatImpl cat) {
        PetAnswerDto catDto = new PetAnswerDto();
        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setBirthDate(cat.getBirthDate());
        catDto.setOwnerId(cat.getOwner().getId());
        catDto.setColor(cat.getColor());
        catDto.setBreed(cat.getBreed());
        catDto.setFriends(cat.getFriends()
                .stream()
                .map(f->CatMapper.mapToDto((CatImpl)f))
                .toList());

        return catDto;
    }
}
