package org.myaukalki.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data class CreateOwnerDto {

    private String name;
    private LocalDate birthDate;
}
