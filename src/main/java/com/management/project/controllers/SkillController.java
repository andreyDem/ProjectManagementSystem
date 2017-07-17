package com.management.project.controllers;

import com.management.project.dao.GenericDAO;
import com.management.project.models.Skill;

import java.util.Scanner;

/**
 * An controller for working with Skill entity
 *
 * @author Вадим
 */
public class SkillController extends AbstractModelController<Skill> {

    /**
     * Constructor
     *
     * @param dao An instance of GenericDAO<Skill, Long> for working with database with Project entity
     */
    public SkillController(GenericDAO<Skill, Long> dao) {
        super(dao);
    }

    /**
     * Method for creating new skills. The method asks user for skill's name, and return a new skill with this name
     *
     * @return a new skill with name that the user inputted
     */
    @Override
    protected Skill getNewModel() {
        System.out.print("Input skill name: ");
        String skillName = new Scanner(System.in).nextLine();
        return new Skill(-100, skillName);
    }

    /**
     * The method prints menu of this controller
     */
    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("ACTIONS WITH SKILLS:");
        super.printMenu();
    }
}


