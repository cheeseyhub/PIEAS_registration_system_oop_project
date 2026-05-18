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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.CourseEntity;
import com.pieas.student_registration.Entities.SemesterEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.CourseService;
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
    private CourseService courseService;
    private StudentEntity studentData;

    @Override
    public void beforeEnter(BeforeEnterEvent e) {
        AuthUtil.requireLogin(e);
    }

    public CourseEnrollPageView(StudentService studentService,
            CourseService courseService) {
        try {
            this.studentService = studentService;
            this.studentData = studentService.getLoggedUser();
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

            for (CourseEntity course : courseService.getCoursesBydegreeProgram(studentData.getDegreeName())) {
                tempLayoutContainer.add(displayCoursesTemplate(course));
            }

            return tempLayoutContainer;
        }

        private VerticalLayout displayCoursesTemplate(CourseEntity course) {
            if (studentData.getCurrentSemester() != null && studentData.getCurrentSemester().getCourses() != null) {
                for (CourseEntity c : studentData.getCurrentSemester().getCourses()) {
                    if (c.getCourseCode().equals(course.getCourseCode())) {
                        course.setEnrolledInCourse(true);
                        break;
                    }
                }
            }

            VerticalLayout tempLayoutContainer = new VerticalLayout();
            this.setHeightFull();
            tempLayoutContainer.addClassName("course-enrollment-displaycourse-child");

            Span courseNameSpan = new Span(course.getCourseName());
            courseNameSpan.addClassName("course-enrollment-displaycourse-child-courseName");
            Span instructorSpan = new Span(course.getInstructor());
            instructorSpan.addClassName("course-enrollment-displaycourse-child-instructor");
            Span courseCodeSpan = new Span(course.getCourseCode());
            courseCodeSpan.addClassName("course-enrollment-displaycourse-child-courseCode");
            Span creditHourSpan = new Span(String.valueOf(course.getCreditHour()) + " Cr");
            creditHourSpan.addClassName("course-enrollment-displaycourse-child-creditHour");
            Section durationSpan = new Section(
                    new Span(String.valueOf(course.getSemesterNo()) + " Semester"));
            creditHourSpan.addClassName("course-enrollment-displaycourse-child-duration");

            Span enrollButton = new Span();
            enrollButton.getStyle().set("cursor", "pointer");

            enrollButton.addClickListener(e -> {
                try {

                    if (course.isEnrolledInCourse() == false) {
                        if (studentData.getCurrentSemester() == null || studentData.getSemesters().isEmpty()) {
                            ArrayList<SemesterEntity> semesters = new ArrayList<>();
                            SemesterEntity newSemester = new SemesterEntity(1, "Fall 2026", true,
                                    new ArrayList<CourseEntity>(0), 0);
                            semesters.add(newSemester);
                            studentData.setSemesters(semesters);
                        }

                        if (studentData.getCurrentSemester().getCourses() == null
                                || studentData.getCurrentSemester().getCourses().isEmpty()) {
                            studentData.getCurrentSemester().setCourses(new ArrayList<CourseEntity>());
                        }

                        studentData.getCurrentSemester().getCourses().add(course);
                        course.setEnrolledInCourse(true);
                        for (CourseEntity c : studentData.getCurrentSemester().getCourses()) {
                            if (c.getCourseCode().equals(course.getCourseCode())) {
                                c.setEnrolledInCourse(true);
                                break;
                            }
                        }

                        studentService.updateStudent(studentData);
                        Notification.show("Enrolled in course: " + course.getCourseName());
                        UI.getCurrent().getPage().reload();
                    } else {
                        ArrayList<CourseEntity> updatedCourseList = new ArrayList<>();
                        updatedCourseList.addAll(studentData.getCurrentSemester().getCourses());
                        updatedCourseList.removeIf(c -> c.getCourseCode().equals(course.getCourseCode()));
                        studentData.getCurrentSemester().setCourses(updatedCourseList);
                        studentService.updateStudent(studentData);
                        course.setEnrolledInCourse(false);
                        Notification.show("Dropped course: " + course.getCourseName());
                        UI.getCurrent().getPage().reload();
                    }
                } catch (Exception ex) {
                    Notification.show("Error enrolling in course: " + ex.getMessage());
                }
            });

            Icon icon;

            if (course.isEnrolledInCourse() == false) {
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