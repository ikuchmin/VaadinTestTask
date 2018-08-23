package com.haulmont.testtask.UI.views;

import com.haulmont.testtask.UI.dialogs.RecipeEditDialog;
import com.haulmont.testtask.UI.dialogs.UserEditDialog;
import com.haulmont.testtask.UI.models.DialogEventType;
import com.haulmont.testtask.models.RoleType;
import com.haulmont.testtask.models.entities.Recipe;
import com.haulmont.testtask.models.entities.Recipe;
import com.haulmont.testtask.repositories.RecipeRepository;
import com.haulmont.testtask.repositories.RoleRepository;
import com.haulmont.testtask.repositories.UserRepository;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@SpringComponent
@DesignRoot("/templates/views/RecipesView.html")
public class RecipesView extends VerticalLayout implements View {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    private Grid<Recipe> grid;

    //toolbar
    private HorizontalLayout toolbar;
    private Button addButton;

    public RecipesView() {
        Design.read(this);
        initializeGrid();
        initializeToolbar();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadDataToGrid();
    }

    private void addUserButton(Button.ClickEvent clickEvent) {
        Window addDialog = new RecipeEditDialog(event -> {
            if (event.type == DialogEventType.SUBMIT) {
                recipeRepository.save(event.model);
                loadDataToGrid();
            }
            //UI.getCurrent().removeWindow(addDialog);
        });
        UI.getCurrent().addWindow(addDialog);
    }

    private void removeUserButton(Recipe recipe) {
        ConfirmDialog.show(UI.getCurrent(),
                "Confirm deletion",
                "Item will be deleted permanently",
                "Delete",
                "Cancel",
                confirmDialog -> {
                    if (confirmDialog.isConfirmed()) {
                        recipeRepository.delete(recipe);
                        loadDataToGrid();
                    }
                }
        );
    }

    private void editUserButton(Recipe recipe) {
        Window addDialog = new RecipeEditDialog(recipe,
                event -> {
                    if (event.type == DialogEventType.SUBMIT) {
                        recipeRepository.save(event.model);
                        loadDataToGrid();
                    }
                    //UI.getCurrent().removeWindow(addDialog);
                }
        );
        UI.getCurrent().addWindow(addDialog);
    }

    private void initializeToolbar() {
        addButton.addClickListener(this::addUserButton);
    }

    private void initializeGrid() {
        grid.addColumn(Recipe::getName).setCaption("Recipe name");
        grid.addColumn(Recipe::getValue).setCaption("Recipe");
        grid.addColumn(Recipe::getPriority).setCaption("Priority");
        grid.addComponentColumn(recipe -> {
            Button b = new Button("", clickEvent -> this.editUserButton(recipe));
            b.setIcon(VaadinIcons.PENCIL);
            return b;
        });
        grid.addComponentColumn(recipe -> {
            Button b = new Button("", clickEvent -> this.removeUserButton(recipe));
            b.setIcon(VaadinIcons.TRASH);
            return b;
        });
    }

    private void loadDataToGrid() {
        grid.setItems(StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(Collectors.toList()));
    }
}
