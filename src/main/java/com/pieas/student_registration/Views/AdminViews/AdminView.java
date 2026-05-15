package com.pieas.student_registration.Views.AdminViews;

import com.pieas.student_registration.Views.TemplateClasses.AdminSidebar;
import com.pieas.student_registration.Views.TemplateClasses.Header;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("admin")
@PageTitle("Admin")
@StyleSheet("styles/style.css")

public class AdminView extends HorizontalLayout {
    private AdminSidebar sidebar;

    AdminView() {
        this.setWidthFull();
        this.setHeightFull();
        this.setSpacing(false);
        sidebar = new AdminSidebar();
        sidebar.setHeightFull();
        add(sidebar);

        this.add(new MainView());
    }

    class MainView extends VerticalLayout {
        MainView() {
            this.setWidthFull();
            this.setHeightFull();
            this.setSpacing(false);

            this.add(new Header("Admin"));
            this.add(displayMain());

        }

        private VerticalLayout displayMain() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.setWidthFull();
            tempLayout.setHeightFull();
            tempLayout.setSpacing(false);

            tempLayout.add(new ManageDepartmentView());
            sidebar.addClickListener(e -> {
                tempLayout.removeAll();
                switch (sidebar.getCurrentView()) {
                    case "student":
                        tempLayout.add(new ManageStudentView());
                        break;
                    case "course":
                        tempLayout.add(new ManageCoursesView());
                        break;
                    case "department":
                        tempLayout.add(new ManageDepartmentView());
                        break;
                    case "admin":
                        tempLayout.add(new ManageAdminView());
                        break;
                }
            });
            return tempLayout;
        }

    }
}