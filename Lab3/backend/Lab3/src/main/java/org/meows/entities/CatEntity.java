package org.meows.entities;

import lombok.*;
import org.meows.models.utils.Color;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cats")
public class CatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private LocalDate birthDate;

    @Getter
    @Setter
    private String breed;

    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owners")
    @Getter
    @Setter
    private OwnerEntity owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    @Getter
    @Setter
    private List<CatEntity> friends = new ArrayList<>();

    public CatEntity() {}

    public CatEntity(String name, LocalDate birthDate, String breed, Color color) {

        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
