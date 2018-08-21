package com.haulmont.testtask.UI.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;


@SpringComponent
public class MainView extends VerticalLayout implements View {

    public MainView() {
        //Design.read(this);
        this.addComponent(new Label("my main page"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
