package com.pieas.student_registration.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("main")

public class DashBoardView extends VerticalLayout implements BeforeEnterObserver {

    public DashBoardView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(new HeaderSection());
        add(new MainContentSection());
        expand(new MainContentSection());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Check authentication if needed
    }
}

class HeaderSection extends HorizontalLayout {
    public HeaderSection() {
        setWidthFull();
        setAlignItems(Alignment.CENTER);

        Image logoImage = new Image("images/logo.png", "PIEAS Logo");
        logoImage.setHeight("50px");

        H1 header = new H1("Pakistan Institute of Engineering and Applied Sciences");
        header.getStyle().set("font-size", "1.5rem");

        add(logoImage, header);
        expand(header);
    }
}

class MainContentSection extends HorizontalLayout {
    public MainContentSection() {
        setSizeFull();

        Sidebar sidebar = new Sidebar();
        VerticalLayout contentArea = new VerticalLayout();

        sidebar.setWidth("250px");
        contentArea.setWidthFull();

        add(sidebar, contentArea);
        expand(contentArea);
    }
}

class Sidebar extends VerticalLayout {
    public Sidebar() {
        setWidthFull();
        setPadding(true);
        setSpacing(true);

        String[][] buttons = {
                { "Student Registration", "data" },
                { "Course Enrollment", "course" },
                { "Semester Result", "result" },
                { "Log out", "logout" }
        };

        for (String[] button : buttons) {
            Button btn = new Button(button[0]);
            btn.addClickListener(e -> UI.getCurrent().navigate(button[1]));
            btn.setWidthFull();
            add(btn);
        }
    }
}