package com.pieas.student_registration.Views;

import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.copilot.shaded.guava.collect.Table;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("result")
@StyleSheet("styles/style.css")
@PageTitle("Student Registration")

public class ResultView extends HorizontalLayout {
    ResultView() {
        this.setWidthFull();
        this.setHeightFull();
        this.setSpacing(false);
        this.setPadding(false);

        this.add(new Sidebar(), new MainView());
    }

    class MainView extends VerticalLayout {
        MainView() {
            this.setWidthFull();
            this.setHeightFull();
            this.setSpacing(false);
            this.setPadding(false);

            this.add(new Header(), Main());
        }

        private VerticalLayout Main() {
            VerticalLayout tempLayoutContainer = new VerticalLayout();
            tempLayoutContainer.addClassName("result-view-main");
            tempLayoutContainer.setWidthFull();
            tempLayoutContainer.setHeightFull();

            tempLayoutContainer.add(MainViewHeader());

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

    }

}