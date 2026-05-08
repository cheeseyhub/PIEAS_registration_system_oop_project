package com.pieas.student_registration.Views;

import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("registration")
@StyleSheet("styles/style.css")
@PageTitle("Student Registration")

public class StudentRegistrationView extends HorizontalLayout {
    StudentRegistrationView() {
        this.setWidthFull();
        this.setSpacing(false);
        this.setPadding(false);

        this.add(new Sidebar(), new MainView());
    }

    class MainView extends VerticalLayout {
        MainView() {
            this.setWidthFull();
            this.setHeightFull();
            this.setSpacing(false);
            this.setPadding(false);

            this.add(new Header());
        }
    }

}