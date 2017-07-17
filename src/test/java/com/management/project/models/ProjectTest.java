package com.management.project.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectTest {
    private Project project = new Project(1, "Android app", 3000, new Company(1, "Luxsoft"),
            new Customer(1, "Bayer"));

    @Test
    public void getId() throws Exception {
        assertTrue(project.getId() == 1);
    }

    @Test
    public void setId() throws Exception {
        project.setId(12);
        assertTrue(project.getId() == 12);
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Android app", project.getName());
    }

    @Test
    public void setName() throws Exception {
        project.setName("Clear service");
        assertEquals("Clear service", project.getName());
        project.setName(null);
        assertEquals("", project.getName());
    }

    @Test
    public void getCost() throws Exception {
        assertTrue(project.getCost() == 3000);
        project.setCost(-5);
        assertTrue(project.getCost() == 0);
    }

    @Test
    public void setCost() throws Exception {
        project.setCost(5000);
        assertTrue(project.getCost() == 5000);
    }

    @Test
    public void getCompany() throws Exception {
        assertEquals(new Company(1, "Luxsoft"), project.getCompany());
    }

    @Test
    public void setCompany() throws Exception {
        Company company = new Company(2, "Rozetka");
        project.setCompany(company);
        assertEquals(new Company(2, "Rozetka"), project.getCompany());
        project.setCustomer(null);
        assertTrue(project.getCompany() == company);
    }

    @Test
    public void getCustomer() throws Exception {
        assertEquals(new Customer(1, "Bayer"), project.getCustomer());
    }

    @Test
    public void setCustomer() throws Exception {
        Customer customer = new Customer(10, "Petrovi4");
        project.setCustomer(customer);
        assertEquals(new Customer(10, "Petrovi4"), project.getCustomer());
        project.setCompany(null);
        assertTrue(project.getCustomer() == customer);
    }

    @Test
    public void equals() throws Exception {
        Project project2 = new Project(1, "Android app", 3000, new Company(1, "Luxsoft"),
                new Customer(1, "Bayer"));
        assertEquals(project2, project);
        assertEquals(project, project2);
        assertFalse(project.equals(null));
        assertFalse(project.equals(new Integer(1)));
        project2.setName("Go");
        assertFalse(project.equals(project2));
        Project project1 = new Project(1, "Android app", 3000, new Company(1, "Luxsoft"),
                new Customer(1, "Bayer"));
        project1.setCompany(new Company(1,"Test"));
        assertNotEquals(project,project1);
        assertNotEquals(project1,project);
        project1.setCompany(null);
        assertNotEquals(project,project1);
        assertNotEquals(project1,project);
        project.setCompany(null);
        assertTrue(project.equals(project1));
        project1.setCustomer(new Customer(1,"Test"));
        assertNotEquals(project,project1);
        assertNotEquals(project1,project);
        project1.setCustomer(null);
        assertNotEquals(project,project1);
        assertNotEquals(project1,project);
        project.setCustomer(null);
        assertTrue(project.equals(project1));
    }

    @Test
    public void testHashCode() throws Exception {
        Project project2 = new Project(1, "Android app", 3000, new Company(1, "Luxsoft"),
                new Customer(1, "Bayer"));
        assertTrue(project2.hashCode() == project.hashCode());
        project2.setName("New app");
        assertFalse(project2.hashCode() == project.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(project.toString().contains("Android app"));
        assertTrue(project.toString().contains("3000"));
        assertTrue(project.toString().contains("Luxsoft"));
        assertTrue(project.toString().contains("Bayer"));
    }

}