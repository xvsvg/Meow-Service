package org.myaukalki.domain.contracts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "owner")
public abstract class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "owner_name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "owner_birth_date", nullable = false)
    @Getter
    @Setter
    private LocalDate birthDate;

    @OneToMany(targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JoinTable(name = "owner_pet",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "pets_id"))
    private List<Pet> pets;

    protected Owner() {
    }

    public Owner(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.pets = new LinkedList<>();
    }
}