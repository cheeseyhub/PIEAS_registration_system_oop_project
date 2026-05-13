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

        add(new MainView());
    }

    class MainView extends VerticalLayout {
        MainView() {
            add(new Header("Admin"));
            add(displayMain());

        }

        private VerticalLayout displayMain() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.add(manageStudentView());
            sidebar.addClickListener(e -> {
                tempLayout.removeAll();
                switch (sidebar.getCurrentView()) {
                    case "student":
                        tempLayout.add(manageStudentView());
                        break;
                    case "course":
                        tempLayout.add(manageCoursesView());
                        break;
                    case "department":
                        tempLayout.add(manageDepartmentView());
                        break;
                    case "admin":
                        tempLayout.add(manageAdminView());
                        break;
                }
            });
            return tempLayout;
        }

        private VerticalLayout manageStudentView() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.add("Student View");
            return tempLayout;
        }

        private VerticalLayout manageDepartmentView() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.add("department View");
            return tempLayout;
        }

        private VerticalLayout manageCoursesView() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.add("course View");
            return tempLayout;
        }

        private VerticalLayout manageAdminView() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.add("admin View");
            return tempLayout;
        }
    }
}