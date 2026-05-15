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

class ManageStudentView extends VerticalLayout{
    public ManageStudentView() {
            this.addClassName("admin-layout-main");
            
            HorizontalLayout header = new HorizontalLayout();
            
            Button addNewStudent = new Button("Add New Student");
            addNewStudent.addClickListener(e -> {
                this.removeAll();
                this.add(header);
                this.add(addStudent());
            });
            
            header.add(new H2("Students"), addNewStudent);
            this.add(header);
            this.add(displayStudent(""));
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
    
}