package com.housie.model;

import javax.persistence.*;

@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "passcode")
    private String passcode;

    @Column(name = "ticket_price")
    private Integer ticketPrice;

    public Game() {}

    public Game(String passcode, Integer ticketPrice) {
        this.passcode = passcode;
        this.ticketPrice = ticketPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
