package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.mapper.GameMapper;
import com.housie.model.Game;
import com.housie.model.Number;
import com.housie.model.web.GameRequest;
import com.housie.model.web.GameResponse;
import com.housie.model.web.NumberRequest;
import com.housie.model.Participant;
import com.housie.model.ParticipantRequest;
import com.housie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private GameMapper gameMapper;

    @Override
    public GameResponse create(GameRequest gameRequest) {
        Game game = gameMapper.mapTo(gameRequest);
        String uuid = UUID.randomUUID().toString();
        game.setUuid(uuid);
        gameDao.create(game);
        return gameMapper.mapTo(game);
    }

    @Override
    public void addParticipant(ParticipantRequest participantRequest) {
        Participant participant = createParticipantFrom(participantRequest);
        gameDao.addParticipant(participant);
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

    private Participant createParticipantFrom(ParticipantRequest participantRequest) {
        Game game = gameDao.getByUuid(participantRequest.getGame());
        return new Participant(participantRequest.getName(), game);
    }
}
