package org.banks.implementations.clients;

import org.banks.contracts.clients.*;
import org.jetbrains.annotations.Nullable;

public class RussianClient extends Client {

    RussianClient(String firstName, String secondName, @Nullable String address, @Nullable ClientId id) {
        if (address == null || id == null)
            setIsSuspicious(true);
    }
}
