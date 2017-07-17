package com.management.project.dao.jdbc;

import com.management.project.connections.ConnectionDB;
import com.management.project.dao.SkillDAO;
import com.management.project.models.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class implements a set of methods for working
 * with database, with Skill entity.
 *
 * @author Вадим
 */
public class JdbcSkillDAO implements SkillDAO {

    /**
     * A pattern of an SQL command (without particular values)
     * for saving a skill in a database
     */
    private final static String SAVE = "INSERT INTO skills (name) VALUES(?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a skill in a database by id
     */
    private final static String FIND_BY_ID = "SELECT * FROM skills WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular values)
     * for update a skill in a database
     */
    private final static String UPDATE = "UPDATE skills SET name = ? WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for removing a skill from a database by id
     */
    private final static String DELETE = "DELETE FROM skills WHERE ID = ?";

    /**
     * An SQL command for getting all skills from a database
     */
    private final static String FIND_ALL = "SELECT * FROM skills";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a skill in a database by name
     */
    private final static String FIND_BY_NAME = "SELECT * FROM skills WHERE NAME = ? ";

    /**
     * A pattern of an SQL command  for finding a id
     * from the last inserted skill in a database
     */
    private final static String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";

    /**
     * a connection to database
     */
    private ConnectionDB connectionDB;

    /**
     * * Constructor.
     *
     * @param connectionDB a connection to database
     */
    public JdbcSkillDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Method adds a new skill to database
     *
     * @param obj a skill, which must to be store in the database
     * @return id of skill if skill was add to database successfully
     */
    @Override
    public Long save(Skill obj) {
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
     * Method finding skill in database by id of skill
     *
     * @param aLong id of skill
     * @return a skill with entered id
     * or new skill with empty parameters if skill with this id does not exist
     */
    @Override
    public Skill findById(Long aLong) {
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return new Skill(aLong, "");
            }
            return createSkill(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method updates name of skill
     *
     * @param obj skill with new name
     */
    @Override
    public void update(Skill obj) {
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method removes skill from database
     *
     * @param obj skill which must be removed
     */
    @Override
    public void delete(Skill obj) {
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns all skills from database
     *
     * @return list of all skills from database
     */
    @Override
    public List<Skill> findAll() {
        List<Skill> skillList = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                Skill skill = createSkill(resultSet);
                skillList.add(skill);
            }
            return skillList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method looking skill in database for name of skill
     *
     * @param name a name of skill
     * @return a skill with entered name
     * or new skill with empty parameters if skill with this name does not exist
     */
    public Skill findByName(String name) {
        try (Connection connection = connectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return new Skill(0, name);
            }
            return createSkill(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method creates new skill
     *
     * @param resultSet a set of result from statement query
     * @return a skill, which was created on the basis of resultSet
     * @throws SQLException in case of connection problems
     */
    private static Skill createSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setName(resultSet.getString("name"));
        return skill;
    }
}