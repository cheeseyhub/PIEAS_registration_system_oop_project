package com.pieas.student_registration.Views.AdminViews;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

class ManageStudentView extends VerticalLayout {
    public ManageStudentView() {
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

        TextField regNo = new TextField("Reg No");
        regNo.setPattern("\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}");
        regNo.setMaxLength(16);
        regNo.setPlaceholder("03-3-1-079-2025");
        regNo.setHelperText("Format: XX-X-X-XXX-XXXX");
        regNo.addClassName("regNoAddStudent");

        PasswordField password = new PasswordField("Password");
        password.setPlaceholder("Enter Password");

        Select<DepartmentEntity> department = new Select<>("Department");
        Button addButton = new Button("Add Student");
        addButton.addClassName("addStudentButton");

        addButton.addClickListener(e -> {
            // Add student logic here
        });

        tempLayout.add(department, regNo, password, addButton);
        return tempLayout;
    }

    private VerticalLayout displayStudent(String dep) {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("student-display-table");
        tempLayout.setWidthFull();
        tempLayout.setHeightFull();

        StudentEntity students[] = new StudentEntity[100];

        for (int count = 0; count < 100; count++) {

            students[count] = new StudentEntity();

            students[count].setName(String.valueOf("Zohaib Kaleem"));
            students[count].setFatherName(String.valueOf("Kaleem Abbas"));
            students[count].setDepartment(String.valueOf("Computer and Information Sciences"));
        }

        tempLayout.add(new HorizontalLayout(
                new Span("Name"),
                new Span("FatherName"),
                new Span("Department"),
                new Span("Current Semester Enrolled"),
                new Span("Edit")));
        for (StudentEntity student : students) {
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

        });

        tempHorizontalLayout.add(
                new Span(student.getName()),
                new Span(student.getFatherName()),
                new Span(student.getDepartment()),
                new Span(String.valueOf(student.getCurrentSemesterIndex() + 1)),
                btn);

        return tempHorizontalLayout;
    }

}