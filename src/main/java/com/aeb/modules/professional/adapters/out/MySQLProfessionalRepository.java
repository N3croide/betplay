package com.aeb.modules.professional.adapters.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aeb.genericClasses.person.infrastructure.MySQLRepository;
import com.aeb.modules.professional.domain.Professional;
import com.aeb.modules.professional.infrastructure.IProfessional;

public class MySQLProfessionalRepository extends MySQLRepository<Professional> implements IProfessional{


    public MySQLProfessionalRepository(String url, String username, String password){
        super(url, username, password);
    }

    @Override
    protected Professional mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Professional(resultSet.getInt("id"), resultSet.getString("name"));
    }

    @Override
    protected PreparedStatement createFindByIdStatement(Connection connection, Integer id) throws SQLException {
        String query = "SELECT id,name FROM professional WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement createSaveStatement(Connection connection, Professional professional) throws SQLException {
        String query = "INSERT INTO professional (id, name, last_name, age, role) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        System.out.println(professional);
        statement.setInt(1, professional.getId());
        statement.setString(2, professional.getName());
        statement.setString(3,professional.getLastName());
        statement.setInt(4,professional.getAge());
        statement.setString(5,professional.getRole());
        return statement;
    }

    @Override
    protected String getTableName() {
        return "professional";
    }
}
    

