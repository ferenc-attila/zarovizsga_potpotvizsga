package bistros;

import java.util.Objects;

public class Address {

    private String street;
    private int number;

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number && street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number);
    }

    @Override
    public String toString() {
        return String.join(" ", street, Integer.toString(number));
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }
}
