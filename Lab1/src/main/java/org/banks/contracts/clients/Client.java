package org.banks.contracts.clients;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

public abstract class Client {

    @Getter
    private String firstName;

    @Getter
    private String secondName;

    @Getter
    @Setter
    @Nullable
    private String address;

    @Getter
    @Setter
    private ClientId id;

    @Getter
    @Setter
    private Boolean isSuspicious;
}
