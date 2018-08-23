package com.haulmont.testtask.models.entities;

import com.haulmont.testtask.models.RecipePriority;

import javax.persistence.*;


@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;
    private RecipePriority priority;

    public Recipe() {
    }

    public Recipe(String name, String value, RecipePriority priority) {
        this.name = name;
        this.value = value;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RecipePriority getPriority() {
        return priority;
    }

    public void setPriority(RecipePriority priority) {
        this.priority = priority;
    }
}
