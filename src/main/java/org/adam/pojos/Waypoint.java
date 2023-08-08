package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Waypoint extends TableObject {

    private Integer id;

    private Integer arrangement;

    private String name;

    private String address;

    private Map<String, Double> coords;

    public Map<String, Double> getCoords() {
        return coords;
    }

    public void setCoords(Map<String, Double> coords) {
        this.coords = coords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArrangement() {
        return arrangement;
    }

    public void setArrangement(Integer arrangement) {
        this.arrangement = arrangement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}