package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class Validator {

    private Validator() {
    }

    public static Set<ValidationError> validateErrors(Animal animal) {
        Set<ValidationError> validationErrors = new HashSet<>();
        ValidationError error = ValidationError.checkName(animal.name());
        if (error != null) {
            validationErrors.add(error);
        }
        error = ValidationError.checkType(animal.type());
        if (error != null) {
            validationErrors.add(error);
        }
        error = ValidationError.checkSex(animal.sex());
        if (error != null) {
            validationErrors.add(error);
        }
        error = ValidationError.checkAge(animal.age(), animal.type());
        if (error != null) {
            validationErrors.add(error);
        }
        error = ValidationError.checkHeight(animal.height(), animal.type());
        if (error != null) {
            validationErrors.add(error);
        }
        error = ValidationError.checkWeight(animal.weight(), animal.type());
        if (error != null) {
            validationErrors.add(error);
        }
        error = ValidationError.checkBites(animal.bites(), animal.type());
        if (error != null) {
            validationErrors.add(error);
        }
        return validationErrors;
    }

    public static String validateWithStringErrors(Animal animal) {
        Set<ValidationError> validationErrors = validateErrors(animal);
        StringBuilder result = new StringBuilder();
        for (var error : validationErrors) {
            result.append(error.getField()).append(" ");
        }
        return result.toString();
    }
}
