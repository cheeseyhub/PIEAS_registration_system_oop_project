package com.pieas.student_registration;

import com.vaadin.flow.component.notification.Notification.Position;
import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("changePassword")
@PageTitle("Change Password")
@StyleSheet("styles/style.css")

public class ChangePasswordView extends HorizontalLayout {
    public ChangePasswordView() {
        this.setWidthFull();
        this.setHeightFull();
        this.setPadding(false);
        this.setSpacing(false);

        this.add(new Sidebar(),
                new MainView());
    }
}

class MainView extends VerticalLayout {
    MainView() {
        this.setWidthFull();
        this.setHeightFull();
        this.setPadding(false);
        this.setSpacing(false);

        this.add(new Header(), changePasswordView());
    }

    private VerticalLayout changePasswordView() {
        VerticalLayout tempLayoutContainer = new VerticalLayout();

        tempLayoutContainer.setWidthFull();
        tempLayoutContainer.setHeightFull();
        tempLayoutContainer.addClassName("ChangePasswordView");

        tempLayoutContainer.add(changePasswordHeader(), changePasswordForm());
        return tempLayoutContainer;
    }

    private HorizontalLayout changePasswordHeader() {
        HorizontalLayout tempLayoutContainer = new HorizontalLayout();

        H2 heading = new H2("Change Password");
        Paragraph paragraphNote = new Paragraph("Choose a strong password to keep your account secure.");

        Icon icon = new Icon(VaadinIcon.KEY_O);
        icon.addClassName("ChangePasswordView-icon");

        Span iconWrapper = new Span();
        iconWrapper.addClassName("ChangePasswordView-iconWrapper");
        iconWrapper.add(icon);

        tempLayoutContainer.add(iconWrapper, new VerticalLayout(
                heading, paragraphNote));
        return tempLayoutContainer;
    }

    private VerticalLayout changePasswordForm() {
        VerticalLayout tempLayoutContainer = new VerticalLayout();
        tempLayoutContainer.addClassName("ChangePasswordView-form");

        PasswordField passwordField = new PasswordField("New Password", "At least 8 characters");
        passwordField.addClassName("ChangePasswordView-form-passwordField");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password", "Re-enter the password");
        confirmPasswordField.addClassName("ChangePasswordView-form-passwordField");

        Paragraph passwordNote = new Paragraph(
                "Use a unique password you don't reuse on other sites. Mix letters, numbers, and symbols for stronger protection.");
        passwordNote.addClassName("ChangePasswordView-form-passwordNote");

        Button changePasswordButton = new Button("Change Password");
        changePasswordButton.addClassName("ChangePasswordView-form-button");

        // Change Password Function
        changePasswordButton.addClickListener(e -> {
            // Fetch Value from both fields
            String passwordString = passwordField.getValue();
            String confirmPasswordString = confirmPasswordField.getValue();

            if (passwordString == null || passwordString.isEmpty()) {
                Notification.show("Please confirm password", 2000, Position.BOTTOM_END);
                return;
            }

            if (confirmPasswordString == null || confirmPasswordString.isEmpty()) {
                Notification.show("Please confirm password", 2000, Position.BOTTOM_END);
                return;
            }
        });

        tempLayoutContainer.add(passwordField, confirmPasswordField, passwordNote, changePasswordButton);
        return tempLayoutContainer;
    }
}
