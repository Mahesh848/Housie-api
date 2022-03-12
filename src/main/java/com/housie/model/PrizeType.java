package com.housie.model;

public enum PrizeType {
    TOP_LINE("top_line", "All the numbers in the top row of the ticket should be strikethrough"),
    MIDDLE_LINE("middle_line", "All the numbers in the middle row of the ticket should be strikethrough"),
    BOTTOM_LINE("bottom_line","All the numbers in the bottom row of the ticket should be strikethrough"),
    FIRST_FIVE("first_five", "First five number in the ticket"),
    HOUSE_FULL("house_full", "All the numbers in the ticket should be strikethrough");

    private final String name;
    private final String description;

    PrizeType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean equals(String name) {
        return this.name.compareTo(name.toLowerCase()) == 0;
    }

    public static boolean isValid(String type) {
        for (PrizeType prizeType : PrizeType.values()) {
            if (prizeType.equals(type)) return true;
        }
        return false;
    }

    public static Double getAmountForType(String type, Integer totalAmount) {
        if (HOUSE_FULL.equals(type)) {
            return totalAmount * (25 / 100.0);
        } else {
            return totalAmount * (18.75 / 100.0);
        }
    }
}
