package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class OrderRequestEventMeta {
    @JsonProperty("pickup_time")
    private Instant pickupTime;

    public Instant getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Instant pickupTime) {
        this.pickupTime = pickupTime;
    }
}
