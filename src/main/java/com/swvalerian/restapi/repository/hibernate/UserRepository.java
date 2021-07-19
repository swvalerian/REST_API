package com.swvalerian.restapi.repository.hibernate;

import com.swvalerian.restapi.model.User;
import com.swvalerian.restapi.repository.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository implements GenericRepository<User, Integer> {
    SessionFactory sessionFactory = HibernateSessionInit.getSessionFactory();

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // в запросе указываем класс, работаем с обьектами классов
        Query<User> query = session.createQuery("FROM User");
        List<User> userList = query.list();

        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public List<User> update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();

        return getAll();
    }

    @Override
    public User getId(Integer id) {
        return getAll().stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public User save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);

        transaction.commit();
        session.close();
    }
}
