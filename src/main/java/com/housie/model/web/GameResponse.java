package com.housie.model.web;

public class GameResponse {
    private String uuid;
    private Integer createdBy;

    public GameResponse(String uuid, Integer createdBy) {
        this.uuid = uuid;
        this.createdBy = createdBy;
    }

    public String getUuid() {
        return uuid;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }
}
