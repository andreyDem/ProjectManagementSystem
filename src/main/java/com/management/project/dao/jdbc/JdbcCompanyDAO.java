package com.management.project.dao.jdbc;

import com.management.project.connections.ConnectionDB;
import com.management.project.dao.CompanyDAO;
import com.management.project.models.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class implements a set of methods for working
 * with database, with Company entity.
 *
 * @author Slava
 */
public class JdbcCompanyDAO implements CompanyDAO {

    /**
     * A pattern of an SQL command (without particular values)
     * for saving a company in a database
     */
    private final static String SAVE = "INSERT INTO companies (name) VALUES(?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a company in a database by id
     */
    private final static String FIND_BY_ID = "SELECT * FROM companies WHERE id = ?";

    /**
     * A pattern of an SQL command (without particular values)
     * for update a company in a database
     */
    private final static String UPDATE = "UPDATE companies SET name = ? WHERE id = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for removing a company from a database by id
     */
    private final static String DELETE = "DELETE FROM companies WHERE id = ?";

    /**
     * An SQL command for getting all companies from a database
     */
    private final static String FIND_ALL = "SELECT * FROM companies";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a company in a database by name
     */
    private final static String FIND_BY_NAME = "SELECT * FROM companies WHERE name = ? ";

    /**
     * A pattern of an SQL command  for finding a id from the last
     * inserted company in a database
     */
    private final static String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";

    /**
     * a connection to a database
     */
    private ConnectionDB connectionDB;

    /**
     * * Constructor.
     *
     * @param connectionDB a connection to a database
     */
    public JdbcCompanyDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Method finds a company in database by name of developer
     *
     * @param companyName a name of a company
     * @return a company with entered name
     * or new company with empty parameters if company with this name does not exist
     */
    @Override
    public Company findByName(String companyName) {
        Company company = new Company(0, companyName);
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, companyName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
            return company;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method saves a new company in the database
     *
     * @param obj a company, which must be save in the database
     * @return company`s id if the company was add to database successfully
     */
    @Override
    public Long save(Company obj) {
        Long id;
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
                Statement statement = connection.createStatement()) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getLong(1);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method finds a company in database by id
     *
     * @param aLong an id of a company
     * @return a company with entered id
     * or new company with empty parameters if company with this id does not exist
     */
    @Override
    public Company findById(Long aLong) {
        Company foundedCompany = new Company(aLong, "");
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundedCompany = new Company(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
            return foundedCompany;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method updates a company in the database
     *
     * @param company a company with new name
     */
    @Override
    public void update(Company company) {
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setLong(2, company.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method removes a company from database
     *
     * @param obj company which must be removed
     */
    @Override
    public void delete(Company obj) {
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns all companies from the database
     *
     * @return list of all companies from the database
     */
    @Override
    public List<Company> findAll() {
        List<Company> allCompanies = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allCompanies.add(
                        new Company(
                                resultSet.getLong("id"),
                                resultSet.getString("name")
                        )
                );
            }
            return allCompanies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
