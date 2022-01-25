package DataAccessObjects;

import Interfaces.DAO;
import Models.Entities.Batch;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BatchDao implements DAO<Batch> {
    @Override
    public void save(Batch obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Batch obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Batch obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Batch findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Batch batch = session.get(Batch.class, id);
        tx1.commit();
        session.close();
        return batch;
    }

    @Override
    public List<Batch> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Batch> batches = (List<Batch>)session.createQuery("From Batch").list();
        tx1.commit();
        session.close();
        return batches;
    }
}
