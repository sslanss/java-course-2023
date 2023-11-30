package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseWithLocks implements PersonDatabase {
    private final Map<Integer, Person> peopleById;
    private final Map<String, List<Person>> peopleByName;
    private final Map<String, List<Person>> peopleByAddress;
    private final Map<String, List<Person>> peopleByPhone;
    private final ReadWriteLock lock;

    public PersonDatabaseWithLocks() {
        peopleById = new HashMap<>();
        peopleByName = new HashMap<>();
        peopleByAddress = new HashMap<>();
        peopleByPhone = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public synchronized List<Person> getRecords() {
        return new ArrayList<>(peopleById.values());
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
            lock.writeLock().lock();
            try {
                if (!isPeopleByIdContainsId(person.id())) {
                    safelyAdd(person);
                }
            } finally {
                lock.writeLock().unlock();
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
        lock.writeLock().lock();
        try {
            Person deletedPerson = peopleById.remove(id);
            if (deletedPerson != null) {
                deleteSafely(deletedPerson);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return peopleByName.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return peopleByAddress.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return peopleByPhone.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
