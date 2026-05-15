package com.pieas.student_registration.Views.AdminViews;

import com.pieas.student_registration.Entities.Admin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class ManageAdminView extends VerticalLayout {
    public ManageAdminView() {
        this.addClassName("admin-layout-main");
        this.setWidthFull();
        this.setHeightFull();
        this.setSpacing(false);

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.addClassName("addAdminHeader");

        Button addNewAdmin = new Button("Add New Admin");
        addNewAdmin.addClassName("add-new-admin-button");

        addNewAdmin.addClickListener(e -> {
            this.removeAll();
            this.add(header);
            this.add(addAdmin());
        });

        header.add(new H2("Manage Admins"), addNewAdmin);
        this.add(header);
        this.add(displayAdmins());
    }

    private VerticalLayout addAdmin() {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("addadminforum");
        tempLayout.setWidthFull();

        TextField username = new TextField("Admin ID");
        username.setPlaceholder("Enter Admin ID");

        PasswordField password = new PasswordField("Password");
        password.setPlaceholder("Enter Password");

        Button addButton = new Button("Add Admin");
        addButton.addClassName("addAdminButton");

        addButton.addClickListener(e -> {
            // Add admin logic here
        });

        tempLayout.add(username, password, addButton);
        return tempLayout;
    }

    private VerticalLayout displayAdmins() {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("admin-display-table");
        tempLayout.setWidthFull();
        tempLayout.setHeightFull();

        Admin admin[] = new Admin[10];

        for (int count = 0; count < 10; count++) {

            admin[count] = new Admin();

            admin[count].setUsername("admin" + count + "@pieas.edu.pk");
            admin[count].setPassword("admin" + count);
        }

        tempLayout.add(new HorizontalLayout(
                new Span("Username"),
                new Span("Password"),
                new Span("Edit")));
        for (Admin admn : admin) {
            tempLayout.add(displayAdminTemplate(admn));
        }

        return tempLayout;
    }

    private HorizontalLayout displayAdminTemplate(Admin admin) {
        HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
        tempHorizontalLayout.addClassName("admin-display-row-template");

        Button btn = new Button(new Icon(VaadinIcon.TRASH));

        btn.addClassName("manage-admin-table-button");
        btn.addClickListener(e -> {

        });

        tempHorizontalLayout.add(
                new Span(admin.getUsername()),
                new Span(admin.getPassword()),
                btn);

        return tempHorizontalLayout;
    }

}