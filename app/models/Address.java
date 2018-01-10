package models;

public class Address {
    public String country;
    public String zipcode;
    public String street;
    public String streetNumber;
    public String addressExtra;

    public Address(String country, String zipcode, String street, String streetNumber, String addressExtra) {
        this.country = country;
        this.zipcode = zipcode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.addressExtra = addressExtra;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAddressExtra() {
        return addressExtra;
    }

    public void setAddressExtra(String addressExtra) {
        this.addressExtra = addressExtra;
    }
}
