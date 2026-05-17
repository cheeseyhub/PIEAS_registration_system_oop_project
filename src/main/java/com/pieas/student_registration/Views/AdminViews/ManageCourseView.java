package com.pieas.student_registration.Views.AdminViews;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.CourseEntity;
import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Services.CourseService;
import com.pieas.student_registration.Services.DepartmentService;
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
import com.vaadin.flow.component.textfield.TextField;

class ManageCourseView extends VerticalLayout {
    @Autowired
    CourseService courseService;

    @Autowired
    DepartmentService departmentService;

    public ManageCourseView(CourseService courseService, DepartmentService departmentService) {
        this.courseService = courseService;
        this.departmentService = departmentService;

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

        Select<String> department = new Select<>("Department");
        List<String> temp = new ArrayList<>();
        for (DepartmentEntity dep : departmentService.getAllDepartments()) {
            for (String degree : dep.getDegreeName()) {
                temp.add(degree);
            }
        }
        department.setItems(temp);

        TextField courseTitle = new TextField("Course Title");
        courseTitle.setPlaceholder("Course Title");
        courseTitle.setRequired(true);

        TextField instructor = new TextField("Instructor");
        instructor.setPlaceholder("Instructor");
        instructor.setRequired(true);

        TextField courseCode = new TextField("Course Code");
        courseCode.setPlaceholder("Course Code");
        courseCode.setRequired(true);

        Select<Integer> semesterNo = new Select<>("Semester No.");
        department.addValueChangeListener(e -> {
            if (department.getValue() != null) {
                if (department.getValue().startsWith("BS"))
                    semesterNo.setItems(1, 2, 3, 4, 5, 6, 7, 8);
                else if (department.getValue().startsWith("MS"))
                    semesterNo.setItems(1, 2, 3, 4);
                else if (department.getValue().startsWith("PHD"))
                    semesterNo.setItems(1, 2, 3, 4, 5, 6);
            }
        });

        Select<Integer> creditHours = new Select<>("Credit Hours");
        creditHours.setItems(1, 2, 3);

        Button addButton = new Button("Add Course");
        addButton.addClassName("addCourseButton");

        addButton.addClickListener(e -> {
            try {
                courseService.addCourse(new CourseEntity(courseTitle.getValue(), department.getValue(),
                        instructor.getValue(), courseCode.getValue(),
                        semesterNo.getValue(), creditHours.getValue()));
                Notification.show("Course Added Successfully");
                UI.getCurrent().getPage().reload();
            } catch (Exception ex) {
                Notification.show("Error Adding Course: " + ex.getMessage());
            }
        });

        tempLayout.add(department, courseTitle, instructor, courseCode, semesterNo, creditHours, addButton);
        return tempLayout;
    }

    private VerticalLayout displayCourse(String filter) {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("course-display-table");
        tempLayout.setWidthFull();
        tempLayout.setHeightFull();

        tempLayout.add(new HorizontalLayout(
                new Span("Department"),
                new Span("Course Title"),
                new Span("Instructor"),
                new Span("Course Code"),
                new Span("Duration"),
                new Span("Credit Hours"),
                new Span("Edit")));

        for (CourseEntity course : courseService.getAllCourses()) {
            tempLayout.add(displayCourseTemplate(course));
        }

        return tempLayout;
    }

    private HorizontalLayout displayCourseTemplate(CourseEntity course) {
        HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
        tempHorizontalLayout.addClassName("course-display-row-template");

        Button btn = new Button(new Icon(VaadinIcon.TRASH));

        btn.addClassName("manage-course-table-button");
        btn.addClickListener(e -> {
            courseService.deleteCourse(course.getId());
            UI.getCurrent().getPage().reload();
        });

        tempHorizontalLayout.add(
                new Span(course.getDepartment()),
                new Span(course.getCourseName()),
                new Span(course.getInstructor()),
                new Span(course.getCourseCode()),
                new Span(String.valueOf(course.getSemesterNo())),
                new Span(String.valueOf(course.getCreditHour())),
                btn);

        return tempHorizontalLayout;
    }

}