package org.meows.models.create;

import lombok.Data;
import org.meows.models.utils.Color;

import java.time.LocalDate;

public @Data class CreateCatRequest {

    private String name;

    private LocalDate birthDate;

    private String breed;

    private Long ownerId;

    private Color color;
}
