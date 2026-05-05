package com.pieas.student_registration.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("main")

public class DashboardView extends VerticalLayout {
    public DashboardView() {
        add(new Header(), new Main());

        this.setWidth("100vw");
        this.setHeight("100vh");
        this.getStyle().set("margin", "0");
        this.getStyle().set("padding", "0");
    }
}

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
                new Sidebar());
    }
}

class Sidebar extends VerticalLayout {
    public Sidebar() {
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