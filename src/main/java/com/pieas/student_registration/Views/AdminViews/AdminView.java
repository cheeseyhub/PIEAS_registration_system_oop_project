package com.pieas.student_registration.Views.AdminViews;

import java.io.InputStream;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.StudentService;
import com.pieas.student_registration.Views.TemplateClasses.AdminSidebar;
import com.pieas.student_registration.Views.TemplateClasses.Header;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
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

            tempLayout.add(displayStudent(""));

            return tempLayout;
        }

        private VerticalLayout addStudent() {
            VerticalLayout tempLayout = new VerticalLayout();
            TextField regNo = new TextField("Reg No");
            PasswordField password = new PasswordField("Password");
            Select<DepartmentEntity> department = new Select<>("Department");
            Button addButton = new Button("Add Student");

            addButton.addClickListener(e -> {
                // Add student logic here
            });

            tempLayout.add(regNo, password, department, addButton);
            return tempLayout;
        }

        private VerticalLayout displayStudent(String dep) {
            VerticalLayout tempLayout = new VerticalLayout();

            HorizontalLayout header = new HorizontalLayout();

            Button addNewStudent = new Button("Add New Student");
            addNewStudent.addClickListener(e -> {
                addStudent();
            });

            header.add(new H2("Students"), addNewStudent);
            tempLayout.add(header);

            StudentEntity students[] = new StudentEntity[10];

            for (int count = 0; count < 10; count++) {

                students[count] = new StudentEntity();

                students[count].setName(String.valueOf("name " + count + 1));
                students[count].setFatherName(String.valueOf("name " + count + 1));
                students[count].setDepartment(String.valueOf("name " + count + 1));
            }

            for (StudentEntity student : students) {
                tempLayout.add(displayStudentTemplate(student));
            }

            return tempLayout;
        }

        private HorizontalLayout displayStudentTemplate(StudentEntity student) {
            HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
            tempHorizontalLayout.addClassName("student-display-row-template");

            tempHorizontalLayout.add(
                    new Span(student.getName()),
                    new Span(student.getFatherName()),
                    new Span(student.getDepartment()),
                    new Span(String.valueOf(student.getCurrentSemesterIndex() + 1)),
                    new Button(new Icon(VaadinIcon.PENCIL)));

            return tempHorizontalLayout;
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