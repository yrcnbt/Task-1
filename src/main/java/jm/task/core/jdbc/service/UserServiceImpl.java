package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao udji = new UserDaoHibernateImpl();

    public UserServiceImpl() {
    }

    public void createUsersTable() {
        udji.createUsersTable();
    }

    public void dropUsersTable() {
        udji.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        udji.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        udji.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return udji.getAllUsers();
    }

    public void cleanUsersTable() {
        udji.cleanUsersTable();
    }
}
