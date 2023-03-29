package org.meows.models.create;

import lombok.Data;

import java.time.LocalDate;

public @Data class CreateOwnerRequest {

    private String name;

    private LocalDate birthDate;
}
