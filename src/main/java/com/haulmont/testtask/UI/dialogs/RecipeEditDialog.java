package com.haulmont.testtask.UI.dialogs;

import com.haulmont.testtask.UI.models.DialogEvent;
import com.haulmont.testtask.UI.models.DialogEventType;
import com.haulmont.testtask.models.RecipePriority;
import com.haulmont.testtask.models.entities.Recipe;
import com.haulmont.testtask.models.entities.Role;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.declarative.Design;

import java.util.Collection;
import java.util.function.Consumer;


@DesignRoot(value = "/templates/dialogs/RecipeEditDialog.html")
public class RecipeEditDialog extends Window {

    private TextField nameField;
    private TextField valueField;
    private NativeSelect<RecipePriority> prioritySelect;

    private Button submitButton;
    private Button dismissButton;

    private Recipe recipe;
    private Consumer<DialogEvent<Recipe>> event;

    /**
     * Constructor for creating new recipe
     *
     * @param event
     */
    public RecipeEditDialog(Consumer<DialogEvent<Recipe>> event) {
        this.recipe = new Recipe();
        this.event = event;
        init(false);
    }

    /**
     * Constructor for updating existing recipe
     *
     * @param recipe
     * @param event
     */
    public RecipeEditDialog(Recipe recipe, Consumer<DialogEvent<Recipe>> event) {
        this.recipe = recipe;
        this.event = event;
        init(true);
    }

    private void init(boolean updating) {
        Design.read(this);
        if (updating) {
            setRecipeData();
        }
        setButtonsHandlers();
        center();
        prioritySelect.setItems(RecipePriority.values());
        prioritySelect.setItemCaptionGenerator(RecipePriority::name);
    }

    private void setButtonsHandlers() {
        submitButton.addClickListener(this::submit);
        dismissButton.addClickListener(this::dismiss);
    }

    private void setRecipeData() {
        nameField.setValue(recipe.getName());
        valueField.setValue(recipe.getValue());
        prioritySelect.setValue(recipe.getPriority());
    }

    private Recipe collectRecipeData() {
        recipe.setName(nameField.getValue());
        recipe.setValue(valueField.getValue());
        recipe.setPriority(prioritySelect.getValue());
        return recipe;
    }

    private void submit(Button.ClickEvent clickEvent) {
        this.event.accept(new DialogEvent<>(DialogEventType.SUBMIT, collectRecipeData()));
        this.close();
    }

    private void dismiss(Button.ClickEvent clickEvent) {
        this.event.accept(new DialogEvent<>(DialogEventType.DISMISS, collectRecipeData()));
        this.close();
    }
}
