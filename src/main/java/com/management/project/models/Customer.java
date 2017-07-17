package com.management.project.models;

import javax.persistence.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the Customer.
 *
 * @author Aleksey
 */
@Entity
@Table (name = "customers")
public class Customer implements Model {

    /**
     * The unique identifier for each customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of this customer.
     */
    @Column (name = "name")
    private String name;

    /**
     * Default Constructor
     */
    public Customer() {
    }

    /**
     * Constructor
     *
     * @param name a name to the new customer.
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Constructor
     *
     * @param id   a unique identifier for the new customer.
     * @param name a name to the new customer.
     */
    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns a string representation of the customer.
     *
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customer='" + name + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this customer is the same as the object
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
        Customer customer = (Customer) object;
        return name.equals(customer.name);
    }

    /**
     * Returns a hash code value for the customer.
     *
     * @return A hash code value for this customer.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Getters and setters methods by all fields of Customer.
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
