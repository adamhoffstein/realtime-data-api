package org.adam.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VehicleType {
    @JsonProperty("Van")
    VAN,
    @JsonProperty("Motorcycle")
    MOTORCYCLE,
    @JsonProperty("other")
    OTHER
}
