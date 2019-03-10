package ru.bgbrakhi.stream;

public class Address {
    String city;
    String street;
    int home;
    int apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object obj) {
        Address address = (Address)obj;
        return this.city.toLowerCase().equals(address.city.toLowerCase()) &&
               this.street.toLowerCase().equals(address.street.toLowerCase()) &&
               this.home == address.home &&
               this.apartment == address.apartment;
    }

    @Override
    public int hashCode() {
        return new StringBuilder().
                append(city.toLowerCase()).
                append(street.toLowerCase()).
                append(home).
                append(apartment).
                toString().
                hashCode();
    }
}