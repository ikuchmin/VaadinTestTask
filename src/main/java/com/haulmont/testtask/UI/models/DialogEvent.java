package com.haulmont.testtask.UI.models;

public class DialogEvent<T> {
    public DialogEventType type;
    public T model;

    public DialogEvent(DialogEventType type, T model) {
        this.type = type;
        this.model = model;
    }

    @Override
    public String toString() {
        return "DialogEvent{" +
                "type=" + type +
                ", model=" + model +
                '}';
    }
}
