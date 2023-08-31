package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users(Id SERIAL PRIMARY KEY, Name VARCHAR(100) NOT NULL, LastName VARCHAR(100) NOT NULL, age INTEGER NOT NULL)").addEntity(User.class).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User usr = session.get(User.class, id);
            session.delete(usr);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try(Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            List<User> selectAFromUser = session.createQuery("FROM users", User.class).getResultList();
            transaction.commit();
            return selectAFromUser;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
        }
    }
}
