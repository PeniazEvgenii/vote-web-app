package by.it_academy.jd2.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private final List<Err> errors = new ArrayList<>();

    public void addError(Err error) {
        errors.add(error);
    }

    public List<Err> getErrors() {
        return errors;
    }

    public boolean checkErrorEmpty() {
        return errors.isEmpty();
    }
}
