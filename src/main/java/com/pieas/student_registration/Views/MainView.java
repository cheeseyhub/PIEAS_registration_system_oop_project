package com.pieas.student_registration.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

class Header extends HorizontalLayout {
    public Header() {
        this.setWidth("100vw");
        this.setHeight("10vh");

        // CSS Styles
        this.getStyle().set("background-color", "violet");
        this.getStyle().setBorderRadius("20px");
        this.getStyle().setBorder("2px solid red");
    }
}

class Main extends HorizontalLayout {
    public Main() {
        add(
                new Header());
    }
}

class logo extends HorizontalLayout {
    logo() {

    }
}

class Sidebar extends VerticalLayout {
    public Sidebar() {
        add(new logo());

        String buttons[][] = {
                { "Student Registration", "registration" },
                { "Course Enroll", "courses" },
                { "View Results", "result" },
                { "Change Password", "changePassword" },
                { "Log Out", "logout" }
        };

        for (String btn[] : buttons) {
            addButton(btn[0], btn[1]);
        }
    }

    private void addButton(String text, String destination) {
        Button button = new Button(text);
        button.addClickListener(e -> {
            UI.getCurrent().navigate(destination);
        });

        add(button);
    }
}