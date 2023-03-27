package org.myaukalki.domain.implementations;

import lombok.Getter;
import lombok.Setter;
import org.myaukalki.domain.contracts.*;
import org.myaukalki.domain.utils.Color;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
public class CatImpl extends Pet {

    @Column(name = "color")
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private Color color;

    @Column(name = "breed")
    @Getter
    @Setter
    private String breed;

    @ManyToMany(targetEntity = Pet.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JoinTable(name = "pet_pet",
    joinColumns = @JoinColumn(name = "pet_id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Pet> friends;

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
