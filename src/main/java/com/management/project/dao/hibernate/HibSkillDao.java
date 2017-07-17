package com.management.project.dao.hibernate;

import com.management.project.dao.SkillDAO;
import com.management.project.models.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * * The class implements a set of methods for working
 * with database including Hibernate framework, with Skill entity
 *
 * @author Вадим
 */
public class HibSkillDao implements SkillDAO {

    /**
     * An instance of SessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param sessionFactory an instance of SessionFactory
     */
    public HibSkillDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * The method saves a new skill in a database
     *
     * @param obj a skill, which must be save in a database
     * @return skill`s id if a skill was add to database successfully
     */
    @Override
    public Long save(Skill obj) {
        Long id;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            id = (Long) session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    /**
     * The method finds a skill in database by id of skill
     *
     * @param aLong an id of a skill
     * @return a skill with entered id
     * or new skill with empty parameters if the skill with this id does not exist in the database
     */
    @Override
    public Skill findById(Long aLong) {
        Skill skill = new Skill(aLong, "");
        try (Session session = sessionFactory.openSession()) {
            Skill skillFromDB = session.get(Skill.class, aLong);
            if (skillFromDB != null) {
                skill = skillFromDB;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    /**
     * The method updates a skill in a database
     * (finds skill in a database by id and overwrites other fields)
     *
     * @param obj is a skill with new parameters
     */
    @Override
    public void update(Skill obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Skill skillToUpdate = session.get(Skill.class, obj.getId());
            if (skillToUpdate != null) {
                skillToUpdate.setName(obj.getName());
                session.update(skillToUpdate);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The method removes a skill from a database
     *
     * @param obj is a skill which must be removed
     */
    @Override
    public void delete(Skill obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Skill skillToDelete = session.get(Skill.class, obj.getId());
            if (skillToDelete != null) {
                session.delete(skillToDelete);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The method returns all skills from a database
     *
     * @return list of all skills from a database
     */
    @Override
    public List<Skill> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Skill").list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method finds a skill in a database by name of the skill
     *
     * @param name is a name of a skill
     * @return a skill with entered name
     * or new skill with empty parameters if the skill with this id does not exist in the database
     */
    @Override
    public Skill findByName(String name) {
        Skill skill = new Skill(0, "");
        try (Session session = sessionFactory.openSession()) {
            Skill skillFromDB = (Skill) session.createQuery("from Skill s where s.name like :name")
                    .setParameter("name", name).uniqueResult();
            if (skillFromDB != null) {
                skill = skillFromDB;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return skill;
    }
}
