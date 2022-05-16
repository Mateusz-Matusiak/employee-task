package com.example.employeetask.employee;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;

    public Employee() {}

    public Employee(String firstName, String lastName, Long departmentId, String job_title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = job_title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long department_id) {
        this.departmentId = department_id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String job_title) {
        this.jobTitle = job_title;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department_id=" + departmentId +
                ", job_title='" + jobTitle + '\'' +
                '}';
    }
}
