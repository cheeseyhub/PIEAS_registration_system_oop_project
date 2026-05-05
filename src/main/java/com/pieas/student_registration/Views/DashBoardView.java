package com.pieas.student_registration.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("main")

public class DashboardView extends VerticalLayout {
    public DashboardView() {
        add(
                new Main());
    }
}

class Header extends HorizontalLayout {
    public Header() {

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