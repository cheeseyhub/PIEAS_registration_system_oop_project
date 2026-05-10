package com.pieas.student_registration.Views;

import com.pieas.student_registration.UI.Utils.AuthUtil;
import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("result")
@StyleSheet("styles/style.css")
@PageTitle("Student Registration")

public class ResultView extends HorizontalLayout implements BeforeEnterObserver {
    private String currentUser;

    @Override
    public void beforeEnter(BeforeEnterEvent e) {
        AuthUtil.requireLogin(e);
    }

    public ResultView() {
        try {
            this.currentUser = AuthUtil.getCurrentStudentName();
        } catch (Exception e) {
            UI.getCurrent().navigate("");
            return;
        }

        add(
                new Sidebar(),
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

            this.add(new Header(studentName), Main());
        }

        private VerticalLayout Main() {
            VerticalLayout tempLayoutContainer = new VerticalLayout();
            tempLayoutContainer.addClassName("result-view-main");
            tempLayoutContainer.setWidthFull();
            tempLayoutContainer.setHeightFull();

            tempLayoutContainer.add(MainViewHeader(), semesterNavbar(), displayResult());

            return tempLayoutContainer;
        }

        private HorizontalLayout MainViewHeader() {
            HorizontalLayout tempLayoutContainer = new HorizontalLayout();
            tempLayoutContainer.setWidthFull();
            tempLayoutContainer.addClassName("result-main-header");
            tempLayoutContainer.add(leftHeader(), rightHeader());
            return tempLayoutContainer;
        }

        private HorizontalLayout leftHeader() {
            HorizontalLayout tempLayoutContainer = new HorizontalLayout();
            tempLayoutContainer.getStyle().set("align-self", "left");
            Span iconWrapper = new Span();

            Icon icon = new Icon(VaadinIcon.ACADEMY_CAP);
            icon.addClassName("iconWrapper-icon");
            iconWrapper.add(icon);
            iconWrapper.addClassName("iconWrapper");

            tempLayoutContainer.add(
                    iconWrapper,
                    new VerticalLayout(
                            new H2("Academic Result"),
                            new Paragraph("Review your semester-wise performance.")));

            return tempLayoutContainer;
        }

        private HorizontalLayout rightHeader() {
            HorizontalLayout tempLayoutContainer = new HorizontalLayout();
            tempLayoutContainer.addClassName("result-main-header-right");
            Icon icon = new Icon(VaadinIcon.BAR_CHART_H);
            icon.addClassName("result-icon");

            tempLayoutContainer.add(
                    icon,
                    new VerticalLayout(
                            new Paragraph("CGPA"),
                            new H2("0.00")));

            return tempLayoutContainer;
        }

        private HorizontalLayout semesterNavbar() {
            HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
            tempHorizontalLayout.addClassName("semesterNavbar");
            Button btn[] = { semesterNabarTemplate("All courses"),
                    semesterNabarTemplate("Fall 2025"),
                    semesterNabarTemplate("Spring 2026") };

            for (Button button : btn) {
                button.addClickListener(e -> {
                    for (Button tempBtn : btn) {
                        if (tempBtn.hasClassName("semesterNavbarCell-active"))
                            tempBtn.removeClassName("semesterNavbarCell-active");
                    }

                    button.addClassName("semesterNavbarCell-active");

                });
            }

            btn[0].click();

            tempHorizontalLayout.add(btn);
            return tempHorizontalLayout;
        }

        private Button semesterNabarTemplate(String text) {
            Button textButton = new Button(text);
            textButton.addClassName("semesterNavbarCell");
            return textButton;
        }

        private VerticalLayout displayResult() {
            VerticalLayout tempLayoutContainer = new VerticalLayout();
            tempLayoutContainer.addClassName("resultView-table");

            tempLayoutContainer.add(
                    displayResultRowTemplate(new String[] { "Course", "Tilte", "Semester", "Credits", "Grade", "GPA" }),
                    displayResultRowTemplate(new String[] { "CIS-101", "Computer Programming and Fundamentals",
                            "Fall 2025", "3", "A", "4.0" }),
                    displayResultRowTemplate(new String[] { "CIS-101", "Computer Programming and Fundamentals",
                            "Fall 2025", "3", "B", "4.0" }),
                    displayResultRowTemplate(new String[] { "CIS-101", "Computer Programming and Fundamentals",
                            "Fall 2025", "3", "C", "4.0" }),
                    displayResultRowTemplate(new String[] { "CIS-101", "Computer Programming and Fundamentals",
                            "Fall 2025", "3", "F", "4.0" }),
                    displayResultRowTemplate(new String[] { "CIS-101", "Computer Programming and Fundamentals",
                            "Fall 2025", "3", "D", "4.0" }));

            return tempLayoutContainer;
        }

        private HorizontalLayout displayResultRowTemplate(String data[]) {
            HorizontalLayout tempLayoutContainer = new HorizontalLayout();
            tempLayoutContainer.addClassName("resultView-table-row");
            tempLayoutContainer.setWidthFull();

            for (String i : data) {
                tempLayoutContainer.add(new Span(i));
            }

            if (data[4].contains("A"))
                tempLayoutContainer.getComponentAt(4).addClassName("result-view-grade-A");
            else if (data[4].contains("B"))
                tempLayoutContainer.getComponentAt(4).addClassName("result-view-grade-B");
            else if (data[4].contains("C"))
                tempLayoutContainer.getComponentAt(4).addClassName("result-view-grade-C");
            else if (data[4].contains("D"))
                tempLayoutContainer.getComponentAt(4).addClassName("result-view-grade-D");
            else if (data[4].contains("F"))
                tempLayoutContainer.getComponentAt(4).addClassName("result-view-grade-F");

            return tempLayoutContainer;
        }
    }
}