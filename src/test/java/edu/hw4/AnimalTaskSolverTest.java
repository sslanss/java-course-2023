package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex;
import static edu.hw4.Animal.Type;

public class AnimalTaskSolverTest {

    private List<Animal> animals;

    @BeforeEach
    public void setUpList() {
        animals = new ArrayList<>(List.of(
            new Animal("Lilus", Type.CAT, Sex.M, 3, 40, 5, false),
            new Animal("Archie", Type.DOG, Sex.F, 4, 80, 15, true),
            new Animal("Jerry", Type.BIRD, Sex.M, 2, 10, 1, false)
        ));
    }

    @Test
    public void testSortAnimalsListByHeight() {
        List<Animal> result = AnimalTaskSolver.sortAnimalsByHeight(animals);

        Assertions.assertThat(result.get(0).height()).isEqualTo(10);
        Assertions.assertThat(result.get(1).height()).isEqualTo(40);
        Assertions.assertThat(result.get(2).height()).isEqualTo(80);
    }

    @Test
    public void testSortAnimalsListByWeight() {
        List<Animal> result = AnimalTaskSolver.sortAnimalsByWeight(animals, 2);

        Assertions.assertThat(result.get(0).weight()).isEqualTo(15);
        Assertions.assertThat(result.get(1).weight()).isEqualTo(5);

        result = AnimalTaskSolver.sortAnimalsByWeight(animals, 0);

        Assertions.assertThat(result).isEqualTo(animals);

        result = AnimalTaskSolver.sortAnimalsByWeight(animals, 4);

        Assertions.assertThat(result.get(0).weight()).isEqualTo(15);
        Assertions.assertThat(result.get(1).weight()).isEqualTo(5);
        Assertions.assertThat(result.get(2).weight()).isEqualTo(1);
    }

    @Test
    public void testCountAnimalsListBySpecies() {
        animals.add(new Animal("Bimbo", Type.CAT, Sex.M, 3, 40, 5, false));
        animals.add(new Animal("Rocky", Type.DOG, Sex.M, 3, 140, 15, false));
        animals.add(new Animal("Mimi", Type.CAT, Sex.M, 3, 40, 5, false));

        Map<Type, Integer> result = AnimalTaskSolver.countAnimalsBySpecies(animals);

        Assertions.assertThat(result.get(Type.CAT)).isEqualTo(3);
        Assertions.assertThat(result.get(Type.DOG)).isEqualTo(2);
        Assertions.assertThat(result.get(Type.BIRD)).isEqualTo(1);
    }

    @Test
    public void testFindAnimalWithLongestName() {
        Animal result = AnimalTaskSolver.findAnimalWithLongestName(animals);
        Assertions.assertThat(result.name()).isEqualTo("Archie");

        animals.remove(1);
        result = AnimalTaskSolver.findAnimalWithLongestName(animals);
        Assertions.assertThat(result.name()).isEqualTo("Lilus");

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findAnimalWithLongestName(animals);
        Assertions.assertThat(result).isNull();
    }

    @Test
    public void testFindMoreMalesOrFemales() {
        Sex result = AnimalTaskSolver.findMoreMalesOrFemales(animals);
        Assertions.assertThat(result).isEqualTo(Sex.M);

        animals.remove(0);
        result = AnimalTaskSolver.findMoreMalesOrFemales(animals);
        Assertions.assertThat(result).isNull();

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findMoreMalesOrFemales(animals);
        Assertions.assertThat(result).isNull();
    }

    @Test
    public void testFindHeaviestAnimalOfEachSpecies() {
        animals.add(new Animal("Bimbo", Type.CAT, Sex.M, 8, 40, 8, false));
        animals.add(new Animal("Rocky", Type.DOG, Sex.M, 1, 140, 5, false));
        animals.add(new Animal("Mimi", Type.DOG, Sex.M, 9, 90, 15, false));

        Map<Type, Animal> result = AnimalTaskSolver.findHeaviestAnimalOfEachSpecies(animals);

        Assertions.assertThat(result.get(Type.CAT)).isEqualTo(
            new Animal("Bimbo", Type.CAT, Sex.M, 8, 40, 8, false));
        Assertions.assertThat(result.get(Type.DOG)).isEqualTo(new Animal("Archie", Type.DOG, Sex.F, 4, 80, 15, true));
        Assertions.assertThat(result.get(Type.BIRD)).isEqualTo(
            new Animal("Jerry", Type.BIRD, Sex.M, 2, 10, 1, false));

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findHeaviestAnimalOfEachSpecies(animals);
        Assertions.assertThat(result).isEqualTo(new HashMap<>());
    }

    @Test
    public void testFindKthOldestAnimal() {
        Animal result = AnimalTaskSolver.findKthOldestAnimal(animals, -1);
        Assertions.assertThat(result).isNull();

        result = AnimalTaskSolver.findKthOldestAnimal(animals, 2);
        Assertions.assertThat(result).isEqualTo(new Animal("Lilus", Type.CAT, Sex.M, 3, 40, 5, false));

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findKthOldestAnimal(animals, 5);
        Assertions.assertThat(result).isNull();
    }

    @Test
    public void testFindHeaviestAnimalBelowHeight() {
        Optional<Animal> result = AnimalTaskSolver.findHeaviestAnimalBelowHeight(animals, 90);
        Assertions.assertThat(result.orElse(null)).isEqualTo(new Animal("Archie", Type.DOG, Sex.F, 4, 80, 15, true));

        result = AnimalTaskSolver.findHeaviestAnimalBelowHeight(animals, 5);
        Assertions.assertThat(result.orElse(null)).isNull();

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findHeaviestAnimalBelowHeight(animals, 5);
        Assertions.assertThat(result.orElse(null)).isNull();
    }

    @Test
    public void testCalculateTotalPaws() {
        int result = AnimalTaskSolver.calculateTotalPaws(animals);
        Assertions.assertThat(result).isEqualTo(10);

        animals = new ArrayList<>();
        result = AnimalTaskSolver.calculateTotalPaws(animals);
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void testFindAnimalsWithAgeMismatchPaws() {
        List<Animal> result = AnimalTaskSolver.findAnimalsWithAgeMismatchPaws(animals);
        Assertions.assertThat(result).containsExactly(
            new Animal("Lilus", Type.CAT, Sex.M, 3, 40, 5, false)
        );

        animals.remove(0);
        result = AnimalTaskSolver.findAnimalsWithAgeMismatchPaws(animals);
        Assertions.assertThat(result).isEmpty();

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findAnimalsWithAgeMismatchPaws(animals);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testFindBitingAnimalsWithHeightAbove() {
        List<Animal> result = AnimalTaskSolver.findBitingAnimalsWithHeightAbove(animals);
        Assertions.assertThat(result).isEmpty();

        animals.add(new Animal("Rex", Type.DOG, Sex.M, 6, 120, 4, true));

        result = AnimalTaskSolver.findBitingAnimalsWithHeightAbove(animals);
        Assertions.assertThat(result).containsExactly(
            new Animal("Rex", Type.DOG, Sex.M, 6, 120, 4, true)
        );

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findBitingAnimalsWithHeightAbove(animals);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testCountAnimalsWithWeightMoreThanHeight() {
        int result = AnimalTaskSolver.countAnimalsWithWeightMoreThanHeight(animals);
        Assertions.assertThat(result).isEqualTo(0);

        animals.add(new Animal("Rex", Type.DOG, Sex.M, 6, 30, 31, true));

        result = AnimalTaskSolver.countAnimalsWithWeightMoreThanHeight(animals);
        Assertions.assertThat(result).isEqualTo(1);

        animals = new ArrayList<>();
        result = AnimalTaskSolver.countAnimalsWithWeightMoreThanHeight(animals);
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void testFindAnimalsWithMoreThanTwoWordNames() {
        List<Animal> result = AnimalTaskSolver.findAnimalsWithMoreThanTwoWordNames(animals);
        Assertions.assertThat(result).isEmpty();

        animals.add(new Animal("Mr. Little Mimy", Type.CAT, Sex.M, 4, 8, 4, false));

        result = AnimalTaskSolver.findAnimalsWithMoreThanTwoWordNames(animals);
        Assertions.assertThat(result).containsExactly(new Animal("Mr. Little Mimy", Type.CAT, Sex.M, 4, 8, 4, false));

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findAnimalsWithMoreThanTwoWordNames(animals);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testIsDogTallerThan() {
        boolean result = AnimalTaskSolver.isDogTallerThan(animals, 90);
        Assertions.assertThat(result).isFalse();

        result = AnimalTaskSolver.isDogTallerThan(animals, 70);
        Assertions.assertThat(result).isTrue();

        animals = new ArrayList<>();
        result = AnimalTaskSolver.isDogTallerThan(animals, 70);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void testCalculateTotalWeightBySpeciesAndAge() {
        animals.add(new Animal("Mimy", Type.CAT, Sex.M, 4, 8, 4, false));
        animals.add(new Animal("Rex", Type.DOG, Sex.M, 3, 130, 31, true));
        animals.add(new Animal("Bimbo", Type.CAT, Sex.M, 6, 40, 8, false));
        animals.add(new Animal("Billy", Type.SPIDER, Sex.M, 3, 40, 1, false));

        Map<Type, Integer> result = AnimalTaskSolver.calculateTotalWeightBySpeciesAndAge(animals, 1, 4);
        Assertions.assertThat(result).isEqualTo(new HashMap<>() {{
            put(Type.CAT, 9);
            put(Type.DOG, 46);
            put(Type.BIRD, 1);
            put(Type.SPIDER, 1);
        }});

        result = AnimalTaskSolver.calculateTotalWeightBySpeciesAndAge(animals, 8, 10);
        Assertions.assertThat(result).isEmpty();

        animals = new ArrayList<>();
        result = AnimalTaskSolver.calculateTotalWeightBySpeciesAndAge(animals, 1, 4);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testSortAnimalsByTypeSexName() {
        animals.add(new Animal("Lily", Type.CAT, Sex.M, 3, 40, 5, false));
        List<Animal> result = AnimalTaskSolver.sortAnimalsByTypeSexName(animals);
        Assertions.assertThat(result).containsExactly(
            new Animal("Lilus", Type.CAT, Sex.M, 3, 40, 5, false),
            new Animal("Lily", Type.CAT, Sex.M, 3, 40, 5, false),
            new Animal("Archie", Type.DOG, Sex.F, 4, 80, 15, true),
            new Animal("Jerry", Type.BIRD, Sex.M, 2, 10, 1, false)
        );

        animals = new ArrayList<>();
        result = AnimalTaskSolver.sortAnimalsByTypeSexName(animals);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testIsSpidersBiteMoreThanDogs() {
        boolean result = AnimalTaskSolver.isSpidersBiteMoreThanDogs(animals);
        Assertions.assertThat(result).isFalse();

        animals.add(new Animal("Billy", Type.SPIDER, Sex.M, 3, 40, 1, true));
        result = AnimalTaskSolver.isSpidersBiteMoreThanDogs(animals);
        Assertions.assertThat(result).isFalse();

        animals.add(new Animal("Bill", Type.SPIDER, Sex.M, 3, 40, 1, true));
        result = AnimalTaskSolver.isSpidersBiteMoreThanDogs(animals);
        Assertions.assertThat(result).isTrue();

        animals = new ArrayList<>();
        result = AnimalTaskSolver.isSpidersBiteMoreThanDogs(animals);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void testFindHeaviestFish() {
        List<List<Animal>> animalLists = new ArrayList<>();
        animalLists.add(animals);
        animalLists.add(new ArrayList<>(List.of(
            new Animal("Lilus", Type.CAT, Sex.M, 3, 40, 5, false),
            new Animal("Nemo", Type.FISH, Sex.M, 1, 2, 0, false),
            new Animal("Archie", Type.FISH, Sex.F, 4, 80, 2, true)
        )));

        Animal result = AnimalTaskSolver.findHeaviestFish(animalLists);
        Assertions.assertThat(result).isEqualTo(new Animal("Archie", Type.FISH, Sex.F, 4, 80, 2, true));

        animalLists.remove(animals);
        animals.add(new Animal("Archil", Type.FISH, Sex.F, 4, 80, 3, false));
        animalLists.add(animals);
        result = AnimalTaskSolver.findHeaviestFish(animalLists);
        Assertions.assertThat(result).isEqualTo(new Animal("Archil", Type.FISH, Sex.F, 4, 80, 3, false));

        animals = new ArrayList<>();
        animalLists.add(animals);
        result = AnimalTaskSolver.findHeaviestFish(animalLists);
        Assertions.assertThat(result).isEqualTo(new Animal("Archil", Type.FISH, Sex.F, 4, 80, 3, false));

        animalLists = new ArrayList<>() {{
            add(new ArrayList<>());
        }};
        result = AnimalTaskSolver.findHeaviestFish(animalLists);
        Assertions.assertThat(result).isNull();
    }

    @Test
    public void testFindAnimalsWithRecordErrors() {
        Map<String, Set<ValidationError>> result = AnimalTaskSolver.findAnimalsWithRecordErrors(animals);
        Assertions.assertThat(result).isEmpty();

        animals.add(new Animal("Nemo", Type.FISH, Sex.F, 4, 10080, 2223, false));
        animals.add(new Animal("", Type.FISH, null, 104, 80, 3, false));
        result = AnimalTaskSolver.findAnimalsWithRecordErrors(animals);
        Assertions.assertThat(result).isEqualTo(new HashMap<>() {{
            put("Nemo", new HashSet<>() {{
                add(new ValidationError("The animal has invalid weight.", "weight"));
                add(new ValidationError("The animal has invalid height.", "height"));
            }});
            put("", new HashSet<>() {{
                add(new ValidationError("Empty sex-field.", "sex"));
                add(new ValidationError("Empty name.", "name"));
                add(new ValidationError("The animal has invalid weight.", "weight"));
                add(new ValidationError("The animal has invalid height.", "height"));
                add(new ValidationError("The animal has invalid age.", "age"));
            }});
        }});

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findAnimalsWithRecordErrors(animals);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testFindAnimalsWithRecordErrorsInStringFormat() {
        Map<String, String> result = AnimalTaskSolver.findAnimalsWithRecordErrorsInStringFormat(animals);
        Assertions.assertThat(result).isEmpty();

        animals.add(new Animal("Nemo", Type.FISH, Sex.F, 4, 10080, 2223, false));
        animals.add(new Animal("", Type.FISH, null, 104, 80, 3, false));
        result = AnimalTaskSolver.findAnimalsWithRecordErrorsInStringFormat(animals);
        Assertions.assertThat(result).isEqualTo(new HashMap<>() {{
            put("Nemo", "height weight ");
            put("", "age height name sex weight ");
        }});

        animals = new ArrayList<>();
        result = AnimalTaskSolver.findAnimalsWithRecordErrorsInStringFormat(animals);
        Assertions.assertThat(result).isEmpty();
    }
}
