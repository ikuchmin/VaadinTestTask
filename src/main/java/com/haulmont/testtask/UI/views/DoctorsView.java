package com.haulmont.testtask.UI.views;

import com.haulmont.testtask.UI.dialogs.UserEditDialog;
import com.haulmont.testtask.UI.models.DialogEventType;
import com.haulmont.testtask.models.RoleType;
import com.haulmont.testtask.models.entities.User;
import com.haulmont.testtask.repositories.RoleRepository;
import com.haulmont.testtask.repositories.UserRepository;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@SpringComponent
@DesignRoot("/templates/views/UsersView.html")
public class DoctorsView extends VerticalLayout implements View {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private RoleType roleType = RoleType.DOCTOR;

    private Grid<User> grid;

    //toolbar
    private HorizontalLayout toolbar;
    private Button addButton;

    public DoctorsView() {
        Design.read(this);
        initializeGrid();
        initializeToolbar();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (roleType == null) {
            Notification.show("View role type doesn't set");
            return;
        }
        loadDataToGrid();
    }

    private void addUserButton(Button.ClickEvent clickEvent) {
        Window addDialog = new UserEditDialog(roleType, event -> {
            if (event.type == DialogEventType.SUBMIT) {
                userRepository.save(event.model);
                loadDataToGrid();
            }
            //UI.getCurrent().removeWindow(addDialog);
        }, roleRepository.findByName(RoleType.DOCTOR));
        UI.getCurrent().addWindow(addDialog);
    }

    private void removeUserButton(User user) {
        ConfirmDialog.show(UI.getCurrent(),
                "Confirm deletion",
                "Item will be deleted permanently",
                "Delete",
                "Cancel",
                confirmDialog -> {
                    if (confirmDialog.isConfirmed()) {
                        userRepository.delete(user);
                        loadDataToGrid();
                    }
                }
        );
    }

    private void editUserButton(User user) {
        Window addDialog = new UserEditDialog(roleType, user,
                event -> {
                    if (event.type == DialogEventType.SUBMIT) {
                        userRepository.save(event.model);
                        loadDataToGrid();
                    }
                    //UI.getCurrent().removeWindow(addDialog);
                },
                roleRepository.findByName(RoleType.DOCTOR)
        );
        UI.getCurrent().addWindow(addDialog);
    }

    private void initializeToolbar() {
        addButton.addClickListener(this::addUserButton);
    }

    private void initializeGrid() {
        grid.addColumn(User::getFirstName).setCaption("First name");
        grid.addColumn(User::getMiddleName).setCaption("Middle name");
        grid.addColumn(User::getLastName).setCaption("Second name");
        grid.addColumn(User::getPhone).setCaption("Phone");
        grid.addColumn(User::getSpecialization).setCaption("Specialization");
        grid.addComponentColumn(user -> {
            Button b = new Button("", clickEvent -> this.editUserButton(user));
            b.setIcon(VaadinIcons.PENCIL);
            return b;
        });
        grid.addComponentColumn(user -> {
            Button b = new Button("", clickEvent -> this.removeUserButton(user));
            b.setIcon(VaadinIcons.TRASH);
            return b;
        });
    }

    // todo ручное заполнение грида
    private void loadDataToGrid() {
        grid.setItems(userRepository.getAllUsersWithRole(roleType));
    }
}
