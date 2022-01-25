package DataAccessObjects;

import Interfaces.DAO;
import Models.Entities.Standart;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StandartDao implements DAO<Standart> {
    @Override
    public void save(Standart obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Standart obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Standart obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Standart findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Standart standart = session.get(Standart.class, id);
        tx1.commit();
        session.close();
        return standart;
    }

    @Override
    public List<Standart> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Standart> standarts = (List<Standart>)session.createQuery("From Standart").list();
        tx1.commit();
        session.close();
        return standarts;
    }
}
