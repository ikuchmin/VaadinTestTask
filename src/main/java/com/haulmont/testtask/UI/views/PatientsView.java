package com.haulmont.testtask.UI.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@SpringComponent
public class PatientsView extends VerticalLayout implements View {

    public PatientsView() {
        this.addComponent(new Label("patients"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}