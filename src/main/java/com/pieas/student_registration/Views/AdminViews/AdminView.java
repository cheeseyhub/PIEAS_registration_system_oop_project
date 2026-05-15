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
        sidebar = new AdminSidebar();
        add(sidebar);

        this.add(new MainView());
    }

    class MainView extends VerticalLayout {
        MainView() {
            this.setWidthFull();
            this.setHeightFull();

            add(new Header("Admin"));
            add(displayMain());

        }

        private VerticalLayout displayMain() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.setWidthFull();
            tempLayout.setHeightFull();

            tempLayout.add(new ManageStudentView());
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