package com.management.project.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest {

    private Company testCompany = new Company(4566, "Hair Fair");

    @Test
    public void getId() {
        assertTrue(testCompany.getId() == 4566);
    }

    @Test
    public void setId() {
        testCompany.setId(5555);
        assertTrue(testCompany.getId() == 5555);
    }

    @Test
    public void getName() {
        assertTrue(testCompany.getName().equals("Hair Fair"));
    }

    @Test
    public void setName() {
        testCompany.setName("British Airlines");
        assertTrue(testCompany.getName().equals("British Airlines"));
    }

    @Test
    public void setNullName() {
        testCompany.setName(null);
        assertNotNull(testCompany.getName());
    }

    @Test
    public void equals() {
        Company some2 = new Company(4566, "Hair Fair");
        assertEquals(some2, testCompany);
        assertNotEquals(testCompany,null);
        assertFalse(testCompany.equals(new Integer(2)));
    }

    @Test
    public void testToString() {
        assertTrue(testCompany.toString().contains("Hair Fair"));
        assertTrue(testCompany.toString().contains("4566"));
    }
}
