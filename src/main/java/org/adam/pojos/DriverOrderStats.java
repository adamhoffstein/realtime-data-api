package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class DriverOrderStats {

    @JsonProperty("driver_id")
    private Integer driverId;
    private ArrayList<OrderRequestSmall> interactedOrders;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public ArrayList<OrderRequestSmall> getInteractedOrders() {
        return interactedOrders;
    }

    public void setInteractedOrders(ArrayList<OrderRequestSmall> interactedOrders) {
        this.interactedOrders = interactedOrders;
    }
}
