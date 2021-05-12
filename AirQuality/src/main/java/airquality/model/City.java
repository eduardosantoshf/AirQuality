package airquality.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String countryCode;
    private Double latitude, longitude;
    private Integer aqi; // Air Quality Index [US - EPA standard 0 - +500]
    private Double co, o3, so2, no2; // Concentration of carbon monoxide (µg/m³)
    private String predominantPollenType; // Trees / Weeds / Molds / Grasses

    /*---------- Constructors ----------*/

    public City() {

    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, String countryCode, Double latitude, Double longitude, Integer aqi, Double co, Double o3, Double so2, Double no2, String predominantPollenType) {
        this.name = name;
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.aqi = aqi;
        this.co = co;
        this.o3 = o3;
        this.so2 = so2;
        this.no2 = no2;
        this.predominantPollenType = predominantPollenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getO3() {
        return o3;
    }

    public void setO3(Double o3) {
        this.o3 = o3;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public String getPredominantPollenType() {
        return predominantPollenType;
    }

    public void setPredominantPollenType(String predominantPollenType) {
        this.predominantPollenType = predominantPollenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(countryCode, city.countryCode) && Objects.equals(latitude, city.latitude) && Objects.equals(longitude, city.longitude) && Objects.equals(aqi, city.aqi) && Objects.equals(co, city.co) && Objects.equals(o3, city.o3) && Objects.equals(so2, city.so2) && Objects.equals(no2, city.no2) && Objects.equals(predominantPollenType, city.predominantPollenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryCode, latitude, longitude, aqi, co, o3, so2, no2, predominantPollenType);
    }
}
