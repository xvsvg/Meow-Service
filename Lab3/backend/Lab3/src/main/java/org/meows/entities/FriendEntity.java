package org.meows.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "friends")
public class FriendEntity {

    @ManyToMany(mappedBy = "friends", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<CatEntity> friends = new ArrayList<>();
}
