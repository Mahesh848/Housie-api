package com.housie.mapper;

import com.housie.model.Game;
import com.housie.model.web.GameRequest;
import com.housie.model.web.GameResponse;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public Game mapTo(GameRequest gameRequest) {
        Game game = new Game();
        game.setPasscode(gameRequest.getPasscode());
        game.setTicketPrice(gameRequest.getTicketPrice());
        return game;
    }

    public GameResponse mapTo(Game game) {
        GameResponse gameResponse = new GameResponse(game.getUuid());
        return gameResponse;
    }
}
