package com.management.project.controllers;

import com.management.project.dao.CompanyDAO;
import com.management.project.dao.GenericDAO;
import com.management.project.dao.ProjectDAO;
import com.management.project.dao.SkillDAO;
import com.management.project.models.Company;
import com.management.project.models.Developer;
import com.management.project.models.Project;
import com.management.project.models.Skill;

import java.util.HashSet;
import java.util.Scanner;

/**
 * An controller for working with Developer entity
 *
 * @author Slava Makhinich
 */
public class DeveloperController extends AbstractModelController<Developer> {

    /**
     * An instance of SkillDAO for working with database with database with Skill entity
     */
    protected SkillDAO skillDAO;

    /**
     * An instance of CompanyDAO for working with database with Company entity
     */
    protected CompanyDAO companyDAO;

    /**
     * An instance of ProjectDAO for working with database with Project entity
     */
    protected ProjectDAO projectDAO;

    /**
     * Constructor
     *
     * @param dao An instance of GenericDAO<Developer, Long> for working with database with Project entity
     * @param skillDAO An instance of CompanyDAO for working with database with Skill entity
     * @param companyDAO An instance of CompanyDAO for working with database with Company entity
     * @param projectDAO An instance of CompanyDAO for working with database with Project entity
     */
    public DeveloperController(
            GenericDAO<Developer, Long> dao,
            SkillDAO skillDAO,
            CompanyDAO companyDAO, ProjectDAO projectDAO
    ) {
        super(dao);
        this.skillDAO = skillDAO;
        this.companyDAO = companyDAO;
        this.projectDAO = projectDAO;
    }

    /**
     * The method prints menu of this controller
     */
    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("ACTIONS WITH DEVELOPERS:");
        super.printMenu();
    }

    /**
     * Method for creating new developers. The method asks user for developer's parameters,
     * and return a new developer with these parameters
     *
     * @return a new developer with parameters that the user inputted
     */
    @Override
    protected Developer getNewModel() {
        System.out.print("Input developer name: ");
        String developerName = new Scanner(System.in).nextLine();
        System.out.println("Input developer salary: ");
        int salary = new Scanner(System.in).nextInt();
        System.out.println("Input company id: ");
        long companyId = new Scanner(System.in).nextLong();
        System.out.println("Input project id: ");
        long projectId = new Scanner(System.in).nextLong();
        System.out.print("Input skill ids (example: 1,2,3... ): ");
        String skillIdLine = new Scanner(System.in).nextLine().replaceAll(" ", "");
        HashSet<Skill> skillsSet = new HashSet<>();
        for (String skillId : skillIdLine.split(",")) {
            Skill skill = skillDAO.findById(Long.parseLong(skillId));
            if (skill != null) {
                skillsSet.add(skill);
            }
        }
        Company company = companyDAO.findById(companyId);
        Project project = projectDAO.findById(projectId);
        return new Developer(-100, developerName, company, project, salary, skillsSet);
    }
}
