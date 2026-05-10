package com.pieas.student_registration.Views;

import com.vaadin.flow.component.notification.Notification.Position;
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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.lang.Thread;

@Route("changePassword")
@PageTitle("Change Password")
@StyleSheet("styles/style.css")

public class ChangePasswordView extends HorizontalLayout implements BeforeEnterObserver {
    private String currentUser;

    @Override
    public void beforeEnter(BeforeEnterEvent e) {
        AuthUtil.requireLogin(e);
    }

    public ChangePasswordView() {
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
            this.setPadding(false);
            this.setSpacing(false);

            this.add(new Header(studentName), changePasswordView());
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
            icon.addClassName("iconWrapper-icon");

            Span iconWrapper = new Span();
            iconWrapper.addClassName("iconWrapper");
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

            Button changePasswordButton = new Button("");
            changePasswordButton.addClassName("ChangePasswordView-form-button");

            Icon saveIcon = new Icon(VaadinIcon.KEY_O);
            changePasswordButton.getElement().appendChild(saveIcon.getElement(),
                    new Span("Change Password").getElement());

            // Change Password Function
            changePasswordButton.addClickListener(e -> {
                // Fetch Value from both fields
                String passwordString = passwordField.getValue();
                String confirmPasswordString = confirmPasswordField.getValue();

                if (passwordString == null || passwordString.isEmpty()) {
                    Notification.show("Please enter password", 2000, Position.BOTTOM_END);
                    return;
                }

                if (passwordString.length() < 8 || passwordString.length() > 16) {
                    Notification.show("Invalid Password.\nPassword must be between 8-16 characters", 2000,
                            Position.BOTTOM_END);
                    return;
                }

                if (confirmPasswordString == null || confirmPasswordString.isEmpty()) {
                    Notification.show("Please confirm password", 2000, Position.BOTTOM_END);
                    return;
                }

                if (!passwordString.matches(confirmPasswordString)) {
                    Notification.show("Passwords do not match", 2000, Position.BOTTOM_END);
                    return;
                }

                // PASSWORD CHANGE CODE HERE
                try {
                    Notification.show("Password Changed Successfully", 2000, Position.BOTTOM_END);
                    Thread.sleep(3);
                    UI.getCurrent().navigate("main");
                } catch (Exception err) {
                    Notification.show("Error Changing Passwor.\nTry Again Later", 2000, Position.BOTTOM_END);
                    return;
                }
            });

            tempLayoutContainer.add(passwordField, confirmPasswordField, passwordNote, changePasswordButton);
            return tempLayoutContainer;
        }
    }
}