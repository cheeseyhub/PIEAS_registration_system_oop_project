package com.pieas.student_registration.Views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.*;

@Route("main")

class sidebar extends VerticalLayout{
    private void AnchorButton(String text, String url)
    {
        Button btn = new Button(text);
        btn.addClickListener(e->{
            UI.getCurrentUI().navigate(url);
        });

        add(btn);
    }

    public sidebar()
    {
        String buttons[][] = {
            {"Student Registration", "data"},
            {"Course Enrollment","course"},
            {"Semester Result","result"},
            {"Log out","logout"}
        };

        for(String i[]: buttons)
        {
            add(AnchorButton(i[0],i[1]));
        }
    }
}

class HeaderSection extends HorizontalLayout{
    public HeaderSection()
    {
        Image logoImage = new Image(DownlaodHandler.forClassResource(getClass(),"/images/logo.png"), "PIEAS Logo");
        H1 header = new header("Pakistan Institute of Engineering and Applied Sciences");

        add(image, header);
    }
}

class MainView extends HorizontalLayout{
    public MainView()
    {
        sidebar sidebr = new sidebar();
        add(sidebar);
    }
}

public class DashBoardView extends HorizontalLayout{
    public DashBoardView() {
        HeaderSection header = new header();
        MainView main = new MainView();

        add(header, main);

    }
}
