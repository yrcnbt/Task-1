package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private final Connection connection = Util.getConnection();

    public void createUsersTable() {
        String sql = "CREATE TABLE users(Id SERIAL PRIMARY KEY, Name VARCHAR(100) NOT NULL, LastName VARCHAR(100) NOT NULL, age INTEGER NOT NULL)";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(Name, LastName, age) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users1 WHERE Id IS ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        try(Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> allUsers = new ArrayList<>();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }
            resultSet.close();
            return allUsers;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE  users";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}