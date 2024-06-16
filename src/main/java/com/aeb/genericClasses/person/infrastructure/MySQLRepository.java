package com.aeb.genericClasses.person.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aeb.genericClasses.person.domain.IPerson;

public abstract class MySQLRepository<T> implements IPerson<T>{

    protected final String url;
    protected final String username;
    protected final String password;

    public MySQLRepository(String url, String username, String password){
        this.password = password;
        this.url = url;
        this.username = username;
    }

    protected abstract T mapResultSetToEntity(ResultSet result) throws SQLException;
    protected abstract PreparedStatement createFindByIdStatement(Connection connection, Integer id) throws SQLException;
    protected abstract PreparedStatement createSaveStatement(Connection connection, T entity) throws SQLException;

    @Override
    public List<T> getAll(){
        List<T> entities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            String query = "SELECT id,name from " + getTableName();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                entities.add(mapResultSetToEntity(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public void save(T entity){
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = createSaveStatement(connection, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getById(Integer id){
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = createFindByIdStatement(connection, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    protected abstract String getTableName();
}
