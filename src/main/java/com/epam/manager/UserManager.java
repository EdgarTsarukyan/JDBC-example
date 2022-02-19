package com.epam.manager;

import com.epam.conf.DBConnectionProvider;
import com.epam.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Manager<User, Integer> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

//    @Override
//    public List<User> getAll() {
//        List<User> users = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setSurname(resultSet.getString("surname"));
//                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            System.out.println("something wrong during work");
//        }
//        return users;
//    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("something wrong during work");
        }
        return users;
    }

//    @Override
//    public User getById(Integer id) {
//        User user = new User();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id =" + id);
//            if (resultSet.next()) {
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setSurname(resultSet.getString("surname"));
//                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
//                return user;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("something wrong during work in method getById");
//        }
//
//        return null;
//    }

    @Override
    public User getById(Integer id) {
        User user = new User();
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("something wrong during work in method getById");
        }

        return null;
    }

//    @Override
//    public void create(User object) {
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("INSERT INTO users(name, surname, email, password)" +
//                    "VALUES('" + object.getName() + "','" + object.getSurname() + "'," +
//                    "'" + object.getEmail() + "','" + object.getPassword() + "') ");
//        } catch (SQLException e) {
//            System.out.println("something wrong during work in method getById");
//        }
//    }

    @Override
    public void create(User objet) {
        try {
            String query = "INSERT INTO users(name, surname, email, password) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, objet.getName());
            preparedStatement.setString(2, objet.getSurname());
            preparedStatement.setString(3, objet.getEmail());
            preparedStatement.setString(4, objet.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong during work in method create");
        }
    }

//    @Override
//    public void update(User objet) {
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("UPDATE users SET name = '" + objet.getName() + "', surname = '" + objet.getSurname() + "'," +
//                    " email = '" + objet.getEmail() + "',password = " +
//                    "'"+objet.getPassword()+"' WHERE id =" + objet.getId());
//        } catch (SQLException e) {
//            System.out.println("something wrong during work in method update");
//        }
//    }

    @Override
    public void update(User objet) {
        try {
            String query = "UPDATE users Set name = ?, surname = ?, email = ?, password = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, objet.getName());
            preparedStatement.setString(2, objet.getSurname());
            preparedStatement.setString(3, objet.getEmail());
            preparedStatement.setString(4, objet.getPassword());
            preparedStatement.setInt(5, objet.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong during work in method create");
        }
    }

//    @Override
//    public void delete(Integer id) {
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("DELETE FROM users WHERE id = " + id);
//        } catch (SQLException e) {
//            System.out.println("something wrong during work in method delete");
//        }
//    }

    @Override
    public void delete(Integer id) {
        try {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong during work in method delete");
        }
    }
}
