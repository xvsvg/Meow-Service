package org.myaukalki.domain.implementations;

import lombok.Getter;
import lombok.Setter;
import org.myaukalki.domain.contracts.*;
import org.myaukalki.domain.utils.Color;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

@Entity
@Table(name = "Pets")
public class CatImpl extends Pet {

    @Column(name = "Color")
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private final Color color;

    @Column(name = "Breed")
    @Getter
    @Setter
    private final String breed;

    @ManyToMany(targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "Pets")
    @Getter
    @Setter
    private final LinkedList<Pet> friends;

    protected CatImpl() {
        super();
        color = Color.none;
        breed = "";
        friends = new LinkedList<>();
    }

    public CatImpl(String name,
                   LocalDate birthDate,
                   Owner owner,
                   Color color,
                   String breed) {
        super(name, birthDate, owner);
        this.color = color;
        this.breed = breed;
        friends = new LinkedList<>();
    }

    public CatImpl(String name,
                   LocalDate birthDate,
                   Owner owner,
                   Color color,
                   String breed,
                   Collection<Pet> friends) {

        super(name, birthDate, owner);


        this.color = color;
        this.breed = breed;
        this.friends = new LinkedList<>(friends);
    }
}
