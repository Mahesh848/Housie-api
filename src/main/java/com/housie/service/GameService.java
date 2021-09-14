package com.housie.service;

import com.housie.model.Game;

import java.util.UUID;

public interface GameService {
    UUID create(Game game);
}
