package org.myaukalki.mapper;

import org.myaukalki.domain.contracts.Owner;
import org.myaukalki.domain.contracts.Pet;
import org.myaukalki.domain.implementations.CatImpl;
import org.myaukalki.domain.implementations.OwnerImpl;
import org.myaukalki.dto.CreateOwnerDto;
import org.myaukalki.dto.OwnerAnswerDto;
import org.myaukalki.dto.OwnerRequestDto;
import org.myaukalki.implementations.OwnerDaoImpl;

import java.time.LocalDate;
import java.util.LinkedList;

public class OwnerMapper {

    private static final OwnerDaoImpl ownerDao = new OwnerDaoImpl();

    private OwnerMapper() {
    }

    public static Owner mapToEntity(OwnerRequestDto dto) {
        var owner = ownerDao.find(dto.getId());
        return owner;
    }

    public static Owner mapToEntity(CreateOwnerDto dto) {
        OwnerImpl owner = new OwnerImpl(
                dto.getName(),
                dto.getBirthDate());

        return owner;
    }

    public static Owner mapToEntity(OwnerAnswerDto dto) {
        var owner = ownerDao.find(dto.getId());
        return owner;
    }

    public static OwnerAnswerDto mapToDto(Owner owner) {
        OwnerAnswerDto answer = new OwnerAnswerDto();
        answer.setId(owner.getId());
        answer.setName(owner.getName());
        answer.setBirthDate(owner.getBirthDate());
        answer.setPets(new LinkedList<>(owner.getPets()
                .stream().map((Pet cat) -> CatMapper.mapToDto((CatImpl) cat)).toList()));
        return answer;
    }
}
