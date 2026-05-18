package com.pieas.student_registration.Views.StudentViews;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.CourseEntity;
import com.pieas.student_registration.Entities.SemesterEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.StudentService;
import com.pieas.student_registration.UI.Utils.AuthUtil;
import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("main")
@PageTitle("Dashboard")
@StyleSheet("styles/style.css")

public class DashboardView extends HorizontalLayout implements BeforeEnterObserver {
        @Autowired
        private StudentService studentService;

        private StudentEntity studentData;
        private String currentUser;

        @Override
        public void beforeEnter(BeforeEnterEvent e) {
                AuthUtil.requireLogin(e);
        }

        public DashboardView(StudentService stdService) {
                this.studentService = stdService;
                try {
                        this.currentUser = studentService.getLoggedStudentName();
                        this.studentData = studentService.getLoggedUser();
                } catch (Exception e) {
                        UI.getCurrent().navigate("");
                        Notification.show(e.getMessage());
                        return;
                }

                add(
                                new Sidebar("main"),
                                new Main());

                this.setWidthFull();
                this.setHeightFull();
                this.setSpacing(false);
                this.addClassName("dashboard");
        }

        class Main extends VerticalLayout {
                Main() {
                        this.setWidthFull();
                        this.setPadding(false);
                        this.setSpacing(false);
                        this.addClassName("main-section");

                        add(
                                        new Header(currentUser),
                                        new DashboardMainView());
                }
        }

        class DashboardMainView extends VerticalLayout {
                public DashboardMainView() {
                        this.addClassName("DashboardMainView");

                        add(IntroDashboardSection(), CourseInfoSection(), courseEnrollSection());
                }

                private VerticalLayout IntroDashboardSection() {
                        VerticalLayout tempLayoutContainer = new VerticalLayout();
                        tempLayoutContainer.addClassName("dashboard-main-intro-section");

                        H1 heading = new H1("Your Academic Journey");
                        heading.addClassName("heading");
                        Paragraph welcomeParagraph = new Paragraph(
                                        "Track currently enrolled courses, plan upcoming enrollments, and review your performance — all from one place.");
                        welcomeParagraph.addClassName("welcomeParagraph");

                        tempLayoutContainer.add(heading, welcomeParagraph);

                        return tempLayoutContainer;
                }

                private HorizontalLayout CourseInfoSection() {
                        HorizontalLayout tempLayoutContainer = new HorizontalLayout();
                        tempLayoutContainer.addClassName("dashobard-course-info-section");

                        SemesterEntity currentSemester = studentData.getCurrentSemester();

                        if (currentSemester == null) {
                                currentSemester = new SemesterEntity(1, "Fall 2026", true,
                                                new ArrayList<CourseEntity>(), 0);
                        }

                        studentData.calculateCgpa();
                        HorizontalLayout temp[] = {

                                        helperCourseInfoSection("Course Enrolled",
                                                        String.valueOf(currentSemester.getTotalEnrolledcourses()),
                                                        VaadinIcon.ACADEMY_CAP),
                                        helperCourseInfoSection("Total Credit",
                                                        String.valueOf(currentSemester.getTotalCreditHour()),
                                                        VaadinIcon.TROPHY),
                                        helperCourseInfoSection("GPA", String.valueOf(studentData.getCgpa()),
                                                        VaadinIcon.MEDAL)
                        };

                        temp[0].addClassName("dashboard-course-info-section-child");
                        temp[1].addClassName("dashboard-course-info-section-child");
                        temp[2].addClassName("dashboard-course-info-section-child");
                        tempLayoutContainer.add(temp[0], temp[1], temp[2]);

                        return tempLayoutContainer;

                }

                private HorizontalLayout helperCourseInfoSection(String title, String number, VaadinIcon icon) {
                        HorizontalLayout tempLayoutContainer = new HorizontalLayout();

                        VerticalLayout tempLayout = new VerticalLayout();
                        Paragraph paragraph = new Paragraph(title);
                        H1 heading = new H1(number);

                        tempLayout.add(paragraph, heading);
                        Icon icn = new Icon(icon);
                        icn.addClassName("iconWrapper-icon");

                        Span iconWrapper = new Span();
                        iconWrapper.add(icn);
                        iconWrapper.addClassName("iconWrapper");

                        tempLayoutContainer.add(tempLayout, iconWrapper);
                        return tempLayoutContainer;
                }

                private VerticalLayout courseEnrollSection() {
                        VerticalLayout tempLayoutContainer = new VerticalLayout();

                        HorizontalLayout temp = new HorizontalLayout(
                                        new VerticalLayout(
                                                        new H2("Currently Enrolled Courses"),
                                                        new Paragraph("Courses you are taking this semester")),
                                        new Anchor("/courses", new HorizontalLayout(
                                                        new H6("Enroll More"), new Icon(VaadinIcon.ARROW_RIGHT))));
                        temp.setWidthFull();

                        tempLayoutContainer.add(
                                        temp,
                                        displayCourses());

                        return tempLayoutContainer;
                }

                private HorizontalLayout displayCourses() {
                        HorizontalLayout tempLayoutContainer = new HorizontalLayout();
                        tempLayoutContainer.addClassName("dashboard-displaycourse");

                        if (studentData.getCurrentSemester() == null
                                        || studentData.getCurrentSemester().getCourses() == null
                                        || studentData.getCurrentSemester().getCourses().isEmpty()) {
                                tempLayoutContainer.add(new Paragraph("No courses enrolled for the current semester."));
                                return tempLayoutContainer;
                        }

                        for (CourseEntity course : studentData.getCurrentSemester().getCourses()) {
                                tempLayoutContainer.add(
                                                displayCoursesHelper(course));
                        }

                        return tempLayoutContainer;
                }

                private VerticalLayout displayCoursesHelper(CourseEntity subject) {
                        VerticalLayout tempLayoutContainer = new VerticalLayout();
                        tempLayoutContainer.addClassName("dashboard-displaycourse-child");

                        Span courseNameSpan = new Span(subject.getCourseName());
                        courseNameSpan.addClassName("dashboard-displaycourse-child-courseName");
                        Span instructorSpan = new Span(subject.getInstructor());
                        instructorSpan.addClassName("dashboard-displaycourse-child-instructor");
                        Span courseCodeSpan = new Span(subject.getCourseCode());
                        courseCodeSpan.addClassName("dashboard-displaycourse-child-courseCode");
                        Span creditHourSpan = new Span(String.valueOf(subject.getCreditHour()).concat(" Cr"));
                        creditHourSpan.addClassName("dashboard-displaycourse-child-creditHour");
                        Section semesterSection = new Section(
                                        new Icon(VaadinIcon.CALENDAR_O),
                                        new Span(" " + String.valueOf(subject.getSemesterNo()) + " Semester"));
                        creditHourSpan.addClassName("dashboard-displaycourse-child-duration");

                        HorizontalLayout temp = new HorizontalLayout(courseCodeSpan, creditHourSpan);
                        temp.setWidthFull();

                        tempLayoutContainer.add(
                                        temp,
                                        courseNameSpan,
                                        instructorSpan,
                                        semesterSection);
                        return tempLayoutContainer;
                }
        }
}