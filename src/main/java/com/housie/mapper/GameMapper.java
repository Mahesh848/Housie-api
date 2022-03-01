package com.housie.mapper;

import com.housie.model.Game;
import com.housie.model.Participant;
import com.housie.model.web.GameRequest;
import com.housie.model.web.GameResponse;
import com.housie.model.web.ParticipantRequest;
import com.housie.model.web.ParticipantResponse;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public static Game mapTo(GameRequest gameRequest) {
        Game game = new Game();
        game.setPasscode(gameRequest.getPasscode());
        game.setTicketPrice(gameRequest.getTicketPrice());
        return game;
    }

    public static GameResponse mapTo(Game game) {
        GameResponse gameResponse = new GameResponse(game.getUuid(), game.getCreatedBy().getId());
        return gameResponse;
    }

    public static ParticipantResponse mapTo(Participant participant) {
        ParticipantResponse response = new ParticipantResponse(participant.getId());
        return response;
    }

    public static Participant mapTo(ParticipantRequest participantRequest, Game game) {
        return new Participant(participantRequest.getName(), participantRequest.getTickets(), game);
    }
}
