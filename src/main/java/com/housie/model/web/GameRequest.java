package com.housie.model.web;

public class GameRequest {
    private String passcode;
    private Integer ticketPrice;
    private String createdBy;
    private Integer noOfTickets;

    public GameRequest(String passcode, Integer ticketPrice, String createdBy, Integer noOfTickets) {
        this.passcode = passcode;
        this.ticketPrice = ticketPrice;
        this.createdBy = createdBy;
        this.noOfTickets = noOfTickets;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(Integer noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
}
