package by.it_academy.jd2.validation;

import java.util.List;

public class ValidFormException extends RuntimeException{
    private final List<Err> errors;

    public ValidFormException(List<Err> errors) {
        this.errors = errors;
    }

    public List<Err> getErrors() {
        return errors;
    }
}
