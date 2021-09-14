package com.housie.dao.impl;

import com.housie.dao.GameDao;
import com.housie.model.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
