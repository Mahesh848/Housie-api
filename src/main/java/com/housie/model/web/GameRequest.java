package com.housie.model.web;

public class GameRequest {
    private String passcode;
    private Integer ticketPrice;

    public GameRequest(String passcode, Integer ticketPrice) {
        this.passcode = passcode;
        this.ticketPrice = ticketPrice;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
