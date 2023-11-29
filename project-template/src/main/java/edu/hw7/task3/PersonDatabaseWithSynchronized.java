package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabaseWithSynchronized implements PersonDatabase {

    private final Map<Integer, Person> peopleById;
    private final Map<String, List<Person>> peopleByName;
    private final Map<String, List<Person>> peopleByAddress;
    private final Map<String, List<Person>> peopleByPhone;

    private final Object lockObject;

    public PersonDatabaseWithSynchronized() {
        peopleById = new HashMap<>();
        peopleByName = new HashMap<>();
        peopleByAddress = new HashMap<>();
        peopleByPhone = new HashMap<>();
        lockObject = new Object();
    }

    private boolean isPersonValid(Person person) {
        return person.name() != null && person.address() != null && person.phoneNumber() != null;
    }

    private boolean isPeopleByIdContainsId(int id) {
        return peopleById.get(id) != null;
    }

    private void addPersonToMap(Map<String, List<Person>> map, String personField, Person person) {
        if (map.putIfAbsent(personField, new ArrayList<>() {{
            add(person);
        }}) != null) {
            map.get(personField).add(person);
        }
    }

    private void safelyAdd(Person person) {
        peopleById.put(person.id(), person);
        addPersonToMap(peopleByName, person.name(), person);
        addPersonToMap(peopleByAddress, person.address(), person);
        addPersonToMap(peopleByPhone, person.phoneNumber(), person);
    }

    @Override
    public void add(Person person) {
        if (isPersonValid(person)) {
            synchronized (lockObject) {
                if (!isPeopleByIdContainsId(person.id())) {
                    safelyAdd(person);
                }
            }
        }
    }

    private void deletePersonFromMap(Map<String, List<Person>> map, String personField, Person person) {
        if (map.get(personField).size() == 1) {
            map.remove(personField);
        } else {
            map.get(personField).remove(person);
        }
    }

    private void deleteSafely(Person person) {
        deletePersonFromMap(peopleByName, person.name(), person);
        deletePersonFromMap(peopleByAddress, person.address(), person);
        deletePersonFromMap(peopleByPhone, person.phoneNumber(), person);
    }

    @Override
    public void delete(int id) {
        synchronized (lockObject) {
            Person deletedPerson = peopleById.remove(id);
            if (deletedPerson != null) {
                deleteSafely(deletedPerson);
            }
        }
    }

    @Override
    public synchronized List<Person> getRecords() {
        return new ArrayList<>(peopleById.values());
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return peopleByName.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return peopleByAddress.get(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return peopleByPhone.get(phone);
    }
}
