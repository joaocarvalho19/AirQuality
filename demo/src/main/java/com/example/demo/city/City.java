package com.example.demo.city;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "city")
public class City {

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 20)
    private String name;

    private int co; // monoxido de carbono

    private int o3; // ozone

    private int no2; // dioxido de azoto

    private int p; // atmospheric pressure in hPa

    private int pm25; // particulas inalaveis %

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}