package com.swvalerian.restapi.repository.hibernate;

import com.swvalerian.restapi.model.Event;
import com.swvalerian.restapi.model.File;
import com.swvalerian.restapi.repository.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class EventRepository implements GenericRepository<Event, Integer> {
    SessionFactory sessionFactory = HibernateSessionInit.getSessionFactory();

    @Override
    public List<Event> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // в запросе указываем класс, работаем с обьектами классов
        Query<Event> query = session.createQuery("FROM Event");
        List<Event> eventList = query.list();

        transaction.commit();
        session.close();
        return eventList;
    }

    @Override
    public Event getId(Integer id) {
        return getAll().stream().filter(event -> event.getEventId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Event> update(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        event.setUpdated(LocalDateTime.now()); // можно и тут внести изменения, но это неправильно, этот слой отвечает только за работу с БД
        // т.е. здесь мы работаем уже с готовым, измененнным обьектом!

        session.update(event);

        transaction.commit();
        session.close();

        return getAll();
    }

    @Override
    public Event save(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(event);

        transaction.commit();
        session.close();
        return event;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Event event = session.get(Event.class, id);
        session.delete(event);

        transaction.commit();
        session.close();
    }
}
