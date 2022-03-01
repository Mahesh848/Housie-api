package com.housie.model.web;

public class TicketResponse {
    private Integer id;
    private String numbers;

    public TicketResponse(Integer id, String numbers) {
        this.id = id;
        this.numbers = numbers;
    }

    public Integer getId() {
        return id;
    }

    public String getNumbers() {
        return numbers;
    }
}
