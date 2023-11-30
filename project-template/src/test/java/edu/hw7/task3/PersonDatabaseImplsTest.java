package edu.hw7.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PersonDatabaseImplsTest {
    private static ArrayList<Person> persons;

    @BeforeAll
    public static void initializePersons() {
        persons = new ArrayList<>() {{
            add(new Person(1, "Bob", "AA", "8900"));
            add(new Person(2, "Sam", "BB", "8901"));
            add(new Person(1, "Sam", "AA", "8900"));
            add(new Person(3, null, null, null));
            add(new Person(4, "Bob", "BB", "8900"));
            add(new Person(5, "Sam", "BB", "8900"));
        }};
    }

    private static Arguments[] databases() {
        return new Arguments[] {
            Arguments.of(new PersonDatabaseWithLocks()),
            Arguments.of(new PersonDatabaseWithSynchronized())
        };
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testAdd(PersonDatabase database) {
        for (var person : persons) {
            database.add(person);
        }
        Assertions.assertThat(database.getRecords()).containsExactlyInAnyOrder(persons.get(0),
            persons.get(1), persons.get(4), persons.get(5)
        );
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testDelete(PersonDatabase database) {
        for (var person : persons) {
            database.add(person);
        }
        database.delete(1);
        database.delete(2);
        Assertions.assertThat(database.getRecords()).containsExactlyInAnyOrder(
            persons.get(4),
            persons.get(5)
        );
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testFindByName(PersonDatabase database) {
        for (var person : persons) {
            database.add(person);
        }
        Assertions.assertThat(database.findByName("Bob")).containsExactlyInAnyOrder(
            persons.get(0),
            persons.get(4)
        );
        database.delete(1);
        Assertions.assertThat(database.findByName("Bob")).containsExactlyInAnyOrder(
            persons.get(4));
        database.delete(4);
        Assertions.assertThat(database.findByName("Bob")).isNull();
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testFindByAddress(PersonDatabase database) {
        for (var person : persons) {
            database.add(person);
        }
        Assertions.assertThat(database.findByAddress("BB")).containsExactlyInAnyOrder(persons.get(1),
            persons.get(4), persons.get(5)
        );
        Assertions.assertThat(database.findByAddress("AA")).containsExactlyInAnyOrder(persons.get(0));
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testFindByPhone(PersonDatabase database) {
        for (var person : persons) {
            database.add(person);
        }
        Assertions.assertThat(database.findByPhone("8901")).containsExactlyInAnyOrder(persons.get(1));
        Assertions.assertThat(database.findByAddress("8902")).isNull();
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testAddAndDeleteMethodsWithConcurrency(PersonDatabase database) throws InterruptedException {
        try (var addingThreads = Executors.newFixedThreadPool(6);
             var deletingThreads = Executors.newFixedThreadPool(6)) {
            CountDownLatch addLatch = new CountDownLatch(6);
            CountDownLatch deleteLatch = new CountDownLatch(6);
            for (int i = 0; i < persons.size(); i++) {
                int index = i;
                addingThreads.execute(() -> {
                    database.add(persons.get(index));
                    addLatch.countDown();
                });
            }
            addLatch.await();
            for (int i = 0; i < persons.size(); i++) {
                int index = i;
                deletingThreads.execute(() -> {
                    database.delete(index + 1);
                    deleteLatch.countDown();
                });
            }
            deleteLatch.await();
            Assertions.assertThat(database.getRecords()).isEmpty();
        }
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testFindMethodsWithConcurrency(PersonDatabase database) throws InterruptedException {
        try (var addingThreads = Executors.newFixedThreadPool(6)) {
            CountDownLatch addLatch = new CountDownLatch(6);
            for (int i = 0; i < persons.size(); i++) {
                int index = i;
                addingThreads.execute(() -> {
                    database.add(persons.get(index));
                    addLatch.countDown();
                });
            }
            addLatch.await();
        }
        try (var findingThreads = Executors.newFixedThreadPool(10)) {
            CountDownLatch findLatch = new CountDownLatch(100);
            for (int i = 0; i < 100; i++) {
                findingThreads.execute(() -> {
                    Assertions.assertThat(database.findByName("Bob")).containsExactlyInAnyOrder(
                        persons.get(0),
                        persons.get(4)
                    );
                    findLatch.countDown();
                });
                findingThreads.execute(() -> {
                    Assertions.assertThat(database.findByName("Sam")).containsExactlyInAnyOrder(
                        persons.get(1),
                        persons.get(5)
                    );
                    findLatch.countDown();
                });
                findingThreads.execute(() -> {
                    Assertions.assertThat(database.findByAddress("AA")).containsExactlyInAnyOrder(persons.get(0));
                    findLatch.countDown();
                });
                findingThreads.execute(() -> {
                    Assertions.assertThat(database.findByPhone("8901")).containsExactlyInAnyOrder(persons.get(1));
                    findLatch.countDown();
                });
                findingThreads.execute(() -> {
                    Assertions.assertThat(database.findByAddress("DD")).isNull();
                    findLatch.countDown();
                });
                findingThreads.execute(() -> {
                    Assertions.assertThat(database.findByPhone("8902")).isNull();
                    findLatch.countDown();
                });
            }
            findLatch.await();
        }
    }

    private boolean isDataBaseSynchronizedAfterChanges(List<Person> dataBaseRecords, List<Person> findingRecords) {
        return findingRecords == null || dataBaseRecords.containsAll(findingRecords);
    }

    @ParameterizedTest
    @MethodSource("databases")
    public void testCombinedMethodsWithConcurrency(PersonDatabase database) {
        for (var person : persons) {
            database.add(person);
        }
        try (var deletingThreads = Executors.newFixedThreadPool(10);
             var findingThreads = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 10; i++) {
                int index = i;
                deletingThreads.execute(() ->
                    database.delete(index + 1));
                findingThreads.execute(() ->
                    Assertions.assertThat(isDataBaseSynchronizedAfterChanges(
                        database.getRecords(),
                        database.findByName("Bob")
                    )).isTrue()
                );
                findingThreads.execute(() ->
                    Assertions.assertThat(isDataBaseSynchronizedAfterChanges(
                        database.getRecords(),
                        database.findByAddress("CC")
                    )).isTrue()
                );
            }
        }

    }
}
