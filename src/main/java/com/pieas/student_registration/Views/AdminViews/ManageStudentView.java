package com.pieas.student_registration.Views.AdminViews;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.DepartmentService;
import com.pieas.student_registration.Services.StudentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

class ManageStudentView extends VerticalLayout {
    @Autowired
    StudentService studentService;

    @Autowired
    DepartmentService departmentService;
    private String degreeTitle;
    private String department;

    public ManageStudentView(StudentService studentService, DepartmentService departmentService) {
        this.studentService = studentService;
        this.departmentService = departmentService;

        this.addClassName("admin-layout-main");
        this.setWidthFull();
        this.setHeightFull();
        this.setSpacing(false);

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.addClassName("addStudentHeader");

        Button addNewStudent = new Button("Add New Student");
        addNewStudent.addClassName("add-new-student-button");

        addNewStudent.addClickListener(e -> {
            this.removeAll();
            this.add(header);
            this.add(addStudent());
        });

        header.add(new H2("ManageStudents"), addNewStudent);
        this.add(header);
        this.add(displayStudent(""));
    }

    private VerticalLayout addStudent() {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("addstudentforum");
        tempLayout.setWidthFull();

        TextField name = new TextField("Name");
        name.setPlaceholder("Enter Student Name");
        name.setRequired(true);

        TextField regNo = new TextField("Reg No");
        regNo.setPattern("\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}");
        regNo.setMaxLength(16);
        regNo.setPlaceholder("03-3-1-079-2025");
        regNo.setHelperText("Format: XX-X-X-XXX-XXXX");
        regNo.addClassName("regNoAddStudent");

        PasswordField password = new PasswordField("Password");
        password.setPlaceholder("Enter Password");

        Select<String> degreeProgram = new Select<>("Degree Program");
        List<String> temp = new ArrayList<>();
        for (DepartmentEntity dep : departmentService.getAllDepartments()) {
            temp.addAll(dep.getDegreeName());
        }

        degreeProgram.setItems(temp);

        this.degreeTitle = "";
        this.department = "";

        degreeProgram.addValueChangeListener(e -> {
            degreeTitle = degreeProgram.getValue().split(" ")[0];

            department = "";

            for (DepartmentEntity dep : departmentService.getAllDepartments()) {
                if (dep.getDegreeName().contains(degreeProgram.getValue())) {
                    department = dep.getDepartmentName();
                    break;
                }
            }
        });

        Button addButton = new Button("Add Student");
        addButton.addClassName("addStudentButton");

        addButton.addClickListener(e -> {
            try {
                if (name.getValue().isEmpty()) {
                    Notification.show("Please enter student name");
                    return;
                }
                if (regNo.getValue().isEmpty()) {
                    Notification.show("Please enter registration number");
                    return;
                }
                if (password.getValue().isEmpty()) {
                    Notification.show("Please enter password");
                    return;
                }
                if (degreeProgram.getValue() == null) {
                    Notification.show("Please select a degree program");
                    return;
                }

                studentService
                        .addStudent(new StudentEntity(name.getValue(), password.getValue(), regNo.getValue(),
                                degreeProgram.getValue(), degreeTitle, department));
                Notification.show("Student Added Successfully");
                UI.getCurrent().getPage().reload();
            } catch (Exception ex) {
                Notification.show(ex.getMessage());
            }

        });

        tempLayout.add(name, degreeProgram, regNo, password, addButton);
        return tempLayout;
    }

    private VerticalLayout displayStudent(String dep) {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("student-display-table");
        tempLayout.setWidthFull();
        tempLayout.setHeightFull();

        tempLayout.add(new HorizontalLayout(
                new Span("Registration Number"),
                new Span("Name"),
                new Span("Degree Program"),
                new Span("Current Semester Enrolled"),
                new Span("Edit")));
        for (StudentEntity student : studentService.getAllStudents()) {
            tempLayout.add(displayStudentTemplate(student));
        }

        return tempLayout;
    }

    private HorizontalLayout displayStudentTemplate(StudentEntity student) {
        HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
        tempHorizontalLayout.addClassName("student-display-row-template");

        Button btn = new Button(new Icon(VaadinIcon.TRASH));

        btn.addClassName("manage-student-table-button");
        btn.addClickListener(e -> {
            studentService.deleteStudent(student.getRegistrationNumber());
            UI.getCurrent().getPage().reload();
        });

        tempHorizontalLayout.add(
                new Span(student.getRegistrationNumber()),
                new Span(student.getName()),
                new Span(student.getDegreeName()),
                new Span(String.valueOf(student.getCurrentSemesterIndex() + 1)),
                btn);

        return tempHorizontalLayout;
    }

}