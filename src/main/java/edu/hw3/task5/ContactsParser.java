package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactsParser {

    private static final Comparator<Contact> ASCENDING_CONTACT_COMPARATOR = (contact1, contact2) -> {
        String contact1Name = contact1.getSurname() + contact1.getName();
        String contact2Name = contact2.getSurname() + contact2.getName();
        return contact1Name.compareTo(contact2Name);
    };

    private static final Comparator<Contact> DESCENDING_CONTACT_COMPARATOR = ASCENDING_CONTACT_COMPARATOR.reversed();

    private ContactsParser() {

    }

    public static List<Contact> parse(List<Contact> contacts, SortOrder order) {
        if (contacts == null) {
            return new ArrayList<>();
        } else {
            List<Contact> sortedContacts = new ArrayList<>(contacts);
            if (order == SortOrder.ASC) {
                sortedContacts.sort(ASCENDING_CONTACT_COMPARATOR);
            } else if (order == SortOrder.DESC) {
                sortedContacts.sort(DESCENDING_CONTACT_COMPARATOR);
            }
            return sortedContacts;
        }
    }
}
