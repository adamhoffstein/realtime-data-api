package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestWithDetails {
    @JsonProperty("start_waypoint")
    private Waypoint startWaypoint;
    @JsonProperty("end_waypoint")
    private Waypoint endWaypoint;
    @JsonProperty("order_request")
    private OrderRequest orderRequest;
    @JsonProperty("details")
    private OrderRequestDetail orderRequestDetail;

    public static OrderRequestWithDetails of(OrderRequest orderRequest) {
        OrderRequestWithDetails orderRequestWithDetails = new OrderRequestWithDetails();
        orderRequestWithDetails.setOrderRequest(orderRequest);
        return orderRequestWithDetails;
    }

    public OrderRequestDetail getOrderRequestDetail() {
        return orderRequestDetail;
    }

    public void setOrderRequestDetail(OrderRequestDetail orderRequestDetail) {
        this.orderRequestDetail = orderRequestDetail;
    }

    public Waypoint getEndWaypoint() {
        return endWaypoint;
    }

    public void setEndWaypoint(Waypoint endWaypoint) {
        this.endWaypoint = endWaypoint;
    }

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public Waypoint getStartWaypoint() {
        return startWaypoint;
    }

    public void setStartWaypoint(Waypoint startWaypoint) {
        this.startWaypoint = startWaypoint;
    }
}
