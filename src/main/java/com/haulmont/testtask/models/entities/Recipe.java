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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    public Recipe() {
    }

    public Recipe(String name, String value, RecipePriority priority, User creator) {
        this.name = name;
        this.value = value;
        this.priority = priority;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", priority=" + priority +
                '}';
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
