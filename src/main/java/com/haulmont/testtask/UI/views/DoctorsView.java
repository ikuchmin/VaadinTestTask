package com.haulmont.testtask.UI.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


public class DoctorsView extends VerticalLayout implements View {

    public DoctorsView() {
        this.addComponent(new Label("doctors"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}