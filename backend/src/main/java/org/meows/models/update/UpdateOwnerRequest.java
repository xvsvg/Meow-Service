package org.meows.models.update;

import lombok.Data;

import java.time.LocalDate;

public @Data class UpdateOwnerRequest {

    private Long id;

    private String name;

    private LocalDate birthDate;
}
