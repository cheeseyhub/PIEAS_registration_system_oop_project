package com.pieas.student_registration.Views.TemplateClasses;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("style/global-style.css")
@StyleSheet("style/sidebar-style.css")

public class Sidebar extends VerticalLayout {
    public Sidebar() {
        this.setHeightFull();
        this.getStyle().set("border", "2px solid red");

        Section top = new Section();
        top.add(new Logo());
        top.add(addButtons());

        add(top);
        logoutButton();

        this.addClassName("sidebar");
    }

    private VerticalLayout addButtons() {
        VerticalLayout temp = new VerticalLayout();
        String buttons[][] = {
                { "Dashboard", "main" },
                { "Student Registration", "registration" },
                { "Course Enroll", "courses" },
                { "View Results", "result" },
                { "Change Password", "changePassword" }
        };

        for (String btn[] : buttons) {
            Button button = new Button(btn[0]);
            button.addClassName("sidebar-button");
            button.addClickListener(e -> {
                UI.getCurrent().navigate(btn[1]);
            });

            temp.add(button);
        }

        return temp;
    }

    private void logoutButton() {
        Button logoutButton = new Button("Log Out");
        logoutButton.addClassName("sidebar-button");
        logoutButton.addClickListener(e -> {
            UI.getCurrent().navigate("logout");
        });

        add(logoutButton);
    }
}
