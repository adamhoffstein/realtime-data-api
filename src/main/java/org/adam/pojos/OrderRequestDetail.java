package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequestDetail extends TableObject {
    private Integer id;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Hong_Kong")
    private Date createdAt;

    @JsonProperty("need_pet")
    private Boolean needPet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getNeedPet() {
        return needPet;
    }

    public void setNeedPet(Boolean needPet) {
        this.needPet = Objects.requireNonNullElse(needPet, false);
    }
}
