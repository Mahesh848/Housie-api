package com.housie.service;

import com.housie.model.Game;
import com.housie.model.ParticipantRequest;

public interface GameService {

    String create(Game game);

    void addParticipant(ParticipantRequest participantRequest);
}
