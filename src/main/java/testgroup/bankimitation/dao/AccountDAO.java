package testgroup.bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public List<BankAccount> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("").list();
    }

    public void add(BankAccount object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    public void delete(BankAccount object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public void edit(BankAccount object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    public BankAccount getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BankAccount.class, id);
    }
}
