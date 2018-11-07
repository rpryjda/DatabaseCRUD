package com.pryjda.hibernate_app.dao;

import com.pryjda.hibernate_app.entities.Runs;
import com.pryjda.hibernate_app.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class HibernateRunsCrud implements DaoRunsCrud {

    private SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
    private Session session;

    @Override
    public void create(Runs runs) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(runs);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Runs> read() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Runs> results = session.createQuery("from Runs").list();
        session.getTransaction().commit();
        session.close();
        return results;
    }

    @Override
    public Runs read(int id) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Runs runs = null;
        try {
            runs = (Runs) session
                    .createQuery("from Runs where id=:id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException exe){
            System.out.println("id="+id+" record doesn't exist");
        }
        session.getTransaction().commit();
        session.close();
        return runs;
    }

    @Override
    public void update(Runs runs) {
        this.create(runs);
    }

    @Override
    public void delete() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table Runs").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete Runs where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Runs runs) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(runs);
        session.getTransaction().commit();
        session.close();
    }
}
