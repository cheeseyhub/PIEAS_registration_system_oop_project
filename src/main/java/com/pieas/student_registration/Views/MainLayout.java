package com.pieas.student_registration.Views;

import com.pieas.student_registration.Entities.StudentEntity;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        Object user = VaadinSession.getCurrent().getAttribute("currentUser");
        String name = (user instanceof StudentEntity s) ? s.getName() : "Guest";
        Span welcome = new Span("Welcome, " + name);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), welcome);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Dashboard", DashBoardView.class)
        // Add more links here later (e.g., Profile, Courses)
        ));
    }
}