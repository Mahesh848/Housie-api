package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.model.Game;
import com.housie.model.Number;
import com.housie.model.NumberRequest;
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

    @Override
    public String create(Game game) {
        String uuid = UUID.randomUUID().toString();
        game.setUuid(uuid);
        gameDao.create(game);
        return uuid;
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
