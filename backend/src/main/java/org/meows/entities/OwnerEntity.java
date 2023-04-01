package org.meows.entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<CatEntity> pets = new ArrayList<>();

    public OwnerEntity(){}

    public OwnerEntity(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
