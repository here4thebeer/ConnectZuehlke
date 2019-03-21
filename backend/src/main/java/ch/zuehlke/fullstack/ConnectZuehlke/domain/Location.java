package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.io.Serializable;
import java.util.StringJoiner;

public class Location implements Serializable {

    private String street;
    private String city;
    private int zip;
    private String country;
    private float longitude;
    private float latitude;

    public Location(String street, String city, int zip, String country, float longitude, float latitude) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Location.class.getSimpleName() + "[", "]")
                .add("street='" + street + "'")
                .add("city='" + city + "'")
                .add("zip=" + zip)
                .add("country='" + country + "'")
                .add("longitude=" + longitude)
                .add("latitude=" + latitude)
                .toString();
    }
}