package com.pieas.student_registration.Views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.ButtonVariant;

@Route("main")

public class DashboardView extends VerticalLayout {
    public DashboardView() {
        add(
                new DashboardHeader(),
                new DashboardContent(),
                new DashboardFooter());
        expand(getContent());
        setSizeFull();
    }
}

class DashboardHeader extends HorizontalLayout{
    private Logo logo = new Logo();
    private H1 title = new H1("Dashboard");
    private UserMenu userMenu = new UserMenu();

    public DashboardHeader() {
        add(logo, title, userMenu);
        setWidthFull();
        expand(title);
        setAlignItems(Alignment.CENTER);

        // Header-specific logic lives here
        userMenu.addLogoutListener(e -> handleLogout());
    }
}

class DashboardContent extends HorizontalLayout {
    private SidebarMenu sidebar = new SidebarMenu();
    private MainContentArea mainContent = new MainContentArea();

    public DashboardContent() {
        add(sidebar, mainContent);
        setWidthFull();
        expand(mainContent);

        // Navigation logic stays with the content
        sidebar.addMenuSelectionListener(menuItem -> mainContent.loadView(menuItem));
    }
}

class DashboardFooter extends HorizontalLayout {
    public DashboardFooter() {
        add(new Span("Status: Connected"), new Span("© 2026"));
        setJustifyContentMode(JustifyContentMode.END);
        setWidthFull();
    }
}