package io.personalprojects.sked.exceptions;

import io.personalprojects.sked.errors.ErrorMessage;

/**
 * Thrown to indicate that the employee was not found
 */
public class EmployeeNotFoundException extends SkedException {

    /**
     * @see SkedException#SkedException(String)
     */
    public EmployeeNotFoundException() {
        super(ErrorMessage.EMPLOYEE_NOT_FOUND);
    }
}
