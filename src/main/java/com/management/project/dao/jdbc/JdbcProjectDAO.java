package com.management.project.dao.jdbc;

import com.management.project.dao.*;
import com.management.project.dao.ProjectDAO;
import com.management.project.factory.FactoryDao;
import com.management.project.models.Company;
import com.management.project.models.Customer;
import com.management.project.models.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.management.project.utils.Constants.*;

/**
 * The class implements a set of methods for working
 * with database, with Project entity.
 *
 * @author Slava Makhinich
 */
public class JdbcProjectDAO implements ProjectDAO {

    /**
     * A pattern of an SQL command (without particular values)
     * for saving a project in a database
     */
    private static final String SAVE = "INSERT INTO projects (name, cost, company_id, customer_id) VALUES(?, ?, ?, ?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a project in a database by id
     */
    private static final String FIND_BY_ID = "SELECT * FROM projects WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular values)
     * for update a project in a database
     */
    private static final String UPDATE = "UPDATE projects SET name  = ?, cost = ?, company_id = ?, customer_id = ? WHERE id = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for removing a project from a database by id
     */
    private static final String DELETE = "DELETE FROM projects WHERE ID = ?";

    /**
     * An SQL command for getting all projects from a database
     */
    private static final String FIND_ALL = "SELECT * FROM projects";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a project in a database by name
     */
    private static final String FIND_BY_NAME = "SELECT * FROM projects WHERE NAME LIKE ?";

    /**
     * an instance of JdbcCompanyDAO
     */
    private CompanyDAO companyDAO;

    /**
     * an instance of JdbcCustomerDAO
     */
    private CustomerDAO customerDAO;

    /**
     * a connection to a database
     */
    private Connection connection;

    /**
     * Constructor.
     *
     * @param connection @param connection a connection to a database
     * @throws SQLException in case of connection problems
     */
    public JdbcProjectDAO(Connection connection) throws SQLException {
        this.connection = connection;
        companyDAO = FactoryDao.getCompanyDAO();
        customerDAO = FactoryDao.getCustomerDAO();
    }

    /**
     * Method finds a project in a database by name of the project
     *
     * @param name is a name of a project
     * @return a project with entered name
     * or new project with empty parameters if project with this name does not exist in the database
     */
    @Override
    public Project findByName(String name) {
        Project project =
                new Project(0, name, 0, new Company(0, ""), new Customer(0, ""));
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projects = buildProjectsFromResultSet(resultSet);
            if (projects.size() > 0) {
                project = projects.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(
                    "SQL exception occurred while trying to retrieve " +
                            "Project with Name: " + name
            );
            e.printStackTrace();
        }
        return project;
    }

    /**
     * The method saves a new project in a database
     *
     * @param project a project, which must be save in a database
     * @return projects id if a project was add to database successfully
     */
    @Override
    public Long save(Project project) {
        Long id = null;
        try (PreparedStatement preparedStatementSave = connection.prepareStatement(SAVE);
                PreparedStatement preparedStatementGetLastId = connection.prepareStatement(GET_LAST_ID)) {
            preparedStatementSave.setString(1, project.getName());
            preparedStatementSave.setInt(2, project.getCost());
            preparedStatementSave.setLong(3, project.getCompany().getId());
            preparedStatementSave.setLong(4, project.getCustomer().getId());
            preparedStatementSave.execute();
            ResultSet resultSet = preparedStatementGetLastId.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(
                    "SQL exception occurred while trying to save Customer: " +
                            project.getName() + "\n" + e
            );
        }
        return id;
    }

    /**
     * The method finds a project in database by id of project
     *
     * @param id an id of a project
     * @return a project with entered id
     * or new project with empty parameters if project with this id does not exist
     */
    @Override
    public Project findById(Long id) {
        Project project =
                new Project(id, "", 0, new Company(0, ""), new Customer(0, ""));
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projects = buildProjectsFromResultSet(resultSet);
            if (projects.size() > 0) {
                project = projects.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(
                    "SQL exception occurred while trying to retrieve " +
                            "Project with Name: " + id
            );
            e.printStackTrace();
        }
        return project;
    }

    /**
     * The method updates a project in a database
     * (finds project in a database by id and overwrites other fields)
     *
     * @param project project with new parameters
     */
    @Override
    public void update(Project project) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCost());
            preparedStatement.setLong(3, project.getCompany().getId());
            preparedStatement.setLong(4, project.getCustomer().getId());
            preparedStatement.setLong(5, project.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(
                    "SQL exception occurred while trying to update Project with ID: "
                            + project.getId() + "\n" + e
            );
        }

    }

    /**
     * The method removes a project from a database
     *
     * @param project is a project which must be removed
     */
    @Override
    public void delete(Project project) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, project.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(
                    "SQL exception occurred while trying to delete Project with ID: "
                            + project.getId()
            );
            e.printStackTrace();
        }
    }

    /**
     * The method returns all projects from a database
     *
     * @return list of all projects from a database
     */
    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            projects = buildProjectsFromResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred while trying to find all Projects:");
            e.printStackTrace();
        }
        return projects;
    }

    /**
     * The method builds a list of projects from resultSet
     * (set that we get after execution SQL query)
     *
     * @param resultSet set that we get after execution SQL query
     * @return a list of all projects from a database
     * @throws SQLException in case of connection problems
     */
    private List<Project> buildProjectsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Project> projects = new ArrayList<>();
        Company company;
        Customer customer;
        long id;
        String name;
        int cost;
        while (resultSet.next()) {
            id = resultSet.getLong("id");
            name = resultSet.getString("name");
            cost = resultSet.getInt("cost");
            company = companyDAO.findById(resultSet.getLong("company_id"));
            customer = customerDAO.findById(resultSet.getLong("customer_id"));
            projects.add(new Project(id, name, cost, company, customer));
        }
        return projects;
    }
}
