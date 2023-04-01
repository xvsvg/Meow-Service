package org.meows.models.get;

import lombok.Data;
import org.meows.entities.CatEntity;
import org.meows.models.CatResponse;
import org.meows.models.OwnerResponse;
import org.meows.models.utils.Color;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public @Data class CatGetResponse {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String breed;

    private Color color;

    private OwnerResponse owner;

    private List<CatResponse> friends = new ArrayList<>();

    protected CatGetResponse() {
    }

    public static CatGetResponse toModel(CatEntity cat) {
        var response = new CatGetResponse();
        response.setId(cat.getId());
        response.setName(cat.getName());
        response.setBirthDate(cat.getBirthDate());
        response.setBreed(cat.getBreed());
        response.setColor(cat.getColor());
        response.setOwner(OwnerResponse.toModel(cat.getOwner()));
        response.setFriends(cat.getFriends().stream().map(CatResponse::toModel).toList());

        return response;
    }
}
