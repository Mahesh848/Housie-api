package com.housie.util;

import java.util.*;

public class TicketGenerator {
    private final int [][] ticket;
    private final Set<Integer> columnsSet;
    private static final Map<Integer, int[]> columnRangeMap = new HashMap<>();
    private static final int columns = 9;
    private static final int rows = 3;
    private static final Random random = new Random();

    static {
        columnRangeMap.put(0, new int[]{1,9});
        columnRangeMap.put(1, new int[]{10,19});
        columnRangeMap.put(2, new int[]{20,29});
        columnRangeMap.put(3, new int[]{30,39});
        columnRangeMap.put(4, new int[]{40,49});
        columnRangeMap.put(5, new int[]{50,59});
        columnRangeMap.put(6, new int[]{60,69});
        columnRangeMap.put(7, new int[]{70,79});
        columnRangeMap.put(8, new int[]{80,90});
    }

    public TicketGenerator() {
        this.ticket = new int[rows][columns];
        columnsSet = new HashSet<>(List.of(0,1,2,3,4,5,6,7,8));
    }

    public String generateTicket() {
        pickRandomColumns(0, 5);
        pickRandomColumns(2, 5);
        pickRandomColumns(1,5-columnsSet.size());
        fillTheTicket();
        return convertTheTicketToString();
    }

    private String convertTheTicketToString() {
        StringBuilder ticketString = new StringBuilder();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                if (ticket[i][j] == 0) {
                    ticketString.append(" " + ",");
                } else {
                    ticketString.append(ticket[i][j] + ",");
                }
            }
            ticketString.append("\n");
        }
        return ticketString.toString();
    }

    private void fillTheTicket() {
        for (int i=0; i<columns; i++) {
            fillTheColumn(i);
        }
    }

    private void fillTheColumn(int col) {
        int []range = columnRangeMap.get(col);
        Set<Integer> numbers = new TreeSet<>();
        int count = 0;
        for (int row=0; row<rows; row++) {
            if (ticket[row][col] == 0) continue;
            count++;
        }
        while (count-- > 0) {
            int number = random.nextInt(range[1] - range[0] + 1) + range[0];
            while (numbers.contains(number)) number = random.nextInt(range[1] - range[0] + 1) + range[0];
            numbers.add(number);
        }
        int index = 0;
        int []numbersArray = numbers.stream().mapToInt(num -> num).toArray();
        for (int row=0; row<rows; row++) {
            if (ticket[row][col] == 0) continue;
            ticket[row][col] = numbersArray[index++];
        }
    }

    private void pickRandomColumns(int row, int noOfTimes) {
        Set<Integer> set = new HashSet<>();
        if (row == 1) {
            for (int col : columnsSet) {
                ticket[row][col] = -1;
                set.add(col);
            }
        }
        for (int i=0;i<noOfTimes;i++) {
            int col = random.nextInt(columns);
            while(set.contains(col)) col = random.nextInt(columns);
            set.add(col);
            ticket[row][col] = -1;
            columnsSet.remove(col);
        }
    }

}
