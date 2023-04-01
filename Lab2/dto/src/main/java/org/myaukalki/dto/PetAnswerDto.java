package org.myaukalki.dto;

import lombok.Data;
import org.myaukalki.domain.utils.Color;

import java.time.LocalDate;
import java.util.List;

public @Data class PetAnswerDto {
    private Long id;
    private String name;
    private String breed;
    private LocalDate birthDate;
    private Long ownerId;
    private Color color;
    private List<PetAnswerDto> friends;
}
