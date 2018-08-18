package com.haulmont.testtask.UI.components;

import com.haulmont.testtask.UI.models.RouteLink;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;

import java.util.List;


public class HeaderComponent extends VerticalLayout {

    private HorizontalLayout menu;

    public HeaderComponent(List<RouteLink> links) {
        addComponent(getMenu(links));
    }

    private HorizontalLayout getMenu(List<RouteLink> links) {
        menu = new HorizontalLayout();
        for (RouteLink link : links) {
            menu.addComponent(getLink(link));
        }
        return menu;
    }

    private Link getLink(RouteLink link) {
        return new Link(link.getName(), new ExternalResource(link.getUrl()));
    }
}
