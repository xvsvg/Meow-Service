package org.meows.models;

import lombok.Data;
import org.meows.entities.CatEntity;
import org.meows.models.utils.Color;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public @Data class CatResponse {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String breed;

    private Color color;

    private OwnerResponse owner;

    private List<CatResponse> friends = new ArrayList<CatResponse>();

    protected CatResponse() {}

    public static CatResponse toModel(CatEntity cat) {
        var response = new CatResponse();
        response.setId(cat.getId());
        response.setName(cat.getName());
        response.setBirthDate(cat.getBirthDate());
        response.setBreed(cat.getBreed());
        response.setColor(cat.getColor());
        response.setOwner(OwnerResponse.toModel(cat.getOwner(), cat.getId()));
        response.setFriends(cat.getFriends().stream().map(CatResponse::toModel).toList());

        return response;
    }
}
