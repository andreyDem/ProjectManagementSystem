package com.management.project.models;

import javax.persistence.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the Project.
 *
 * @author Aleksey
 */
@Entity
@Table(name = "projects", schema = "projectManagementDB")
public class Project implements Model {

    /**
     * The unique identifier for each project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of this project.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The cost of this project.
     */
    @Column(name = "cost")
    private int cost;

    /**
     * The company executor of this project.
     */
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * The customer of this project.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Default constructor
     */
    public Project() {
    }

    /**
     * Constructor
     *
     * @param name     a name to the new project.
     * @param cost     a cost of the new project.
     * @param company  a company executor of the new project.
     * @param customer a customer of the new project.
     */
    public Project(String name, int cost, Company company, Customer customer) {
        this.name = name;
        this.cost = cost;
        this.company = company;
        this.customer = customer;
    }

    /**
     * Constructor
     *
     * @param id       a unique identifier for the new project.
     * @param name     a name to the new project.
     * @param cost     a cost of the new project.
     * @param company  a company executor of the new project.
     * @param customer a customer of the new project.
     */
    public Project(long id, String name, int cost, Company company, Customer customer) {
        this(name, cost, company, customer);
        this.id = id;
    }

    /**
     * Returns a string representation of the project.
     *
     * @return A string representation of the project.
     */
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", company=" + company +
                ", customer=" + customer +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this project is the same as the object
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
        Project project = (Project) object;
        if (cost != project.cost) {
            return false;
        }
        if (!name.equals(project.name)) {
            return false;
        }
        if (company != null ? !company.equals(project.company) : project.company != null) {
            return false;
        }
        return customer != null ? customer.equals(project.customer) : project.customer == null;
    }

    /**
     * Returns a hash code value for the project.
     *
     * @return A hash code value for this project.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + cost;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    /**
     * Getters and setters methods by all fields of Project
     **/
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost > 0 ? cost : 0;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
