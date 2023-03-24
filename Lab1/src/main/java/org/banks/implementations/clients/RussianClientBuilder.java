package org.banks.implementations.clients;

import org.banks.contracts.clients.ClientId;
import org.jetbrains.annotations.Nullable;

/**
 * Represents russian client account builder
 */
public class RussianClientBuilder {
    private String firstName;
    private String secondName;
    private @Nullable String address = null;
    private @Nullable RussianClientId id = null;

    /**
     * Sets firstname for a client
     *
     * @param firstName firstname to be set
     * @return Builder object to chain method calls
     */
    public RussianClientBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Sets secondname for a client
     *
     * @param secondName secondname to be set
     * @return Builder object to chain method calls
     */
    public RussianClientBuilder setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    /**
     * Sets address for a client
     *
     * @param address address to be set
     * @return Builder object to chain method calls
     * @see org.jetbrains.annotations.Nullable
     */
    public RussianClientBuilder setAddress(@Nullable String address) {
        this.address = address;
        return this;
    }

    /**
     * Sets id for a client
     *
     * @param id id to be set
     * @return Builder object to chain method calls
     * @see org.jetbrains.annotations.Nullable
     */
    public RussianClientBuilder setId(@Nullable RussianClientId id) {
        this.id = id;
        return this;
    }

    /**
     * @return Valid russian client
     */
    public RussianClient createRussianClient() {
        return new RussianClient(firstName, secondName, address, id);
    }
}