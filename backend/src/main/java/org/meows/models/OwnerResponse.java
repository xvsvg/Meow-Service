package org.meows.models;

import lombok.Data;
import org.meows.entities.CatEntity;
import org.meows.entities.OwnerEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public @Data class OwnerResponse {

    private Long id;

    private String name;

    private LocalDate birthDate;

    public OwnerResponse() {}

    public static OwnerResponse toModel(OwnerEntity owner) {
        var ownerResponse = new OwnerResponse();
        ownerResponse.setId(owner.getId());
        ownerResponse.setName(owner.getName());
        ownerResponse.setBirthDate(owner.getBirthDate());

        return ownerResponse;
    }
}
