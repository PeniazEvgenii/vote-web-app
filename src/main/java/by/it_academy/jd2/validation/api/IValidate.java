package by.it_academy.jd2.validation.api;

import by.it_academy.jd2.validation.ValidationResult;

public interface IValidate<T> {
    ValidationResult valid(T t);
}
