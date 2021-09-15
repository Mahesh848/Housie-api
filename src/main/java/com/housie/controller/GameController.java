package com.housie.controller;

import com.housie.model.Game;
import com.housie.model.ParticipantRequest;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestBody Game game) {
        return gameService.create(game);
    }

    @RequestMapping(value = "/add-participant", method = RequestMethod.POST)
    public String addUser(@RequestBody ParticipantRequest participantRequest) {
        try {
            gameService.addParticipant(participantRequest);
            return "SuccessFully added to the game";
        } catch (NoResultException e) {
            return "Invalid game";
        }
    }

}
