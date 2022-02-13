package com.housie.controller;

import com.housie.model.web.*;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

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
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse("Failed to create game due to Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseEntity<?> join(@RequestBody ParticipantRequest participantRequest) {
        try {
            ParticipantResponse response = gameService.addParticipant(participantRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoResultException e) {
            ErrorResponse response = new ErrorResponse("Invalid game");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (PersistenceException exception) {
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse("Name already exists");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse("Failed to start game due to Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
