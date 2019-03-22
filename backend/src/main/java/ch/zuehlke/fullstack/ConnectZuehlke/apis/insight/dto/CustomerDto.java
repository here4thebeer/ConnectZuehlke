package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties
@Entity
public class CustomerDto {
    @JsonProperty("Id")
    @Id
    private Integer id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Street")
    private String street;
    @JsonProperty("City")
    private String city;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Zip")
    private Integer zip;
    @JsonProperty("Longitude")
    private Double longitude;
    @JsonProperty("Latitude")
    private Double latitude;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String name, String street, String city, String country, Integer zip, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zip = zip;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getZip() {
        return zip;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Customer toCustomer() {
        return new Customer(
                getId(),
                getName(),
                getStreet(),
                getCity(),
                getCountry(),
                getZip(),
                getLongitude(),
                getLatitude()
        );
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zip=" + zip +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
