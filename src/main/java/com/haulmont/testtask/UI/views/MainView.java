package com.haulmont.testtask.UI.views;

import com.haulmont.testtask.UI.components.HeaderComponent;
import com.haulmont.testtask.UI.models.RouteLink;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

import java.util.List;


@DesignRoot
public class MainView extends VerticalLayout implements View {

    private List<RouteLink> links;
    private Panel header;
    private Panel container;

    public MainView(List<RouteLink> links) {
        Design.read(this);

        this.links = links;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.header.setContent(new HeaderComponent(links));

    }
}
