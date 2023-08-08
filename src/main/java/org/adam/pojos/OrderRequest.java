package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.adam.enums.OrderRequestStatus;
import org.adam.enums.VehicleType;

import java.util.Date;

public class OrderRequest extends TableObject {

    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("organization_id")
    private Integer organizationId;
    @JsonProperty("status_cd")
    private Integer statusCd;
    @JsonProperty("vehicle_cd")
    private Integer vehicleCd;
    private Float price;
    private Float bonus;
    private String uuid;
    private OrderRequestStatus status;
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @JsonProperty("pickup_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pickupTime;

    public OrderRequestStatus getStatus() {
        return status;
    }

    public void setStatus(OrderRequestStatus status) {
        this.status = status;
    }

    @JsonProperty("status")
    public OrderRequestStatus unpackStatus() {
        return switch (getStatusCd()) {
            case 1 -> OrderRequestStatus.PENDING;
            case 2 -> OrderRequestStatus.ACTIVE;
            case 3 -> OrderRequestStatus.COMPLETED;
            case 4 -> OrderRequestStatus.CANCELLED;
            default -> OrderRequestStatus.OTHER;
        };
    }


    @JsonProperty("vehicle")
    public VehicleType unpackVehicle() {
        return switch (getVehicleCd()) {
            case 0 -> VehicleType.VAN;
            case 1 -> VehicleType.MOTORCYCLE;
            default -> VehicleType.OTHER;
        };
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    public Integer getVehicleCd() {
        return vehicleCd;
    }

    public void setVehicleCd(Integer vehicleCd) {
        this.vehicleCd = vehicleCd;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getBonus() {
        return bonus;
    }

    public void setBonus(Float bonus) {
        this.bonus = bonus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

}
