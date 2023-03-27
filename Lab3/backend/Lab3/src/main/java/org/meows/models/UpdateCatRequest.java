package org.meows.models;

import lombok.Data;
import org.meows.models.utils.Color;

import java.time.LocalDate;

public @Data class UpdateCatRequest {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String breed;

    private Long ownerId;

    private Color color;
}
