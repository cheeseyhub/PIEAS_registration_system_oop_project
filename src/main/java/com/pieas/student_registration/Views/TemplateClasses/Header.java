package com.pieas.student_registration.Views.TemplateClasses;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Header extends HorizontalLayout {
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
