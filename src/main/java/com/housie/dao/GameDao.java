package com.housie.dao;

import com.housie.model.*;
import com.housie.model.Number;

import java.util.List;

public interface GameDao {
    void create(Game game);

    Game getByUuid(String game);

    Participant addParticipant(Participant participant);

    void addNumbers(List<Number> number);

    void update(Game game);

    void saveTickets(List<Ticket> tickets);

    List<Ticket> getTickets(Integer participantId);

    int getCountOfTickets(Integer gameId);

    void addPrize(Prize prize);

    Participant getParticipant(Integer id);

    boolean isAlreadyExistsForThisGame(String type, Integer id);
}
