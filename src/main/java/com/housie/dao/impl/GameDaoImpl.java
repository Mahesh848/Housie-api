package com.housie.dao.impl;

import com.housie.dao.GameDao;
import com.housie.model.Game;
import com.housie.model.Number;
import com.housie.model.Participant;
import com.housie.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(Game game) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(game);
        transaction.commit();
    }

    @Override
    public Game getByUuid(String gameUuid) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Game> query = builder.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);
        query.select(root).where(builder.equal(root.get("uuid"), gameUuid));
        Query<Game> q = session.createQuery(query);
        Game game = q.getSingleResult();

        transaction.commit();

        return game;
    }

    @Override
    public Participant addParticipant(Participant participant) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(participant);
        transaction.commit();
        participant.setId(id);
        return participant;
    }

    @Override
    public void addNumbers(List<Number> numbers) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for (Number number : numbers) {
            session.save(number);
        }
        transaction.commit();
    }

    @Override
    public void update(Game game) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Game gameInDb = session.load(Game.class, game.getId());
        gameInDb.update(game);
        session.update(gameInDb);
        transaction.commit();
    }

    @Override
    public void saveTickets(List<Ticket> tickets) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for (Ticket ticket : tickets) {
            session.save(ticket);
        }
        transaction.commit();
    }

    @Override
    public List<Ticket> getTickets(String participantId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("participant").get("id"), participantId));
        Query<Ticket> q = session.createQuery(query);
        List<Ticket> tickets = q.getResultList();
        transaction.commit();

        return tickets;
    }
}
