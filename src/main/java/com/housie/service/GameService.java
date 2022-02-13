package com.housie.service;

import com.housie.model.HousieException;
import com.housie.model.web.*;

public interface GameService {

    GameResponse create(GameRequest gameRequest);

    ParticipantResponse addParticipant(ParticipantRequest participantRequest) throws HousieException;

    void addNumber(NumberRequest numberRequest);
}
