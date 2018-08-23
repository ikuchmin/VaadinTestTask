package com.haulmont.testtask.UI;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;


@DesignRoot(value = "/templates/views/MainView.html")
public class MainView extends VerticalLayout {

    public MainView() {
        Design.read(this);
    }
}
