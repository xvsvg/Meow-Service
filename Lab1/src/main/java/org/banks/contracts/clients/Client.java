package org.banks.contracts.clients;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents bank client
 */
public abstract class Client {

    /**
     * @return Client firstname
     */
    @Getter
    private String firstName;

    /**
     * Returns client secondname
     */
    @Getter
    private String secondName;

    /**
     * Returns client address
     * <div class="setterDocs">
     *     Sets client address
     * </div>
     *
     * @param address client address
     * @see org.jetbrains.annotations.Nullable
     */
    @Getter
    @Setter
    @Nullable
    private String address;

    /**
     * Returns client id
     * <div class="setterDocs">
     *     Sets client id
     * </div>
     *
     * @param address client id
     */
    @Getter
    @Setter
    private ClientId id;

    /**
     * Returns client status
     * <div class="setterDocs">
     *     Sets client status
     * </div>
     *
     * @note Suspicious account is an account without address nor client id
     */
    @Getter
    @Setter
    private Boolean isSuspicious;
}
