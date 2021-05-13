package airquality.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String countryCode;
    private Double latitude;
    private Double longitude;
    private Integer aqi; // Air Quality Index [US - EPA standard 0 - +500]
    private Double co;
    private Double o3;
    private Double so2;
    private Double no2;
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

    public Integer getAqi() {
        return aqi;
    }

    public Double getCo() {
        return co;
    }

    public Double getO3() {
        return o3;
    }

    public Double getSo2() {
        return so2;
    }

    public Double getNo2() {
        return no2;
    }

    public String getPredominantPollenType() {
        return predominantPollenType;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", aqi=" + aqi +
                ", co=" + co +
                ", o3=" + o3 +
                ", so2=" + so2 +
                ", no2=" + no2 +
                ", predominantPollenType='" + predominantPollenType + '\'' +
                '}';
    }
}
