package com.housie.service;

import com.housie.model.web.*;

public interface GameService {

    GameResponse create(GameRequest gameRequest);

    ParticipantResponse addParticipant(ParticipantRequest participantRequest);

    void addNumber(NumberRequest numberRequest);
}
