package DataAccessObjects;

import Interfaces.DAO;
import Models.Entities.Produce;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProduceDao implements DAO<Produce> {
    @Override
    public void save(Produce obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Produce obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Produce obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Produce findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Produce produce = session.get(Produce.class, id);
        tx1.commit();
        session.close();
        return produce;
    }

    @Override
    public List<Produce> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Produce> produces = (List<Produce>)session.createQuery("From Produce").list();
        tx1.commit();
        session.close();
        return produces;
    }
}
