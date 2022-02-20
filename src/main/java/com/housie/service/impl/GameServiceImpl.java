package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.mapper.GameMapper;
import com.housie.model.*;
import com.housie.model.Number;
import com.housie.model.web.*;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    private Map<String, Timer> gameToTimerMap = new HashMap<>();

    @Override
    public GameResponse create(GameRequest gameRequest) throws HousieException {
        Game game = GameMapper.mapTo(gameRequest);
        String uuid = UUID.randomUUID().toString();
        game.setUuid(uuid);
        game.setStatus(GameStatus.IDEAL);
        gameDao.create(game);
        Participant creator = createParticipantFrom(new ParticipantRequest(gameRequest.getCreatedBy(), game.getUuid()));
        creator = gameDao.addParticipant(creator);
        game.setCreatedBy(creator);
        gameDao.update(game);
        return GameMapper.mapTo(game);
    }

    @Override
    public ParticipantResponse addParticipant(ParticipantRequest participantRequest) throws HousieException {
        Participant participant = createParticipantFrom(participantRequest);
        Participant p = gameDao.addParticipant(participant);
        return GameMapper.mapTo(p);
    }

    @Override
    public void addNumber(NumberRequest numberRequest) {
        Number number = createNumberFrom(numberRequest);
        gameDao.addNumber(number);
    }

    @Override
    public void startGame(StartRequest startRequest) throws HousieException {
        Game game = gameDao.getByUuid(startRequest.getUuid());
        if (!game.getCreatedBy().getName().equals(startRequest.getParticipant()) ||
                game.getStatus().equals(GameStatus.IN_PROGRESS) || game.getStatus().equals(GameStatus.DONE))
            throw new HousieException("You are not allowed to do this operation");
        game.setStatus(GameStatus.IN_PROGRESS);
        gameDao.update(game);

        TimerTask numberGenerator = new NumberGenerator(game, gameDao);
        Timer timer = new Timer("NumberGenerator: " + game.getUuid());
        timer.schedule(numberGenerator, 5000, 5000);
        gameToTimerMap.put(game.getUuid(), timer);
    }

    @Override
    public void stopGame(String gameUuid) {
        Game game = gameDao.getByUuid(gameUuid);
        game.setStatus(GameStatus.DONE);
        gameDao.update(game);

        Timer timer = gameToTimerMap.get(gameUuid);
        timer.cancel();
    }


    private Number createNumberFrom(NumberRequest numberRequest) {
        Game game = gameDao.getByUuid(numberRequest.getGame());
        return new Number(numberRequest.getNumber(), game);
    }

    private Participant createParticipantFrom(ParticipantRequest participantRequest) throws HousieException {
        Game game = gameDao.getByUuid(participantRequest.getGame());
        if (game.getStatus() != GameStatus.IDEAL) throw new HousieException("You cannot join the game now");
        return GameMapper.mapTo(participantRequest, game);
    }
}
