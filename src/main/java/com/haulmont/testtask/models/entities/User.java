package com.haulmont.testtask.models.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "users_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "creator")
    private List<Recipe> createdRecipes;

    public User() {

    }

    public User(String firstName, String middleName, String lastName, String phone, String specialization, Role role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.specialization = specialization;
        this.role = role;
    }

    public User(String firstName, String middleName, String lastName, String phone, Role role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", specialization='" + specialization + '\'' +
                ", role=" + this.getRole() +
                //", recipes=" + recipes +
                '}';
    }

    public String getInitials() {
        String initials = "";
        if (!firstName.isEmpty())
            initials += firstName.toUpperCase().charAt(0) + ". ";
        if (!middleName.isEmpty())
            initials += middleName.toUpperCase().charAt(0) + ". ";
        if (!lastName.isEmpty())
            initials += lastName;
        return initials;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(List<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }
}