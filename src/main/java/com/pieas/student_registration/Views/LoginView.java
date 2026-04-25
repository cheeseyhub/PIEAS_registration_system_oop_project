package com.pieas.student_registration.Views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        LoginForm login = new LoginForm();
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();

        i18nForm.setTitle("Student Login");
        i18nForm.setUsername("Registration Number");
        i18nForm.setPassword("Password");
        i18nForm.setSubmit("Sign in");

        LoginI18n.ErrorMessage i18nError = i18n.getErrorMessage();
        i18nError.setTitle("Login Failed");
        i18nError.setMessage("Check your registration number  or password and try again");

        login.setI18n(i18n);

        login.setForgotPasswordButtonVisible(false);
        add(new H1("Pieas student login protal"), login);

        login.addLoginListener(e -> {
            boolean isAuthenticated = this.authenticate(e.getUsername(), e.getPassword());
            if (isAuthenticated) {
                getUI().ifPresent(ui -> ui.navigate("dashboard"));
            } else {
                login.setError(true);
            }
        });

    }

    private boolean authenticate(String registrationNumber, String password) {
        return false;
    }

}
