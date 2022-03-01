package com.housie.util;

import com.housie.model.Game;
import com.housie.model.Number;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.*;

public class NumberGenerator extends TimerTask {
    private final Game game;
    private final List<Number> numbers;
    private Integer index;
    private SimpMessagingTemplate simpMessagingTemplate;

    public NumberGenerator(Game game, List<Number> numbers, SimpMessagingTemplate simpMessagingTemplate) {
        this.game = game;
        this.numbers = numbers;
        index = 0;
        this.simpMessagingTemplate = simpMessagingTemplate;
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
        simpMessagingTemplate.convertAndSendToUser(game.getUuid(), "/number", number);
        System.out.println(Thread.currentThread().getName() + " " + number);
        index++;
    }
}
