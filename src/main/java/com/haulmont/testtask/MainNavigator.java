package com.haulmont.testtask;

import com.haulmont.testtask.UI.models.RouteLink;
import com.haulmont.testtask.UI.views.DoctorsView;
import com.haulmont.testtask.UI.views.MainView;
import com.haulmont.testtask.UI.views.PatientsView;
import com.haulmont.testtask.UI.views.RecipesView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;


public class MainNavigator extends UI {

    Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        navigator = new Navigator(this, this);
        List<RouteLink> links = getLinks();
        for (RouteLink link : links) {
            navigator.addView(link.getUrl(), link.getComponent());
        }
    }

    private List<RouteLink> getLinks() {
        List<RouteLink> links = new ArrayList<>();
        links.add(new RouteLink("", "Main", new MainView(links)));
        links.add(new RouteLink("doctors", "Doctors", new DoctorsView()));
        links.add(new RouteLink("recipes", "Recipes", new RecipesView()));
        links.add(new RouteLink("patients", "Patients", new PatientsView()));
        return links;
    }

}