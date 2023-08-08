package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class DriverDetail extends TableObject {
    private Integer driver_id;
    private Map<String, Double> coords;
    @JsonProperty("location_updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date locationUpdatedAt;

    public Map<String, Double> getCoords() {
        return coords;
    }

    public void setCoords(Map<String, Double> coords) {
        this.coords = coords;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public Date getLocationUpdatedAt() {
        return locationUpdatedAt;
    }

    public void setLocationUpdatedAt(Date locationUpdatedAt) {
        this.locationUpdatedAt = locationUpdatedAt;
    }
}
