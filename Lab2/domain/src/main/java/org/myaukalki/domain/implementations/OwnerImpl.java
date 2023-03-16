package org.myaukalki.domain.implementations;

import org.myaukalki.domain.contracts.Owner;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Owners")
public class OwnerImpl extends Owner {

    protected OwnerImpl() {}

    public OwnerImpl(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}
