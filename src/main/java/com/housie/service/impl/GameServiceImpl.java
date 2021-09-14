package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.model.Game;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Override
    public UUID create(Game game) {
        UUID uuid = UUID.randomUUID();
        game.setUuid(uuid);
        gameDao.create(game);
        return uuid;
    }
}
