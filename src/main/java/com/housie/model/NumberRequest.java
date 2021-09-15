package com.housie.model;

public class NumberRequest {
    private String game;
    private Integer number;

    public NumberRequest(String game, Integer number) {
        this.game = game;
        this.number = number;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
