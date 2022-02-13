package com.housie.model.web;

public class GameResponse {
    private String uuid;
    private String createdBy;

    public GameResponse(String uuid, String createdBy) {
        this.uuid = uuid;
        this.createdBy = createdBy;
    }

    public String getUuid() {
        return uuid;
    }
}
