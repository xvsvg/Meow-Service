package org.myaukalki.domain.contracts;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "PetName")
    @Getter
    @Setter
    private String name;

    @Column(name = "PetBirthDate")
    @Getter
    @Setter
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwnerID")
    @Getter
    @Setter
    @Nullable
    private Owner owner;

    protected Pet() {
    }

    public Pet(String name, LocalDate birthDate, Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
    }
}
