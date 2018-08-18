package com.haulmont.testtask.UI.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


public class RecipesView extends VerticalLayout implements View {

    public RecipesView() {
        this.addComponent(new Label("recipes"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
