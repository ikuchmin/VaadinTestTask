package com.haulmont.testtask.UI.views;

import com.haulmont.testtask.models.User;
import com.haulmont.testtask.repositories.RoleRepository;
import com.haulmont.testtask.repositories.UserRepository;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import org.springframework.beans.factory.annotation.Autowired;


@SpringComponent
//@VaadinSessionScope
@DesignRoot(value = "/templates/DoctorsView.html")
public class DoctorsView extends VerticalLayout implements View {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private Panel toolbar;
    private Grid<User> grid;

    public DoctorsView() {
        Design.read(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadDataToGrid();
    }

    private void loadDataToGrid() {
        grid.setItems(userRepository.getAllUsersWithRole("doctor"));
        grid.addColumn(User::getFirstName).setCaption("First name");
        grid.addColumn(User::getSecondName);
        grid.addColumn(User::getMiddleName);
        grid.addColumn(User::getPhone);
        grid.addColumn(User::getSpecialization);
    }
}