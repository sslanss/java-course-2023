package edu.hw3.task5;

public class Contact {

    private final String name;

    private final String surname;

    public Contact(String string) {
        String[] fullname = string.split(" ");
        if (fullname.length == 2) {
            this.name = fullname[0];
            this.surname = fullname[1];
        } else if (fullname.length == 1) {
            this.name = fullname[0];
            this.surname = "";
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contact other = (Contact) obj;
        return name.equals(other.name) && surname.equals(other.surname);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
