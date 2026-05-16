package com.pieas.student_registration.Views.AdminViews;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Entities.SubjectEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

class ManageCourseView extends VerticalLayout {
    public ManageCourseView() {
        this.addClassName("admin-layout-main");
        this.setWidthFull();
        this.setHeightFull();
        this.setSpacing(false);

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.addClassName("addCourseHeader");

        Button addNewCourse = new Button("Add New Course");
        addNewCourse.addClassName("add-new-course-button");

        addNewCourse.addClickListener(e -> {
            this.removeAll();
            this.add(header);
            this.add(addCourse());
        });

        header.add(new H2("Manage Courses"), addNewCourse);
        this.add(header);
        this.add(displayCourse(""));
    }

    private VerticalLayout addCourse() {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("addCourseForm");
        tempLayout.setWidthFull();

        Select<DepartmentEntity> department = new Select<>("Department");

        TextField courseTitle = new TextField("Course Title");
        courseTitle.setPlaceholder("Course Title");
        courseTitle.setRequired(true);

        TextField instructor = new TextField("Instructor");
        instructor.setPlaceholder("Instructor");
        instructor.setRequired(true);

        TextField courseCode = new TextField("Course Code");
        courseCode.setPlaceholder("Course Code");
        courseCode.setRequired(true);

        TextField duration = new TextField("Duration");
        duration.setPlaceholder("Duration");
        duration.setRequired(true);

        TextField creditHours = new TextField("Credit Hours");
        creditHours.setPlaceholder("Credit Hours");
        creditHours.setRequired(true);

        Button addButton = new Button("Add Course");
        addButton.addClassName("addCourseButton");

        addButton.addClickListener(e -> {
            // Add course logic here
        });

        tempLayout.add(department, courseTitle, instructor, courseCode, duration, creditHours, addButton);
        return tempLayout;
    }

    private VerticalLayout displayCourse(String filter) {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("course-display-table");
        tempLayout.setWidthFull();
        tempLayout.setHeightFull();

        SubjectEntity courses[] = new SubjectEntity[100];

        for (int count = 0; count < 100; count++) {

            courses[count] = new SubjectEntity();

            courses[count].setDepartment(String.valueOf("Computer and Information Sciences"));
            courses[count].setCourseTitle(String.valueOf("Data Structures and Algorithms"));
            courses[count].setInstructor(String.valueOf("Kaleem Abbas"));
            courses[count].setCourseCode(String.valueOf("DSA-222"));
            courses[count].setSemesterNo(2);
            courses[count].setCreditHour(count % 4 + 1);
        }

        tempLayout.add(new HorizontalLayout(
                new Span("Department"),
                new Span("Course Title"),
                new Span("Instructor"),
                new Span("Course Code"),
                new Span("Duration"),
                new Span("Credit Hours"),
                new Span("Edit")));

        for (SubjectEntity course : courses) {
            tempLayout.add(displayCourseTemplate(course));
        }

        return tempLayout;
    }

    private HorizontalLayout displayCourseTemplate(SubjectEntity course) {
        HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
        tempHorizontalLayout.addClassName("course-display-row-template");

        Button btn = new Button(new Icon(VaadinIcon.TRASH));

        btn.addClassName("manage-course-table-button");
        btn.addClickListener(e -> {

        });

        tempHorizontalLayout.add(
                new Span(course.getDepartment()),
                new Span(course.getCourseTitle()),
                new Span(course.getInstructor()),
                new Span(course.getCourseCode()),
                new Span(String.valueOf(course.getSemesterNo())),
                new Span(String.valueOf(course.getCreditHour())),
                btn);

        return tempHorizontalLayout;
    }

}