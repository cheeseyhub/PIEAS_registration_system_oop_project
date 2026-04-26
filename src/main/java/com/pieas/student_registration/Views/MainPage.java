package com.pieas.student_registration.Views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("zohaib")

public class MainPage extends VerticalLayout{
    MainPage() {
        Button btn = new Button("Click Me to Call Ibrahim");

        Text text = new Text("This is some text to show");

        btn.addClickListener(e->{
            text.setText("Ibrahim is the best");
        });

        add(btn, text);
    }
}