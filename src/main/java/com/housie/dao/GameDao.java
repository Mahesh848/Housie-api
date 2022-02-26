package com.housie.dao;

import com.housie.model.Game;
import com.housie.model.Number;
import com.housie.model.Participant;

import java.util.List;

public interface GameDao {
    void create(Game game);

    Game getByUuid(String game);

    Participant addParticipant(Participant participant);

    void addNumbers(List<Number> number);

    void update(Game game);
}
