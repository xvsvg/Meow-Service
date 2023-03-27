package org.meows.models;

import lombok.Data;

import java.time.LocalDate;

public @Data class CreateOwnerRequest {

    private String name;

    private LocalDate birthDate;
}
