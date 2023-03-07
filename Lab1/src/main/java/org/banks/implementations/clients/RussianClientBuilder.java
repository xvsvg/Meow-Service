package org.banks.implementations.clients;

import org.banks.contracts.clients.ClientId;
import org.jetbrains.annotations.Nullable;

public class RussianClientBuilder {
    private String firstName;
    private String secondName;
    private @Nullable String address = null;
    private @Nullable RussianClientId id = null;

    public RussianClientBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RussianClientBuilder setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public RussianClientBuilder setAddress(@Nullable String address) {
        this.address = address;
        return this;
    }

    public RussianClientBuilder setId(@Nullable RussianClientId id) {
        this.id = id;
        return this;
    }

    public RussianClient createRussianClient() {
        return new RussianClient(firstName, secondName, address, id);
    }
}