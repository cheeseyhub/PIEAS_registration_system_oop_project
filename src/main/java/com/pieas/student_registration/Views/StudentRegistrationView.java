package com.pieas.student_registration.Views;

import java.text.Normalizer.Form;

import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.frontendtools.installer.VerificationException;

@Route("registration")
@StyleSheet("styles/style.css")
@PageTitle("Student Registration")

public class StudentRegistrationView extends HorizontalLayout {
    StudentRegistrationView() {
        this.setWidthFull();
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
            tempLayoutContainer.addClassName("registration-main");

            tempLayoutContainer.add(Header(), UserInformationForm());

            return tempLayoutContainer;
        }

        private HorizontalLayout Header() {
            HorizontalLayout tempHorizontalLayout = new HorizontalLayout();

            Icon icon = new Icon(VaadinIcon.USER_CARD);
            Span iconWrapper = new Span();
            iconWrapper.addClassName("iconWrapper");
            icon.addClassName("iconWrapper-icon");
            iconWrapper.add(icon);

            tempHorizontalLayout.add(iconWrapper,
                    new VerticalLayout(
                            new H2("Student Registration"),
                            new Paragraph("Keep your personal and academic information up to date.")));
            return tempHorizontalLayout;
        }

        private FormLayout UserInformationForm() {
            FormLayout formLayoutContainer = new FormLayout();

            return formLayoutContainer;
        }
    }

}