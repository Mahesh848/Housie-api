package com.housie.controller;

import com.housie.model.HousieException;
import com.housie.model.web.*;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

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
        } catch (HousieException exception) {
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
        } catch (HousieException exception) {
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse(exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity<?> start(@RequestBody StartRequest startRequest) {
        try {
            gameService.startGame(startRequest);
            return new ResponseEntity<>("game started successfully", HttpStatus.OK);
        } catch (NoResultException exception) {
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse("Invalid game");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (HousieException exception) {
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse(exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    public ResponseEntity<?> stop(@RequestBody String gameUuid) {
        try {
            gameService.stopGame(gameUuid);
            return new ResponseEntity<>("game stopped successfully", HttpStatus.OK);
        } catch (NoResultException exception) {
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse("Invalid game");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getTickets", method = RequestMethod.GET)
    public ResponseEntity<?> getTickets(@RequestParam String userId) {
        try {
            List<TicketResponse> tickets = gameService.getTickets(userId);
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (NoResultException exception) {
            exception.printStackTrace();
            ErrorResponse response = new ErrorResponse("Invalid user");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
