package com.pieas.student_registration.Views.StudentViews;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;

import com.pieas.student_registration.Entities.CourseEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.CourseService;
import com.pieas.student_registration.Services.DepartmentService;
import com.pieas.student_registration.Services.StudentService;
import com.pieas.student_registration.UI.Utils.AuthUtil;
import com.pieas.student_registration.Views.TemplateClasses.*;

@Route("courses")
@PageTitle("Course Enrollment")
@StyleSheet("styles/style.css")

public class CourseEnrollPageView extends HorizontalLayout implements BeforeEnterObserver {
    private String currentUser;
    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentEntity studentData;

    @Override
    public void beforeEnter(BeforeEnterEvent e) {
        AuthUtil.requireLogin(e);
    }

    public CourseEnrollPageView(StudentService studentService, DepartmentService departmentService,
            CourseService courseService) {
        try {
            this.studentService = studentService;
            this.studentData = studentService.getLoggedUser();
            this.departmentService = departmentService;
            this.courseService = courseService;

            this.currentUser = AuthUtil.getCurrentStudentName();
        } catch (Exception e) {
            UI.getCurrent().navigate("");
            return;
        }

        add(
                new Sidebar("courses"),
                new MainView(currentUser));

        this.setWidthFull();
        this.setSpacing(false);
        this.addClassName("dashboard");
    }

    class MainView extends VerticalLayout {
        MainView(String studentName) {
            this.setWidthFull();
            this.setHeightFull();
            this.setSpacing(false);
            this.setPadding(false);

            this.add(new Header(studentName));
            this.add(IntroCourseEnrollSection());
        }

        private VerticalLayout IntroCourseEnrollSection() {
            VerticalLayout tempLayoutContainer = new VerticalLayout();
            tempLayoutContainer.setWidthFull();
            tempLayoutContainer.setHeightFull();
            tempLayoutContainer.setSpacing(false);
            tempLayoutContainer.setPadding(false);
            tempLayoutContainer.addClassName("course-enrollment-main");

            tempLayoutContainer.add(CourseEnrollHeader(), displayCourses());

            return tempLayoutContainer;
        }

        private VerticalLayout CourseEnrollHeader() {
            VerticalLayout tempLayoutContainer = new VerticalLayout();

            // ICON SETTING
            Span iconWrapper = new Span();
            iconWrapper.addClassName("iconWrapper");
            Icon icon = new Icon(VaadinIcon.BOOK_PERCENT);
            icon.addClassName("iconWrapper-icon");
            iconWrapper.add(icon);

            // HEADING
            H2 heading = new H2("Course Enrollment");
            Paragraph paragraph = new Paragraph("Browse the catalogue and add courses to your semester.");

            tempLayoutContainer.add(
                    new HorizontalLayout(
                            iconWrapper,
                            new VerticalLayout(heading, paragraph))

            );
            return tempLayoutContainer;
        }

        private HorizontalLayout displayCourses() {
            HorizontalLayout tempLayoutContainer = new HorizontalLayout();
            tempLayoutContainer.setWidthFull();
            tempLayoutContainer.setHeightFull();
            tempLayoutContainer.setSpacing(false);
            tempLayoutContainer.setPadding(false);
            tempLayoutContainer.addClassName("course-enrollment-displaycourse");

            TextField search = new TextField("", "Search by course content or course code...");
            search.addClassName("course-enrollment-search");

            tempLayoutContainer.add(search);

            for (CourseEntity course : courseService.getCoursesByDegreeProgram(studentData.getDegreeName())) {
                displayCoursesTemplate(course);
            }

            return tempLayoutContainer;
        }

        private VerticalLayout displayCoursesTemplate(String courseName, String instructor, String courseCode,
                String creditHours, String duration, boolean isEnrolled) {
            VerticalLayout tempLayoutContainer = new VerticalLayout();
            tempLayoutContainer.addClassName("course-enrollment-displaycourse-child");

            Span courseNameSpan = new Span(courseName);
            courseNameSpan.addClassName("course-enrollment-displaycourse-child-courseName");
            Span instructorSpan = new Span(instructor);
            instructorSpan.addClassName("course-enrollment-displaycourse-child-instructor");
            Span courseCodeSpan = new Span(courseCode);
            courseCodeSpan.addClassName("course-enrollment-displaycourse-child-courseCode");
            Span creditHourSpan = new Span(creditHours);
            creditHourSpan.addClassName("course-enrollment-displaycourse-child-creditHour");
            Section durationSpan = new Section(
                    new Span(duration));
            creditHourSpan.addClassName("course-enrollment-displaycourse-child-duration");

            Span enrollButton = new Span();
            Icon icon;

            if (isEnrolled == false) {
                icon = new Icon(VaadinIcon.PLUS);
                icon.getStyle().set("width", "20px");
                icon.getStyle().set("height", "20px");

                enrollButton.addClassName("course-enrollment-displacourse-enroll-button-false");
                icon.addClassName("iconWrapper-icon");
                enrollButton.add(
                        icon,
                        new Span("Enroll"));
            } else {
                icon = new Icon(VaadinIcon.CHECK);
                icon.getStyle().set("width", "20px");
                icon.getStyle().set("height", "20px");

                enrollButton.addClassName("course-enrollment-displacourse-enroll-button-true");
                icon.addClassName("iconWrapper-icon");
                enrollButton.add(
                        icon,
                        new Span("Enrolled"));
            }

            Hr hr = new Hr();
            hr.addClassName("course-enrollment-displacourse-enroll-hr");

            tempLayoutContainer.add(
                    courseCodeSpan,
                    courseNameSpan,
                    instructorSpan,
                    new HorizontalLayout(durationSpan, creditHourSpan),
                    hr,
                    enrollButton);
            return tempLayoutContainer;
        }
    }
}