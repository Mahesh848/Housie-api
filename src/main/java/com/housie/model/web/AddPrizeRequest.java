package com.housie.model.web;

public class AddPrizeRequest {
    private Integer participantId;
    private String type;

    public AddPrizeRequest(Integer participantId, String type) {
        this.participantId = participantId;
        this.type = type;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
