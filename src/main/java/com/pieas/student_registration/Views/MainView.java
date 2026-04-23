package com.pieas.student_registration.Views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new H1("This is the view page on the main page"));

    }

}
