package com.housie.mapper;

import com.housie.model.Game;
import com.housie.model.Participant;
import com.housie.model.Ticket;
import com.housie.model.web.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public static List<TicketResponse> mapTo(List<Ticket> tickets) {
        List<TicketResponse> ticketResponses = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketResponses.add(new TicketResponse(ticket.getId(), ticket.getNumbers()));
        }
        return ticketResponses;
    }
}
