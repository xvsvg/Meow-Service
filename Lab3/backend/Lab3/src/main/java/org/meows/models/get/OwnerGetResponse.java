package org.meows.models.get;

import lombok.Data;
import org.meows.entities.OwnerEntity;
import org.meows.models.CatResponse;
import org.meows.models.OwnerResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public @Data class OwnerGetResponse {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private List<CatResponse> cats = new ArrayList<>();

    protected OwnerGetResponse() {
    }

    public static OwnerGetResponse toModel(OwnerEntity owner) {
        var ownerResponse = new OwnerGetResponse();
        ownerResponse.setId(owner.getId());
        ownerResponse.setName(owner.getName());
        ownerResponse.setBirthDate(owner.getBirthDate());
        ownerResponse.setCats(owner.getPets().stream().map(CatResponse::toModel).toList());

        return ownerResponse;
    }
}
