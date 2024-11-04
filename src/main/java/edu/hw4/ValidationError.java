package edu.hw4;

import static edu.hw4.Animal.Sex;
import static edu.hw4.Animal.Type;

public class ValidationError {
    final private String error;
    final private String field;

    ValidationError(String error, String field) {
        this.error = error;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static ValidationError checkName(String name) {
        if (name == null || name.isEmpty()) {
            return new ValidationError("Empty name.", "name");
        } else {
            String regex = "^[A-Z][a-z]+( [A-Z][a-z]+)*$";
            if (!name.matches(regex)) {
                return new ValidationError("Incorrect name format.", "name");
            } else {
                return null;
            }
        }
    }

    public static ValidationError checkSex(Sex sex) {
        if (sex == null) {
            return new ValidationError("Empty sex-field.", "sex");
        } else {
            return null;
        }
    }

    public static ValidationError checkType(Type type) {
        if (type == null) {
            return new ValidationError("Empty type-field.", "type");
        } else {
            return null;
        }
    }

    public static ValidationError checkBites(boolean bites, Animal.Type type) {
        if (type == Animal.Type.FISH && bites) {
            return new ValidationError("The animal can't bites.", "bites");
        } else {
            return null;
        }
    }

    public static ValidationError checkAge(int age, Animal.Type type) {
        switch (type) {
            case CAT, DOG -> {
                if (age < 0 || age > 20) {
                    return new ValidationError("The animal has invalid age.", "age");
                }
            }
            case BIRD -> {
                if (age < 0 || age > 25) {
                    return new ValidationError("The animal has invalid age.", "age");
                }
            }
            case FISH -> {
                if (age < 0 || age > 7) {
                    return new ValidationError("The animal has invalid age.", "age");
                }
            }
            case SPIDER -> {
                if (age < 0 || age > 30) {
                    return new ValidationError("The animal has invalid age.", "age");
                }
            }
            default -> {
                return new ValidationError("The animal has invalid age.", "age");
            }
        }
        return null;
    }

    public static ValidationError checkWeight(double weight, Animal.Type type) {
        switch (type) {
            case CAT -> {
                if (weight < 0 || weight > 10) {
                    return new ValidationError("The animal has invalid weight.", "weight");
                }
            }
            case DOG -> {
                if (weight < 0 || weight > 50) {
                    return new ValidationError("The animal has invalid weight.", "weight");
                }
            }
            case BIRD -> {
                if (weight < 0 || weight > 5) {
                    return new ValidationError("The animal has invalid weight.", "weight");
                }
            }
            case FISH, SPIDER -> {
                if (weight < 0 || weight > 1) {
                    return new ValidationError("The animal has invalid weight.", "weight");
                }
            }
            default -> {
                return new ValidationError("The animal has invalid weight.", "weight");
            }
        }
        return null;
    }

    public static ValidationError checkHeight(int size, Animal.Type type) {
        switch (type) {
            case CAT -> {
                if (size < 5 || size > 80) {
                    return new ValidationError("The animal has invalid height.", "height");
                }
            }
            case DOG -> {
                if (size < 20 || size > 100) {
                    return new ValidationError("The animal has invalid height.", "height");
                }
            }
            case BIRD -> {
                if (size < 5 || size > 30) {
                    return new ValidationError("The animal has invalid height.", "height");
                }
            }
            case FISH -> {
                if (size < 1 || size > 50) {
                    return new ValidationError("The animal has invalid height.", "height");
                }
            }
            case SPIDER -> {
                if (size < 1 || size > 10) {
                    return new ValidationError("The animal has invalid height.", "height");
                }
            }
            default -> {
                return new ValidationError("The animal has invalid height.", "height");
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationError that = (ValidationError) o;
        return error.equals(that.error) && field.equals(that.field);
    }

    @Override
    public int hashCode() {
        int result = error != null ? error.hashCode() : 0;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        return result;
    }
}
