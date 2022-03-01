package com.housie.model;


import javax.persistence.*;

@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="numbers")
    private String numbers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public Ticket(String numbers, Participant participant) {
        this.numbers = numbers;
        this.participant = participant;
    }

    public Ticket() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Integer getId() {
        return id;
    }

    public String getNumbers() {
        return numbers;
    }

    public Participant getParticipant() {
        return participant;
    }
}
