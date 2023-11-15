package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import static edu.hw4.Animal.Sex;
import static edu.hw4.Animal.Type;

public class AnimalTaskSolver {

    private AnimalTaskSolver() {
    }

    private static final int HEIGHT = 100;
    public static List<Animal> sortAnimalsByHeight(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortAnimalsByWeight(@NotNull List<Animal> animals, long k) {
        if (k <= 0) {
            return animals;
        }
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Type, Integer> countAnimalsBySpecies(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(a -> 1)));
    }

    public static Animal findAnimalWithLongestName(@NotNull List<Animal> animals) {
        Optional<Animal> maxNameLengthAnimal = animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()));
        return maxNameLengthAnimal.orElse(null);
    }

    public static Sex findMoreMalesOrFemales(@NotNull List<Animal> animals) {
        Map<Sex, Long> sexCountMap = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        long femalesCount = sexCountMap.getOrDefault(Sex.F, 0L);
        long malesCount = sexCountMap.getOrDefault(Sex.M, 0L);
        if (malesCount == femalesCount) {
            return null;
        } else if (femalesCount < malesCount) {
            return Sex.M;
        } else {
            return Sex.F;
        }
    }

    public static Map<Type, Animal> findHeaviestAnimalOfEachSpecies(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))));
    }

    public static Animal findKthOldestAnimal(@NotNull List<Animal> animals, int k) {
        if (k <= 0 || k > animals.size()) {
            return null;
        } else {
            Optional<Animal> oldestAnimal = animals.stream()
                .sorted(Comparator.comparingInt(Animal::age).reversed())
                .skip(k-1)
                .findFirst();
            return oldestAnimal.orElse(null);
        }
    }

    public static Optional<Animal> findHeaviestAnimalBelowHeight(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .filter((animal -> animal.height() < k))
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer calculateTotalPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.summingInt(Animal::paws));
    }

    public static List<Animal> findAnimalsWithAgeMismatchPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter((animal -> animal.age() != animal.paws()))
            .toList();
    }

    public static List<Animal> findBitingAnimalsWithHeightAbove(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> (animal.bites() && animal.height() > HEIGHT))
            .toList();
    }

    public static Integer countAnimalsWithWeightMoreThanHeight(@NotNull List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> (animal.weight() > animal.height()))
            .count());
    }

    public static List<Animal> findAnimalsWithMoreThanTwoWordNames(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> {
                String[] words = animal.name().split("\\s+");
                return words.length > 2;
            })
            .toList();
    }

    public static Boolean isDogTallerThan(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Type, Integer> calculateTotalWeightBySpeciesAndAge(@NotNull List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter((animal -> animal.age() >= k && animal.age() <= l))
            .collect(Collectors.toMap(Animal::type, Animal::weight, Integer::sum));
    }

    public static List<Animal> sortAnimalsByTypeSexName(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static Boolean isSpidersBiteMoreThanDogs(@NotNull List<Animal> animals) {
        Map<Animal.Type, Long> biteTypeCount = animals.stream()
            .filter(animal -> (animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER))
            .filter((Animal::bites))
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
        Long spiderBytes = biteTypeCount.getOrDefault(Animal.Type.SPIDER, 0L);
        Long dogBytes = biteTypeCount.getOrDefault(Animal.Type.DOG, 0L);
        if (spiderBytes != 0L || dogBytes != 0L) {
            return spiderBytes > dogBytes;
        } else {
            return false;
        }
    }

    public static Animal findHeaviestFish(@NotNull List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(List::stream)
            .filter(animal1 -> animal1.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> findAnimalsWithRecordErrors(@NotNull List<Animal> animals) {
        Map<String, Set<ValidationError>> validationErrors = animals.stream()
            .collect(Collectors.toMap(Animal::name, Validator::validateErrors, (name1, name2) -> name2));
        return validationErrors.entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> findAnimalsWithRecordErrorsInStringFormat(@NotNull List<Animal> animals) {
        Map<String, String> validationErrors = animals.stream()
            .collect(Collectors.toMap(Animal::name, (Validator::validateWithStringErrors), (name1, name2) -> name2));
        return validationErrors.entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
