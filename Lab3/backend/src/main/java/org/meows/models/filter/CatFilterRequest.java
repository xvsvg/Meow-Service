package org.meows.models.filter;

import lombok.Data;
import org.meows.models.utils.Color;

import java.time.LocalDate;

public @Data class CatFilterRequest {

    private String name = "";

    private LocalDate birthDate = LocalDate.MIN;

    private String breed = "";

    private Color color = Color.Default;

    private Long owner = Long.MIN_VALUE;
}
