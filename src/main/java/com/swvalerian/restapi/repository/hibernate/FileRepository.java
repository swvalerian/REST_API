package com.swvalerian.restapi.repository.hibernate;

import com.swvalerian.restapi.model.File;
import com.swvalerian.restapi.repository.GenericRepository;
import com.swvalerian.restapi.repository.hibernate.HibernateSessionInit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FileRepository implements GenericRepository {
    SessionFactory sessionFactory = HibernateSessionInit.getSessionFactory();

    @Override
    public List<File> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // в запросе указываем класс, работаем с обьектами классов
        Query<File> query = session.createQuery("FROM File");
        List<File> fileList = query.list();

        transaction.commit();
        session.close();

        return fileList;
    }

    @Override
    public Object getId(Object o) {
        return null;
    }

    @Override
    public List update(Object o) {
        return null;
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public void deleteById(Object o) {

    }
}
