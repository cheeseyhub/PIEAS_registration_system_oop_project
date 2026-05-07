package com.pieas.student_registration.Views.TemplateClasses;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Logo extends HorizontalLayout {
    Logo() {
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
