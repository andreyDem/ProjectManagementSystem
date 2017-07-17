package com.management.project.models;

import javax.persistence.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the Company.
 *
 * @author Aleksey
 */

@Entity
@Table(name = "companies")
public class Company implements Model {

    /**
     * The unique identifier for each company.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of this company.
     */
    @Column(name = "name")
    private String name;

    /**
     * Constructor without parameters
     */
    public Company() {
    }

    /**
     * Constructor
     *
     * @param name a name to the new company.
     */
    public Company(String name) {
        this.name = name;
    }

    /**
     * Constructor
     *
     * @param id   a unique identifier for the new company.
     * @param name a name to the new company.
     */
    public Company(long id, String name) {
        this(name);
        this.id = id;
    }

    /**
     * Returns a string representation of the company.
     *
     * @return A string representation of the company.
     */
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", company='" + name + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this company is the same as the object
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Company company = (Company) object;
        return name.equals(company.name);
    }

    /**
     * Returns a hash code value for the company.
     *
     * @return A hash code value for this company.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Getters and setters methods by all fields of Company.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
    }
}
