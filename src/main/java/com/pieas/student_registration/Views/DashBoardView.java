package com.pieas.student_registration.Views;

import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

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

class Main extends HorizontalLayout {
    Main() {
        add(
                new Header());
    }
}