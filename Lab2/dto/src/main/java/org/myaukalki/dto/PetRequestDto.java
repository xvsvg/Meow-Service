package org.myaukalki.dto;

import lombok.Data;

import java.util.List;

public @Data class PetRequestDto {
    private Long id;
    private String name;
    private OwnerAnswerDto owner;
}
