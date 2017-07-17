package com.management.project.models;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author Slava Makhinich
 */
public class DeveloperTest {
    private Developer developer;
    private Company company;
    private Project project;

    @Test
    public void getId() throws Exception {
        developer = new Developer(123L, "Nata", 60000);
        assertEquals(123L, developer.getId());
    }

    @Test
    public void setId() throws Exception {
        developer = new Developer(123L, "Nata", 60000);
        developer.setId(555);
        assertTrue(developer.getId() == 555);
    }

    @Test
    public void getName() throws Exception {
        developer = new Developer("developer");
        assertEquals("developer", developer.getName());
    }

    @Test
    public void setName() throws Exception {
        developer = new Developer("developer");
        developer.setName("new developer");
        assertEquals("new developer", developer.getName());
        developer.setName(null);
        assertEquals("", developer.getName());
    }

    @Test
    public void getCompany() throws Exception {
        company = new Company(10001, "Apple");
        developer = new Developer(123, "Slava", company, null, 3000);
        assertTrue(company == developer.getCompany());
    }

    @Test
    public void setCompany() throws Exception {
        company = new Company(10001, "Apple");
        developer = new Developer(123, "Slava", null, null, 3000);
        developer.setCompany(company);
        assertTrue(developer.getCompany() == company);
    }

    @Test
    public void getProject() throws Exception {
        project = new Project(7000, "NewGame", 3000, null, null);
        developer = new Developer(1000, "Vova", null, project, 10000);
        assertTrue(developer.getProject() == project);
    }

    @Test
    public void setProject() throws Exception {
        project = new Project(7000, "NewGame", 3000, null, null);
        developer = new Developer(1200, "Igor", null, null, 3000);
        developer.setProject(project);
        assertTrue(developer.getProject() == project);
    }

    @Test
    public void getSalary() throws Exception {
        developer = new Developer(5000, "Joe", 859);
        assertEquals(859, developer.getSalary());
    }

    @Test
    public void setSalary() throws Exception {
        developer = new Developer("Joe");
        developer.setSalary(6001);
        assertEquals(6001, developer.getSalary());
        developer.setSalary(-123);
        assertEquals(developer.getSalary(), 0);
    }

    @Test
    public void getSkills() throws Exception {
        HashSet<Skill> skills = new HashSet<>();
        developer = new Developer(1L, "Den", null, null, 1, skills);
        assertTrue(skills == developer.getSkills());
    }

    @Test
    public void setSkills() throws Exception {
        developer = new Developer();
        HashSet<Skill> skills = new HashSet<>();
        skills.add(new Skill(1,"wqaef"));
        developer.setSkills(skills);
        assertTrue(developer.getSkills().equals(skills));
    }

    @Test
    public void setSkillsNull() throws Exception {
        developer = new Developer();
        HashSet<Skill> skills = null;
        developer.setSkills(skills);
        assertNotNull(developer.getSkills());
    }

    @Test
    public void addSkill() throws Exception {
        HashSet<Skill> skills = new HashSet<>();
        developer = new Developer(1L, "Den", null, null, 1, skills);
        Skill skill = new Skill(1,"Java");
        assertTrue(developer.addSkill(skill));
        assertFalse(developer.addSkill(skill));
        assertTrue(developer.getSkills().contains(skill));

        assertFalse(developer.addSkill(null));
    }

    @Test
    public void removeSkill() throws Exception {
        Skill skill = new Skill(1,"Java");
        HashSet<Skill> skills = new HashSet<>();
        skills.add(skill);
        developer = new Developer(1, "SomeName", null, null, 333, skills);
        assertTrue(developer.removeSkill(skill));
        assertFalse(developer.getSkills().contains(skill));
        assertFalse(developer.removeSkill(skill));

        Skill skillNull = null;
        assertFalse(developer.removeSkill(skillNull));
    }

    @Test
    public void equals() throws Exception {
        company = new Company(1, "BMW");
        project = new Project(7000, "NewGame", 3000, null, null);
        developer = new Developer(100, "Pavel", company, project, 2000);
        Developer developer1 = new Developer(50, "Pavel", company, project, 2000);
        Developer developer2 = new Developer(150, "Pavel", company, project, 2000);
        //reflexive
        assertTrue(developer.equals(developer));
        //symmetric
        assertTrue(developer1.equals(developer));
        assertTrue(developer.equals(developer1));
        //transitive
        assertTrue(developer.equals(developer2));
        assertTrue(developer1.equals(developer2));
        //consistent
        assertTrue(developer1.equals(developer));
        assertTrue(developer1.equals(developer));
        developer1.setName("Petr");
        assertFalse(developer1.equals(developer));
        developer1.setName("Pavel");
        assertEquals(developer1,developer);
        developer.setCompany(null);
        assertNotEquals(developer1, developer);
        assertNotEquals(developer, developer1);
        developer1.setCompany(null);
        assertEquals(developer1, developer);
        developer1.setSalary(1000);
        assertNotEquals(developer1, developer);
        developer.setSalary(1000);
        assertFalse(developer.equals(null));
        assertFalse(developer.equals(new String("developer")));
        developer1.setProject(new Project(1, "Some Project", 159, null, null));
        assertFalse(developer1.equals(developer));
        developer1.setProject(null);
        assertFalse(developer1.equals(developer));
        developer.setProject(null);
        assertTrue(developer1.equals(developer));
    }

    @Test
    public void testHashCode() throws Exception {
        company = new Company(1, "BMW");
        project = new Project(7000, "NewGame", 3000, null, null);
        developer = new Developer(100, "Pavel", company, project, 2000);
        Developer developer1 = new Developer(500, "Pavel", company, project, 2000);
        assertTrue(developer.hashCode() == developer.hashCode());
        assertTrue(developer.hashCode() == developer1.hashCode());
        developer1.setCompany(null);
        assertFalse(developer1.hashCode() == developer.hashCode());
        developer.setCompany(null);
        assertTrue(developer1.hashCode() == developer.hashCode());
        developer1.setProject(null);
        assertFalse(developer1.hashCode() == developer.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        developer = new Developer(12345, "Dima", 5000);
        developer.addSkill(new Skill(1, "java"));
        assertTrue(developer.toString().contains("Dima"));
        assertTrue(developer.toString().contains("12345"));
        assertTrue(developer.toString().contains("5000"));
        assertTrue(developer.toString().contains("java"));
    }
}