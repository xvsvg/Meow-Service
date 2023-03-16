package org.myaukalki.domain.contracts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;

@Entity
public abstract class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "OwnerName", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "OwnerBirthDate", nullable = false)
    @Getter
    @Setter
    private LocalDate birthDate;

    @OneToMany(targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private LinkedList<Pet> pets;

    protected Owner() {
    }

    public Owner(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.pets = new LinkedList<>();
    }
}