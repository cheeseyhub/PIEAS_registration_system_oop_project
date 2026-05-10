package com.pieas.student_registration.Views;

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
        private String currentUser;

        @Override
        public void beforeEnter(BeforeEnterEvent e) {
                AuthUtil.requireLogin(e);
        }

        public DashboardView() {
                try {
                        this.currentUser = AuthUtil.getCurrentStudentName();
                } catch (Exception e) {
                        UI.getCurrent().navigate("");
                        return;
                }

                add(
                                new Sidebar(),
                                new Main(currentUser));

                this.setWidthFull();
                this.setSpacing(false);
                this.addClassName("dashboard");
        }

        class Main extends VerticalLayout {
                Main(String studentName) {
                        this.setWidthFull();
                        this.setPadding(false);
                        this.setSpacing(false);
                        this.addClassName("main-section");

                        add(
                                        new Header(studentName),
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

                        HorizontalLayout temp[] = {
                                        helperCourseInfoSection("Course Enrolled", "0", VaadinIcon.ACADEMY_CAP),
                                        helperCourseInfoSection("Total Credit", "0", VaadinIcon.TROPHY),
                                        helperCourseInfoSection("GPA", "0.00", VaadinIcon.MEDAL)
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
                        tempLayoutContainer.add(
                                        displayCoursesHelper("Computer Programming and Fundamentals", "Zohaib Kaleem",
                                                        "CIS-101", "3 Cr",
                                                        "Fall 2026"),
                                        displayCoursesHelper("Computer Programming and Fundamentals", "Zohaib Kaleem",
                                                        "CIS-101", "3 Cr",
                                                        "Fall 2026"),
                                        displayCoursesHelper("Computer Programming and Fundamentals", "Zohaib Kaleem",
                                                        "CIS-101", "3 Cr",
                                                        "Fall 2026"),
                                        displayCoursesHelper("Computer Programming and Fundamentals", "Zohaib Kaleem",
                                                        "CIS-101", "3 Cr",
                                                        "Fall 2026"));

                        return tempLayoutContainer;
                }

                private VerticalLayout displayCoursesHelper(String courseName, String instructor, String courseCode,
                                String creditHours, String duration) {
                        VerticalLayout tempLayoutContainer = new VerticalLayout();
                        tempLayoutContainer.addClassName("dashboard-displaycourse-child");

                        Span courseNameSpan = new Span(courseName);
                        courseNameSpan.addClassName("dashboard-displaycourse-child-courseName");
                        Span instructorSpan = new Span(instructor);
                        instructorSpan.addClassName("dashboard-displaycourse-child-instructor");
                        Span courseCodeSpan = new Span(courseCode);
                        courseCodeSpan.addClassName("dashboard-displaycourse-child-courseCode");
                        Span creditHourSpan = new Span(creditHours);
                        creditHourSpan.addClassName("dashboard-displaycourse-child-creditHour");
                        Section durationSpan = new Section(
                                        new Icon(VaadinIcon.CALENDAR_O),
                                        new Span(duration));
                        creditHourSpan.addClassName("dashboard-displaycourse-child-duration");

                        HorizontalLayout temp = new HorizontalLayout(courseCodeSpan, creditHourSpan);
                        temp.setWidthFull();

                        tempLayoutContainer.add(
                                        temp,
                                        courseNameSpan,
                                        instructorSpan,
                                        durationSpan);
                        return tempLayoutContainer;
                }
        }
}