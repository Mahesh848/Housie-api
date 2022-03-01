package com.housie.service;

import com.housie.model.HousieException;
import com.housie.model.web.*;

import java.util.List;

public interface GameService {

    GameResponse create(GameRequest gameRequest) throws HousieException;

    ParticipantResponse addParticipant(ParticipantRequest participantRequest) throws HousieException;

    void startGame(StartRequest startRequest) throws HousieException;

    void stopGame(String gameUuid);

    List<TicketResponse> getTickets(String participantId);
}
