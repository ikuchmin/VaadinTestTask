package com.haulmont.testtask.UI.components;

import com.haulmont.testtask.UI.models.RouteLink;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

import java.util.List;


public class HeaderComponent extends VerticalLayout {

    private HorizontalLayout menu;
    private Navigator navigator;

    public HeaderComponent(List<RouteLink> links, Navigator navigator) {
        this.navigator = navigator;
        addComponent(getMenu(links));
    }

    private HorizontalLayout getMenu(List<RouteLink> links) {
        menu = new HorizontalLayout();
        for (RouteLink link : links) {
            menu.addComponent(getButton(link));
        }
        return menu;
    }

    private Link getLink(RouteLink link) {
        return new Link(link.getName(), new ExternalResource(link.getUrl()));
    }

    private Button getButton(RouteLink link) {
        return new Button(link.getName(), (Button.ClickEvent clickEvent) -> {
            this.navigator.navigateTo(link.getUrl());
        });
    }
}
