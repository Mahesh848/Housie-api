package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.model.Game;
import com.housie.model.Number;

import java.util.*;

public class NumberGenerator extends TimerTask {
    private final Game game;
    private final List<Number> numbers;
    private Integer index;

    public NumberGenerator(Game game, List<Number> numbers) {
        this.game = game;
        this.numbers = numbers;
        index = 0;
    }

    @Override
    public void run() {
        if (index >= 99) {
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer number = numbers.get(index).getNumber();
        System.out.println(Thread.currentThread().getName() + " " + number);
        index++;
    }
}
