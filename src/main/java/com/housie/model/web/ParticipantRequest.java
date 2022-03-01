package com.housie.model.web;

public class ParticipantRequest {
    private String name;
    private String game;
    private Integer tickets;

    public ParticipantRequest(String name, String game, Integer tickets) {
        this.name = name;
        this.game = game;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }
}
