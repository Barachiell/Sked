package io.personalprojects.sked.exceptions;

import io.personalprojects.sked.errors.ErrorMessage;

public class AssociationExistsException extends SkedException {

    /**
     * @see SkedException#SkedException(String)
     */
    public AssociationExistsException() {
        super(ErrorMessage.ASSOCIATION_EXISTS);
    }
}
