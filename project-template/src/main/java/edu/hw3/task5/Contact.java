package edu.hw3.task5;

public class Contact {

    private String name;

    private String surname;

    public Contact(String string) {
        parseString(string);
    }

    public void parseString(String string){
        String[] fullname = string.split(" ");
        if (fullname.length == 2) {
            name = fullname[0];
            surname = fullname[1];
        } else if (fullname.length == 1) {
            name = fullname[0];
            surname = "";
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
