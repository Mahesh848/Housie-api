package com.housie.model;

import javax.persistence.*;

@Entity
@Table(name = "participants", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "game_id"}))
public class Participant {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "tickets")
    private Integer tickets;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    public Participant(String name, Integer tickets, Game game) {
        this.name = name;
        this.tickets = tickets;
        this.game = game;
    }

    public Participant(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }
}
