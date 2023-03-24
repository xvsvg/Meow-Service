package org.myaukalki.dto;

import lombok.Data;
import org.myaukalki.domain.utils.Color;

import java.time.LocalDate;
import java.util.List;

public @Data class CreatePetDto {

    private String name;
    private String breed;
    private LocalDate birthDate;
    private OwnerAnswerDto owner;
    private Color color;
}
