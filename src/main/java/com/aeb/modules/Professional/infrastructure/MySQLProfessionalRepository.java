package com.aeb.modules.Professional.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aeb.genericClasses.person.infrastructure.MySQLRepository;
import com.aeb.modules.Professional.domain.IPorfessional;
import com.aeb.modules.Professional.domain.Professional;

public class MySQLProfessionalRepository extends MySQLRepository<Professional> implements IPorfessional{

    @Override
    protected Professional mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Professional();
    }

    @Override
    protected PreparedStatement createFindByIdStatement(Connection connection, Long id) throws SQLException {
        String query = "SELECT id,name FROM professional WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement createSaveStatement(Connection connection, Customer customer) throws SQLException {
        String query = "INSERT INTO customers (id, name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, customer.getId());
        statement.setString(2, customer.getName());
        return statement;
    }

    @Override
    protected String getTableName() {
        return "professional";
    }
}
    

