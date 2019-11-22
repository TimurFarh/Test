package testgroup.bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.bankimitation.model.BankAccount;

import java.util.List;

@Repository
public class AccountDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<BankAccount> getAll(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from BankAccount where client.id = :clientId");
        query.setParameter("clientId", id);
        return query.list();
    }

    public void add(BankAccount account) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(account);
    }

    public void delete(BankAccount account) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(account);
    }

    public void edit(BankAccount account) {
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
    }

    public BankAccount getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BankAccount.class, id);
    }
}
