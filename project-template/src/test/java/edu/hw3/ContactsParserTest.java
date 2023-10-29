package edu.hw3;

import edu.hw3.task2.BracesClasterizer;
import edu.hw3.task5.Contact;
import edu.hw3.task5.ContactsParser;
import edu.hw3.task5.SortOrder;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ContactsParserTest {

    @Test
    public void parseWithEmptyInputList() {
        List<Contact> contacts = new ArrayList<>();
        List<Contact> result = ContactsParser.parse(contacts, SortOrder.ASC);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void parseWithNullInputList() {
        List<Contact> result = ContactsParser.parse(null,SortOrder.ASC);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void parseWithNullSortOrder() {
        List<Contact> contacts = new ArrayList<>();
        List<Contact> result = ContactsParser.parse(contacts,null);
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    public void parseWithAscSortOrder() {
        List<Contact> contacts = new ArrayList<>(){{
            add(new Contact("John Locke"));
            add(new Contact("Thomas Aquinas"));
            add(new Contact("David Hume"));
        }};
        List<Contact> result = ContactsParser.parse(contacts,SortOrder.ASC);
        assertThat(result).isEqualTo(new ArrayList<>(){{
            add(new Contact("Thomas Aquinas"));
            add(new Contact("David Hume"));
            add(new Contact("John Locke"));
        }});
    }

    @Test
    public void parseWithDescSortOrder() {
        List<Contact> contacts = new ArrayList<>(){{
            add(new Contact("John Locke"));
            add(new Contact("Thomas Aquinas"));
            add(new Contact("David Hume"));
        }};
        List<Contact> result = ContactsParser.parse(contacts,SortOrder.DESC);
        assertThat(result).isEqualTo(new ArrayList<>(){{
            add(new Contact("John Locke"));
            add(new Contact("David Hume"));
            add(new Contact("Thomas Aquinas"));
        }});
    }

    @Test
    public void parseWithEqualSurnames() {
        List<Contact> contacts = new ArrayList<>(){{
            add(new Contact("John Locke"));
            add(new Contact("Thomas Locke"));
            add(new Contact("David Hume"));
        }};
        List<Contact> result = ContactsParser.parse(contacts,SortOrder.ASC);
        assertThat(result).isEqualTo(new ArrayList<>(){{
            add(new Contact("David Hume"));
            add(new Contact("John Locke"));
            add(new Contact("Thomas Locke"));
        }});
    }

    @Test
    public void parseWithNames() {
        List<Contact> contacts = new ArrayList<>(){{
            add(new Contact("John Locke"));
            add(new Contact("Thomas"));
            add(new Contact("David Hume"));
        }};
        List<Contact> result = ContactsParser.parse(contacts,SortOrder.ASC);
        assertThat(result).isEqualTo(new ArrayList<>(){{
            add(new Contact("David Hume"));
            add(new Contact("John Locke"));
            add(new Contact("Thomas"));
        }});
    }
}
