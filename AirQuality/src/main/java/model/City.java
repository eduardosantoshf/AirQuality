package model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    private Long ID;

    private String name;
    private String countryCode;
    private Double latitude, longitude;

    /*---------- Constructors ----------*/

    public City() {

    }

    public City(long ID, String name, String countryCode, Double latitude, Double longitude) {
        this.ID = ID;
        this.name = name;
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*------------ Getters ------------*/

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
