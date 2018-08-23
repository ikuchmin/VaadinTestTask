package com.haulmont.testtask.UI.dialogs;

import com.haulmont.testtask.UI.models.DialogEvent;
import com.haulmont.testtask.UI.models.DialogEventType;
import com.haulmont.testtask.models.RoleType;
import com.haulmont.testtask.models.entities.Role;
import com.haulmont.testtask.models.entities.User;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;

import java.util.Collection;
import java.util.function.Consumer;


@DesignRoot(value = "/templates/dialogs/UserEditDialog.html")
public class UserEditDialog extends Window {

    private TextField firstNameField;
    private TextField secondNameField;
    private TextField middleNameField;
    private TextField phoneField;
    private TextField specializationField;

    private Button submitButton;
    private Button dismissButton;

    private NativeSelect<Role> roleSelect;

    private User user;
    private Consumer<DialogEvent<User>> event;

    /**
     * Constructor for creating new user
     * @param roleType
     * @param event
     * @param roles
     */
    public UserEditDialog(RoleType roleType, Consumer<DialogEvent<User>> event, Collection<Role> roles) {
        this.user = new User();
        this.event = event;
        init(false, roles);
    }

    /**
     * Constructor for updating existing user
     * @param roleType
     * @param user
     * @param event
     * @param roles
     */
    public UserEditDialog(RoleType roleType, User user, Consumer<DialogEvent<User>> event, Collection<Role> roles) {
        this.user = user;
        this.event = event;
        init(true, roles);
    }

    private void init(boolean updating, Collection<Role> roles) {
        Design.read(this);
        submitButton.addClickListener(this::submit);
        dismissButton.addClickListener(this::dismiss);
        roleSelect.setItemCaptionGenerator((role) -> role.getName().name());
        roleSelect.setItems(roles);
        if (updating) {
            setUserData(user);
        }
        center();
    }

    private void setUserData(User user) {
        firstNameField.setValue(user.getFirstName());
        secondNameField.setValue(user.getSecondName());
        middleNameField.setValue(user.getMiddleName());
        phoneField.setValue(user.getPhone());
        specializationField.setValue(user.getSpecialization());
        roleSelect.setValue(user.getRole());
    }

    private User collectUserData() {
        user.setFirstName(firstNameField.getValue());
        user.setSecondName(secondNameField.getValue());
        user.setMiddleName(middleNameField.getValue());
        user.setPhone(phoneField.getValue());
        user.setSpecialization(specializationField.getValue());
        user.setRole(roleSelect.getValue());
        return user;
    }

    private void submit(Button.ClickEvent clickEvent) {
        this.event.accept(new DialogEvent<>(DialogEventType.SUBMIT, collectUserData()));
        this.close();
    }

    private void dismiss(Button.ClickEvent clickEvent) {
        this.event.accept(new DialogEvent<>(DialogEventType.DISMISS, collectUserData()));
        this.close();
    }
}
