package com.pieas.student_registration.Views.TemplateClasses;

import com.pieas.student_registration.UI.Utils.AuthUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Sidebar extends VerticalLayout {

    public Sidebar() {
        this.setHeight("100vh");
        this.setWidth("max-content");
        this.setPadding(false);
        this.setSpacing(false);
        this.addClassName("sidebar");

        Section top = new Section();
        top.addClassName("sidebar-top-section");

        Logo logo = new Logo();
        logo.addClassName("logo");
        top.add(logo);

        VerticalLayout buttonContainer = addButtons();
        buttonContainer.addClassName("sidebar-button-container");
        top.add(buttonContainer);

        add(top);
        addLogoutButton();
    }

    private VerticalLayout addButtons() {
        VerticalLayout buttonContainer = new VerticalLayout();
        buttonContainer.setPadding(false);
        buttonContainer.setSpacing(false);
        buttonContainer.setWidthFull();

        Object[][] buttons = {
                { "Dashboard", "main", VaadinIcon.DASHBOARD },
                { "Student Registration", "registration", VaadinIcon.USER_STAR },
                { "Course Enroll", "courses", VaadinIcon.BOOK },
                { "View Results", "result", VaadinIcon.CHART_LINE },
                { "Change Password", "changePassword", VaadinIcon.KEY }
        };

        for (Object[] btn : buttons) {
            Button button = new Button((String) btn[0]);
            button.addClassName("sidebar-button");
            button.setWidthFull();
            button.setIcon(new Icon((VaadinIcon) btn[2]));

            button.addClickListener(e -> {
                UI.getCurrent().navigate((String) btn[1]);
            });

            button.getStyle().set("text-align", "left");
            button.getStyle().set("justify-content", "flex-start");

            buttonContainer.add(button);
        }

        return buttonContainer;
    }

    private void addLogoutButton() {
        Button logoutButton = new Button("Log Out");
        logoutButton.addClassNames("sidebar-button", "logout-button");
        logoutButton.setWidthFull();
        logoutButton.setIcon(new Icon(VaadinIcon.EXIT_O));
        logoutButton.addClickListener(e -> {
            AuthUtil.logout();
        });

        add(logoutButton);
    }
}