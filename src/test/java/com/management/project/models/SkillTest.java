package com.management.project.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 03.03.2017.
 */
public class SkillTest {
    private Skill skill = new Skill(1912, "Java");

    @Test
    public void getId() throws Exception {
        assertTrue(skill.getId() == 1912);
    }

    @Test
    public void setId() throws Exception {
        skill.setId(2017);
        assertTrue(skill.getId() == 2017);
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Java", skill.getName());
    }

    @Test
    public void setName() throws Exception {
        skill.setName("Java Android");
        assertEquals("Java Android", skill.getName());
        skill.setName(null);
        assertEquals("", skill.getName());
    }

    @Test
    public void equals() throws Exception {
        Skill skill_test = new Skill(1912, "Java");
        assertEquals(skill_test, skill);
        assertEquals(skill,skill);
        Skill skill1 = new Skill();
        Skill skill2 = null;
        assertNotEquals(skill1,skill2);
        assertFalse(skill.equals(new Integer(2)));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Skill skill_test = skill;
        Skill skill_test_1 = new Skill(2017, "Java Android");
        assertTrue(skill_test.hashCode() == skill.hashCode());
        assertFalse(skill.hashCode() == skill_test_1.hashCode());
    }

    @Test
    public void toStringTest() throws Exception {
        assertTrue(skill.toString().contains("1912"));
        assertTrue(skill.toString().contains("Java"));
    }
}