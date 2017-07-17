package com.management.project.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The class implements a set of standard methods for working
 * with entity of the Developer.
 *
 * @author Вадим
 */
@Entity
@Table(name = "developers", schema = "projectManagementDB")
public class Developer implements Model {

    /**
     * The unique identifier for each developer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of this developer.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The company, which employs this developer.
     */
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * The project, that works the developer
     */
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The salary of this developer
     */
    @Column(name = "salary")
    private int salary;

    /**
     * Skills which have developer
     */
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "developers_skills",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    private Set<Skill> skills = new HashSet<Skill>();

    /**
     * Default constructor.
     */

    public Developer() {
    }

    /**
     * Constructor.
     *
     * @param name a name of the new developer.
     */
    public Developer(String name) {
        this.name = name;
    }

    /**
     * Constructor.
     *
     * @param id     The unique identifier for each developer.
     * @param name   a name of the new developer.
     * @param salary The salary of this developer
     */
    public Developer(long id, String name, int salary) {
        this(name);
        this.id = id;
        this.salary = salary;
    }

    /**
     * Constructor.
     *
     * @param id      The unique identifier for each developer.
     * @param name    a name of the new developer.
     * @param company The company, which employs this developer.
     * @param project The project, that works the developer
     * @param salary  The salary of this developer
     */
    public Developer(long id, String name, Company company, Project project, int salary) {
        this(id, name, salary);
        this.company = company;
        this.project = project;
    }

    /**
     * Constructor.
     *
     * @param id      The unique identifier for each developer.
     * @param name    a name of the new developer.
     * @param company The company, which employs this developer.
     * @param project The project, that works the developer
     * @param salary  The salary of this developer
     * @param skills  Skills that have developer
     */
    public Developer(long id, String name, Company company, Project project, int salary, Set<Skill> skills) {
        this(id, name, company, project, salary);
        this.skills = skills;
    }

    /**
     * Returns a string representation of the developer.
     *
     * @return A string representation of the developer.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Developer{")
                .append("id=").append(this.id)
                .append(", name=").append(this.name)
                .append(", salary=").append(this.salary)
                .append(", company=").append(this.company)
                .append(", project=").append(this.project)
                .append(", skills: ");
        for (Skill skill : this.skills) {
            sb.append(skill.getName()).append(", ");
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
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
        Developer developer = (Developer) object;
        if (salary != developer.salary) {
            return false;
        }
        if (!name.equals(developer.name)) {
            return false;
        }
        if (company != null ? !company.equals(developer.company) : developer.company != null) {
            return false;
        }
        return project != null ? project.equals(developer.project) : developer.project == null;

    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + salary;
        return result;
    }

    /**
     * Getters and setters methods by all fields of developer.
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary > 0 ? salary : 0;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(HashSet<Skill> skills) {
        if (skills != null) {
            this.skills = new HashSet<Skill>(skills);
        } else {
            this.skills = new HashSet<Skill>();
        }
    }

    /**
     * @param skill a skill that you need to add the developer
     * @return true if new skill was add  to developer successfully
     * or false if the addition failed
     */
    public boolean addSkill(Skill skill) {
        return (skill != null) && this.skills.add(skill);
    }

    /**
     * @param skill a skill that must be removed from developer
     * @return true if new skill was remove from developer successfully
     * or false if the removal failed
     */
    public boolean removeSkill(Skill skill) {
        return (skill != null) && this.skills.remove(skill);
    }
}