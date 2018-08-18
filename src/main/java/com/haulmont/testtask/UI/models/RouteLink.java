package com.haulmont.testtask.UI.models;

import com.vaadin.navigator.View;

public class RouteLink {
    private String url;
    private String name;

    private View component;

    public RouteLink() {
    }

    public RouteLink(String url, String name, View component) {
        this.url = url;
        this.name = name;
        this.component = component;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public View getComponent() {
        return component;
    }

    public void setComponent(View component) {
        this.component = component;
    }
}
