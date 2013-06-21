package com.akqa.scheduler.model;

/**
 *
 * Model class for Employee
 * 
 * @author galenchik
 */
public class Employee {
    
    private String name;

    /**
     * 
     * @param name - Employee name
     */
    public Employee(String name) {
        this.name = name;
    }

    /**
     *
     * @return - Employee name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - Employee name
     */
    public void setName(String name) {
        this.name = name;
    }
}
