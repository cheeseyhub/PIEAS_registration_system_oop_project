package com.pieas.student_registration.Views;

import com.pieas.student_registration.Entities.StudentEntity;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "dashboard", layout = MainLayout.class)
public class DashBoardView extends VerticalLayout implements BeforeEnterObserver {

    private final H2 welcomeMsg = new H2();
    private final Span regNo = new Span();

    public DashBoardView() {
        setSpacing(true);
        setPadding(true);
        add(welcomeMsg, regNo);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Object userObj = VaadinSession.getCurrent().getAttribute("currentUser");

        if (userObj instanceof StudentEntity student) {
            welcomeMsg.setText("Student Dashboard: " + student.getName());
            regNo.setText("Registration ID: " + student.getRegistrationNumber());
        } else {
            event.forwardTo(LoginView.class);
        }
    }
}