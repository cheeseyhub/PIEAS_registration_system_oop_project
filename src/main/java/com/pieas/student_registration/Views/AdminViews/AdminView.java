package com.pieas.student_registration.Views.AdminViews;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Services.DepartmentService;
import com.pieas.student_registration.Services.StudentService;
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
    @Autowired
    StudentService studentService;

    @Autowired
    DepartmentService departmentService;

    private AdminSidebar sidebar;

    AdminView(StudentService studentService, DepartmentService departmentService) {
        this.studentService = studentService;
        this.departmentService = departmentService;

        this.setWidthFull();
        this.getStyle().set("height", "max-content");
        this.setSpacing(false);
        sidebar = new AdminSidebar();
        add(sidebar);

        this.add(new MainView());
    }

    class MainView extends VerticalLayout {
        MainView() {
            this.setWidthFull();
            this.setHeight("max(max-content, 100vh)");
            this.setSpacing(false);

            this.add(new Header("Admin"));
            this.add(displayMain());

        }

        private VerticalLayout displayMain() {
            VerticalLayout tempLayout = new VerticalLayout();
            tempLayout.setWidthFull();
            tempLayout.setHeightFull();
            tempLayout.setSpacing(false);

            tempLayout.add(new ManageDepartmentView(departmentService));
            sidebar.addClickListener(e -> {
                tempLayout.removeAll();
                switch (sidebar.getCurrentView()) {
                    case "student":
                        tempLayout.add(new ManageStudentView(studentService, departmentService));
                        break;
                    case "course":
                        tempLayout.add(new ManageCourseView());
                        break;
                    case "department":
                        tempLayout.add(new ManageDepartmentView(departmentService));
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