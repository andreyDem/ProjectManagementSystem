package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.models.Model;

import java.util.Scanner;

/**
 * Abstract class, parent for all model's controllers
 *
 * @author Slava Makhinich
 */
public abstract class AbstractModelController<T extends Model> extends AbstractController {

    /**
     * An instance of DAO for working with model and with database
     */
    protected final GenericDAO<T, Long> dao;

    /**
     * Constructor
     *
     * @param dao an instance of DAO for working with model and with database
     */
    public AbstractModelController(GenericDAO<T, Long> dao) {
        this.dao = dao;
    }

    /**
     * The method prints menu of this controller
     */
    @Override
    protected void printMenu() {
        System.out.println("1 - add new");
        System.out.println("2 - update");
        System.out.println("3 - find by id");
        System.out.println("4 - find by name");
        System.out.println("5 - delete");
        System.out.println("6 - show all");
        System.out.println("0 - go to main menu");
    }

    /**
     * The method invokes one of the others methods of this class.
     * The method that will be invoked depends on users choice, that we get from method readChoice()
     *
     * @param choice a choice of user, that we get from method readChoice()
     */
    @Override
    protected void action(int choice) {
        switch (choice) {
        case 1:
            addNew();
            break;
        case 2:
            update();
            break;
        case 3:
            findByIdAndOutput();
            break;
        case 4:
            findByNameAndOutput();
            break;
        case 5:
            deleteById();
            break;
        case 6:
            showAll();
            break;
        }
    }

    /**
     * The method finds all models with particular type in database (uses DAO for this), and output them
     */
    protected void showAll() {
        dao.findAll().forEach(System.out::println);
    }

    /**
     * Method for removing models from database.
     * The method finds model in database by id (uses DAO for this),
     * and removes it (uses DAO for this), if it was founded
     */
    protected void deleteById() {
        System.out.print("Delete by id. Input id: ");
        long id = new Scanner(System.in).nextLong();
        T model = dao.findById(id);
        if (model == null) {
            System.out.println("Model with this id is not found");
            return;
        }
        System.out.println("Delete: " + model);
        dao.delete(model);
    }

    /**
     * The method gets a new model from method getNewModel(), and saves the model in database (uses DAO for this)
     */
    protected void addNew() {
        T model = getNewModel();
        System.out.println("Id of new model: " + dao.save(model));
    }

    /**
     * Method for updating models.
     * The method asks user for id of model, finds model with this id in database (uses DAO for this),
     * gets a new model from method getNewModel(), sets old id for this model,
     * and saves the model in database (uses DAO for this)
     */
    protected void update() {
        System.out.print("Update. Input id: ");
        long id = new Scanner(System.in).nextLong();
        T oldModel = dao.findById(id);
        if (oldModel == null) {
            System.out.println("Model with this id is not found");
            return;
        }
        System.out.println("Input new information for " + oldModel);
        T model = getNewModel();
        model.setId(id);
        System.out.println("New data: ");
        System.out.println(model);
        dao.update(model);
    }

    /**
     * The method finds a model in database by id (uses DAO for this), and outputs founded model
     */
    protected void findByIdAndOutput() {
        System.out.print("Find by id. Input id: ");
        long id = new Scanner(System.in).nextLong();
        T model = dao.findById(id);
        System.out.println(model);
    }

    /**
     * The method finds a model in database by name (uses DAO for this), and outputs founded model
     */
    protected void findByNameAndOutput() {
        System.out.print("Find by name. Input name: ");
        String name = new Scanner(System.in).nextLine();
        T model = dao.findByName(name);
        System.out.println(model);
    }

    /**
     * Method for creating new developers. Needs to be implemented.
     *
     * @return a new model
     */
    protected abstract T getNewModel();
}
