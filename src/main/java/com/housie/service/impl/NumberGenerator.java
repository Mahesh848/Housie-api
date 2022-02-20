package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.model.Game;
import com.housie.model.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TimerTask;

public class NumberGenerator extends TimerTask {
    private final Game game;
    private final Set<Integer> numbers;
    private final GameDao gameDao;

    public NumberGenerator(Game game, GameDao gameDao) {
        this.game = game;
        numbers = new HashSet<>();
        this.gameDao = gameDao;
    }

    @Override
    public void run() {
        Random random = new Random();
        Integer number = random.nextInt(99) + 1;
        while (numbers.contains(number)) {
            number = random.nextInt(99) + 1;
        }
        System.out.println(Thread.currentThread().getName() + " " + number);
        numbers.add(number);
        Number num = new Number(number, game);
        gameDao.addNumber(num);
    }
}
