package com.pieas.student_registration.Views.TemplateClasses;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Header extends HorizontalLayout {
    private String StudentName;

    public Header(String stdName) {
        this.StudentName = stdName;
        this.setWidthFull();
        this.setHeight("max-content");

        this.addClassName("headerBar");

        VerticalLayout leftSection = LeftHeaderSection();
        leftSection.addClassName("left-header-section");

        Div CircularDiv = new Div((String) this.StudentName.substring(0, 1));
        CircularDiv.addClassName("cicleDiv");

        add(leftSection, CircularDiv);
    }

    private VerticalLayout LeftHeaderSection() {
        VerticalLayout tempLayoutContainer = new VerticalLayout();

        Paragraph welcomeParagraph = new Paragraph("Welcome Back");
        H3 studentName = new H3(this.StudentName.split(" ")[0]);

        tempLayoutContainer.add(welcomeParagraph, studentName);
        return tempLayoutContainer;
    }
}
