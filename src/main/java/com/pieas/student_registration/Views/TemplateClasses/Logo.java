package com.pieas.student_registration.Views.TemplateClasses;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Logo extends HorizontalLayout {
    public Logo() {
        this.setAlignItems(FlexComponent.Alignment.CENTER);
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.START); // LEFT ALIGN

        // Add image/icon
        Image logoIcon = new Image("images/pieas-logo.png", "PIEAS Logo");
        logoIcon.setHeight("40px");
        logoIcon.setWidth("40px");

        Span logoText = new Span("PIEAS");
        logoText.getStyle().set("font-size", "20px")
                .set("font-weight", "bold")
                .set("color", "white");

        Span portalBadge = new Span("Student Portal");
        portalBadge.getStyle().set("font-size", "12px")
                .set("color", "#94a3b8");

        VerticalLayout textGroup = new VerticalLayout(logoText, portalBadge);
        textGroup.setSpacing(false);
        textGroup.setPadding(false);

        this.add(logoIcon, textGroup);
    }
}
