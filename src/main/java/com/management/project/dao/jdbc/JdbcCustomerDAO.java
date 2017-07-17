package com.management.project.dao.jdbc;

import com.management.project.dao.CustomerDAO;
import com.management.project.models.Customer;
import com.management.project.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * * The class implements a set of methods
 * for working with database, with Customer entity.
 *
 * @author Pavel Perevoznyk
 */
public class JdbcCustomerDAO implements CustomerDAO {

    /**
     * A pattern of an SQL command (without particular value)
     * for saving a customer in a database
     */
    private static final String SAVE = "INSERT INTO customers (NAME) VALUES(?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a customer in a database by id
     */
    private static final String FIND_BY_ID = "SELECT * FROM customers WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for update a customer in a database
     */
    private static final String UPDATE = "UPDATE customers SET NAME = ? WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for removing a customer from a database
     */
    private static final String DELETE = "DELETE FROM customers WHERE ID = ?";

    /**
     * An SQL command for getting all customers from a database
     */
    private static final String FIND_ALL = "SELECT * FROM customers";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a customer in a database by name
     */
    private static final String FIND_BY_NAME = "SELECT * FROM customers WHERE NAME = ?";

    /**
     * a connection to a database
     */
    private Connection connection;

    /**
     * * Constructor.
     *
     * @param connection a connection to a database
     */
    public JdbcCustomerDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method saves a new project in a database
     *
     * @param obj a customer, which must be save in a database
     * @return customers id if a customer was add to database successfully
     */
    @Override
    public Long save(Customer obj) {
        Long id;
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(SAVE);
                PreparedStatement preparedStatement2 = connection.prepareStatement(Constants.GET_LAST_ID)) {
            preparedStatement1.setString(1, obj.getName());
            preparedStatement1.execute();
            ResultSet rs = preparedStatement2.executeQuery();
            rs.next();
            id = rs.getLong(1);
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    /**
     * The method finds a customer in a database by id of customer
     *
     * @param id an id of a customer
     * @return a customer with entered id
     * or new customer with empty parameters if customer with this id does not exist
     */
    @Override
    public Customer findById(Long id) {
        Customer customer = new Customer(id, "");
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = buildCustomersFromResultSet(resultSet);
            if (customers.size() > 0) {
                customer = customers.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    /**
     * The method updates a customer in a database
     * (finds customer in a database by id and overwrites other fields)
     *
     * @param obj customer with new name
     */
    @Override
    public void update(Customer obj) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The method removes a customer from a database
     *
     * @param obj customer which must be removed
     */
    @Override
    public void delete(Customer obj) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The method returns all customers from a database
     *
     * @return list of all customers from a database
     */
    @Override
    public List<Customer> findAll() {
        List<Customer> customers;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            customers = buildCustomersFromResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    /**
     * Method finds a customer in database by name of the customer
     *
     * @param name a name of a customer
     * @return a customer with entered name
     * or new customer with empty parameters if customer with this name does not exist
     */
    @Override
    public Customer findByName(String name) {
        Customer customer = new Customer(0, name);
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = buildCustomersFromResultSet(resultSet);
            if (customers.size() > 0) {
                customer = customers.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    /**
     * The method builds a list of customers from resultSet
     * (set that we get after execution SQL query)
     *
     * @param rs set that we get after execution SQL query
     * @return a list of all customers from a database
     * @throws SQLException in case of connection problems
     */
    private static List<Customer> buildCustomersFromResultSet(ResultSet rs) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Customer customer;
        while (rs.next()) {
            customer = new Customer(rs.getLong("id"), rs.getString("name"));
            customers.add(customer);
        }
        return customers;
    }
}
