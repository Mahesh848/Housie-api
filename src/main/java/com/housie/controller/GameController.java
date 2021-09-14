package com.housie.controller;

import com.housie.model.Game;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UUID create(@RequestBody Game game) {
        return gameService.create(game);
    }

}
