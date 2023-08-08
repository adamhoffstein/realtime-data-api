package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.adam.enums.UserType;

import java.time.Instant;

public class OrderRequestSmall {
    private Integer id;

    @JsonProperty("event_meta")
    private OrderRequestEventMeta orderRequestEventMeta;

    @JsonProperty("event_time")
    private Instant eventTime;

    @JsonProperty("event_actor_type")
    private UserType eventActorType;
    @JsonProperty("event_name")
    private String eventName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderRequestEventMeta getOrderRequestEventMeta() {
        return orderRequestEventMeta;
    }

    public void setOrderRequestEventMeta(OrderRequestEventMeta orderRequestEventMeta) {
        this.orderRequestEventMeta = orderRequestEventMeta;
    }

    public Instant getEventTime() {
        return eventTime;
    }

    public void setEventTime(Instant eventTime) {
        this.eventTime = eventTime;
    }

    public UserType getEventActorType() {
        return eventActorType;
    }

    public void setEventActorType(UserType eventActorType) {
        this.eventActorType = eventActorType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
