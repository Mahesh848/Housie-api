package com.housie.model.web;

public class PrizesResponse {
    private Double amountForHouseFull;
    private Double amountForOthers;

    public PrizesResponse(Double amountForHouseFull, Double amountForOthers) {
        this.amountForHouseFull = amountForHouseFull;
        this.amountForOthers = amountForOthers;
    }

    public Double getAmountForHouseFull() {
        return amountForHouseFull;
    }

    public Double getAmountForOthers() {
        return amountForOthers;
    }
}
