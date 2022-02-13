package com.housie.controller;

import com.housie.model.web.ErrorResponse;
import com.housie.model.web.GameRequest;
import com.housie.model.web.GameResponse;
import com.housie.model.web.NumberRequest;
import com.housie.model.ParticipantRequest;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GameRequest gameRequest) {
        try {
            GameResponse gameResponse = gameService.create(gameRequest);
            return new ResponseEntity<>(gameResponse, HttpStatus.OK);
        } catch (RuntimeException exception) {
            ErrorResponse response = new ErrorResponse("Failed to create game due to Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @RequestMapping(value = "/add-number", method = RequestMethod.POST)
    public void addNumber(@RequestBody NumberRequest numberRequest) {
        gameService.addNumber(numberRequest);
    }

}
