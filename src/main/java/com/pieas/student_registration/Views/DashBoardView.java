package com.pieas.student_registration.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.aura.Aura;

@Route("main")
@PageTitle("Dashboard")
@StyleSheet("styles/style.css")
@StyleSheet("styles/global-style.css")

public class DashboardView extends HorizontalLayout {
    public DashboardView() {
        add(
                new Sidebar(),
                new Main());

        this.setWidth("100vw");
        this.setHeight("100vh");
        this.getStyle().set("margin", "0");
        this.getStyle().set("padding", "0");
    }
}

class Header extends HorizontalLayout {
    public Header() {
        // this.setWidth("100vw");
        this.setWidthFull();
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
        Image img = new Image(
                "images/pieas-logo.png",
                "logo");
        img.setWidth("35%");

        VerticalLayout name = new VerticalLayout();
        name.getStyle().set("padding", "0");
        name.getStyle().set("margin", "0");

        H1 heading = new H1("PIEAS");
        heading.getStyle().set("padding", "0");
        heading.getStyle().set("margin", "0");

        Paragraph text = new Paragraph("STUDENT PORTAL");
        text.getStyle().set("padding", "0");
        text.getStyle().set("margin", "0");
        text.getStyle().set("white-space", "nowrap");
        text.getStyle().set("font-size", "small");

        name.add(heading, text);
        name.getStyle().set("display", "flex");
        name.getStyle().set("flex-direction", "column");
        name.getStyle().set("gap", "0");

        this.add(img, name);

        this.getStyle().set("display", "flex");
        this.getStyle().set("justify-content", "center");
        this.getStyle().set("align-items", "center");

        this.getStyle().set("width", "max(200px, 10vw)");
    }
}

class Sidebar extends VerticalLayout {
    public Sidebar() {
        this.setHeightFull();
        this.setWidth("15vw");
        this.setPadding(true);
        this.getStyle().set("padding", "10px");
        this.getStyle().set("justify-content", "space-between");
        this.getStyle().set("background-color", "rgb(9, 26, 54)");

        Section top = new Section();
        top.add(new logo());

        add(top);
        logoutButton();
    }

    private void addButtons() {
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

            add(button);
        }
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