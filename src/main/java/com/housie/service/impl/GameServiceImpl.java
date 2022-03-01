package com.housie.service.impl;

import com.housie.dao.GameDao;
import com.housie.mapper.GameMapper;
import com.housie.model.*;
import com.housie.model.Number;
import com.housie.model.web.*;
import com.housie.service.GameService;
import com.housie.util.NumberGenerator;
import com.housie.util.TicketGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Map<String, Timer> gameToTimerMap = new HashMap<>();

    @Override
    public GameResponse create(GameRequest gameRequest) throws HousieException {
        Game game = GameMapper.mapTo(gameRequest);
        String uuid = UUID.randomUUID().toString();
        game.setUuid(uuid);
        game.setStatus(GameStatus.IDEAL);
        gameDao.create(game);
        Participant creator = createParticipantFrom(new ParticipantRequest(gameRequest.getCreatedBy(), game.getUuid(), 0));
        creator = gameDao.addParticipant(creator);
        game.setCreatedBy(creator);
        gameDao.update(game);
        return GameMapper.mapTo(game);
    }

    @Override
    public ParticipantResponse addParticipant(ParticipantRequest participantRequest) throws HousieException {
        Participant participant = createParticipantFrom(participantRequest);
        Participant p = gameDao.addParticipant(participant);
        List<Ticket> tickets = new ArrayList<>();
        for (int i=0; i<p.getTickets(); i++) {
            TicketGenerator ticketGenerator = new TicketGenerator();
            String ticketString = ticketGenerator.generateTicket();
            tickets.add(new Ticket(ticketString, p));
        }
        gameDao.saveTickets(tickets);
        return GameMapper.mapTo(p);
    }

    @Override
    public void startGame(StartRequest startRequest) throws HousieException {
        Game game = gameDao.getByUuid(startRequest.getUuid());
        if (!game.getCreatedBy().getName().equals(startRequest.getParticipant()) ||
                game.getStatus().equals(GameStatus.IN_PROGRESS) || game.getStatus().equals(GameStatus.DONE))
            throw new HousieException("You are not allowed to do this operation");
        game.setStatus(GameStatus.IN_PROGRESS);
        gameDao.update(game);
        List<Number> numbers = generateNumbers(game);
        gameDao.addNumbers(numbers);
        TimerTask numberGenerator = new NumberGenerator(game, numbers, simpMessagingTemplate);
        Timer timer = new Timer("NumberGenerator: " + game.getUuid());
        timer.schedule(numberGenerator, 3000, 5000);
        gameToTimerMap.put(game.getUuid(), timer);
    }

    @Override
    public void stopGame(String gameUuid) {
        Timer timer = gameToTimerMap.get(gameUuid);
        timer.cancel();

        Game game = gameDao.getByUuid(gameUuid);
        game.setStatus(GameStatus.DONE);
        gameDao.update(game);
    }

    @Override
    public List<TicketResponse> getTickets(String participantId) {
        List<Ticket> tickets = gameDao.getTickets(participantId);
        return GameMapper.mapTo(tickets);
    }

    private Participant createParticipantFrom(ParticipantRequest participantRequest) throws HousieException {
        Game game = gameDao.getByUuid(participantRequest.getGame());
        if (game.getStatus() != GameStatus.IDEAL) throw new HousieException("You cannot join the game now");
        return GameMapper.mapTo(participantRequest, game);
    }

    private List<Number> generateNumbers(Game game) {
        List<Number> numbers = new ArrayList<>();
        Set<Integer> numbersSet = new HashSet<>();
        Random random = new Random();
        while (numbersSet.size() < 90) {
            int number = random.nextInt(90) + 1;
            while (numbersSet.contains(number)) {
                number = random.nextInt(90) + 1;
            }
            numbersSet.add(number);
            Number num = new Number(number, game);
            numbers.add(num);
        }
        return numbers;
    }
}
