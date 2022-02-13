package com.housie.service;

import com.housie.model.Game;
import com.housie.model.web.GameRequest;
import com.housie.model.web.GameResponse;
import com.housie.model.web.NumberRequest;
import com.housie.model.ParticipantRequest;

public interface GameService {

    GameResponse create(GameRequest gameRequest);

    void addParticipant(ParticipantRequest participantRequest);

    void addNumber(NumberRequest numberRequest);
}
