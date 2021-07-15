package com.swvalerian.restapi.repository.hibernate;

import com.swvalerian.restapi.model.File;
import com.swvalerian.restapi.repository.GenericRepository;
import com.swvalerian.restapi.repository.hibernate.HibernateSessionInit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FileRepository implements GenericRepository<File, Integer> {
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
    public File getId(Integer id) {
        return getAll().stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public File save(File file) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = (Integer) session.save(file); // получим id вновь созданной записи

        transaction.commit();
        session.close();
        return file;
    }

    @Override
    public List<File> update(File file) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(file);

        transaction.commit();
        session.close();
        return getAll();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        File file = session.get(File.class, id);
        session.delete(file);

        transaction.commit();
        session.close();
    }
}
