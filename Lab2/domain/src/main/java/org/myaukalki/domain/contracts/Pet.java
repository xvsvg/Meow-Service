package org.myaukalki.domain.contracts;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pet")
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "pet_name")
    @Getter
    @Setter
    private String name;

    @Column(name = "pet_birth_date")
    @Getter
    @Setter
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
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
