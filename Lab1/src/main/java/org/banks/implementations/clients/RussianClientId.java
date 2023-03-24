package org.banks.implementations.clients;

import org.banks.contracts.clients.ClientId;
import org.banks.exceptions.ClientIdException;

/**
 * {@inheritDoc}
 */
public class RussianClientId extends ClientId {

    private final int idNumber;

    /**
     * Constructs russian client id instance
     * @param idNumber id number
     */
    public RussianClientId(int idNumber){

        this.idNumber = validate(idNumber);
    }

    private int validate(int idNumber) {
        if (String.valueOf(idNumber).length() < 0 || String.valueOf(idNumber).length() > 6){
            throw ClientIdException.InvalidNumber();
        }

        return idNumber;
    }
}
