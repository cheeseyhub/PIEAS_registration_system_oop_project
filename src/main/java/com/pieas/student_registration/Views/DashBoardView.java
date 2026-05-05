package com.pieas.student_registration.Views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("dashboard")

public class DashboardView e

        
 

 

 

    
class DashboardContent extends Horizo

    talLayout {

    private SidebarMenu sidebar = new SidebarM enu();
    private MainContentArea mainContent = new MainContentArea();

    public DashboardContent() {
        add(sidebar, mainContent);
        setWidthFull();
        expand(mainContent);

        // Navigation logic stays with the content
     
 

setJustifyConte

    }
}