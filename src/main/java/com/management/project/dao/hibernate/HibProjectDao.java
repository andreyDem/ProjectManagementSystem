package com.management.project.dao.hibernate;

import com.management.project.dao.ProjectDAO;
import com.management.project.models.Company;
import com.management.project.models.Customer;
import com.management.project.models.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implements a set of methods for working
 * with database including Hibernate framework, with Project entity
 *
 * @author Slava Makhinich
 */
public class HibProjectDao implements ProjectDAO {

    /**
     * An instance of SessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param sessionFactory an instance of SessionFactory
     */
    public HibProjectDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * The method saves a new project in a database
     *
     * @param project a project, which must be save in a database
     * @return the projects id if a project was add to database successfully
     */
    @Override
    public Long save(Project project) {
        Long id = null;
        try (Session session = sessionFactory.openSession()) {
            id = (Long) session.save(project);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to save project " + project);
            e.printStackTrace();
        }
        return id;
    }

    /**
     * The method finds a project in database by id of the project
     *
     * @param id an id of a project
     * @return a project with entered id
     * or project with empty parameters if project with this id does not exist in the database
     */
    @Override
    public Project findById(Long id) {
        Project project =
                new Project(id, "", 0, new Company(0, ""), new Customer(0, ""));
        try (Session session = sessionFactory.openSession()) {
            Project projectFromDB = session.get(Project.class, id);
            if (projectFromDB != null) {
                project = projectFromDB;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find project with id: " + id);
            e.printStackTrace();
        }
        return project;
    }

    /**
     * The method updates a project in a database
     * (finds project in a database by id and overwrites other fields)
     *
     * @param project is a project with new parameters
     */
    @Override
    public void update(Project project) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Project projectFromDb = session.get(Project.class, project.getId());
            if (projectFromDb == null) {
                return;
            }
            projectFromDb.setName(project.getName());
            projectFromDb.setCost(project.getCost());
            projectFromDb.setCompany(project.getCompany());
            projectFromDb.setCustomer(project.getCustomer());
            session.update(projectFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to update project " + project);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * The method removes a project from a database
     *
     * @param project is a project which must be removed
     */
    @Override
    public void delete(Project project) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Project projectFromDb = session.get(Project.class, project.getId());
            if (projectFromDb == null) {
                return;
            }
            session.delete(projectFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to delete project " + project);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
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
        try (Session session = sessionFactory.openSession()) {
            projects = session.createQuery("from Project").list();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find all projects");
            e.printStackTrace();
        }
        return projects;
    }

    /**
     * Method finds a project in a database by name of the project
     *
     * @param name is a name of a project
     * @return a project with entered name
     * or project with empty parameters if project with this name does not exist in the database
     */
    @Override
    public Project findByName(String name) {
        Project project =
                new Project(0, name, 0, new Company(0, ""), new Customer(0, ""));
        try (Session session = sessionFactory.openSession()) {
            List<Project> projects = session.createQuery("select c from Project c where c.name like :name")
                    .setParameter("name", name).list();
            if (projects.size() != 0) {
                project = projects.get(0);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find project with name: " + name);
            e.printStackTrace();
        }
        return project;
    }
}
