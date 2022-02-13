package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.mapper.GameMapper;
import com.housie.model.*;
import com.housie.model.Number;
import com.housie.model.web.*;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Override
    public GameResponse create(GameRequest gameRequest) {
        Game game = GameMapper.mapTo(gameRequest);
        String uuid = UUID.randomUUID().toString();
        game.setUuid(uuid);
        game.setStatus(GameStatus.IDEAL);
        gameDao.create(game);
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
