package io.personalprojects.sked.exceptions;

import io.personalprojects.sked.errors.ErrorMessage;

public class ShiftNotFoundException extends SkedException {
    /**
     * @see Exception#Exception(String)
     */
    public ShiftNotFoundException() {
        super(ErrorMessage.SHIFT_NOT_FOUND);
    }
}
