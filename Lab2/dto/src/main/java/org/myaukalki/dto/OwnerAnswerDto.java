package org.myaukalki.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public @Data class OwnerAnswerDto {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<PetAnswerDto> pets;
}
