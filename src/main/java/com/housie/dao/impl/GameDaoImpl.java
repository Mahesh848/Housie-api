package com.housie.dao.impl;

import com.housie.dao.GameDao;
import com.housie.model.Game;
import com.housie.model.Number;
import com.housie.model.Participant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    public void addNumber(Number number) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(number);
        transaction.commit();
    }
}
