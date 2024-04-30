package io.personalprojects.sked.exceptions;

import io.personalprojects.sked.errors.ErrorMessage;

public class EstablishmentNotFoundException extends SkedException {
    /**
     * @see Exception#Exception(String)
     */
    public EstablishmentNotFoundException() {
        super(ErrorMessage.ESTABLISHMENT_NOT_FOUND);
    }
}
