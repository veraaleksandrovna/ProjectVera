package DataAccessObjects;

import Interfaces.DAO;
import Models.Entities.Worker;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WorkerDao implements DAO<Worker> {
    @Override
    public void save(Worker obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Worker obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Worker obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Worker findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Worker worker = session.get(Worker.class, id);
        tx1.commit();
        session.close();
        return worker;
    }

    @Override
    public List<Worker> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Worker> workers = (List<Worker>)session.createQuery("From Worker").list();
        tx1.commit();
        session.close();
        return workers;
    }
}
