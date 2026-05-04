package com.pieas.student_registration.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Services.StudentService;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("login")
@StyleSheet("context://styles/style.css")

public class LoginView extends VerticalLayout {
    @Autowired
    StudentService studentService;

    public LoginView() {
        // CSS for LoginView Class
        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);
        setPadding(true);
        setSpacing(true);

        addClassName("login-view");

        H1 header = new H1("PIEAS Student Management");
        header.addClassName("h1");
        header.addClassName("header");
        header.setWidth("50vw");

        ComboBox<String> department = new ComboBox<>("Department");
        department.setItems(
                "BS Computer and Information Sciences",
                "BS Mechanical Engineering",
                "BS Materials and Metallurgy Engineering",
                "BS Checmical Engineering",
                "BS Physics",
                "BS Electrical Engineering");
        department.setWidth("400px");

        TextField regNo = new TextField();
        regNo.setPattern("\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}");
        regNo.setMaxLength(16);
        regNo.setPlaceholder("Registration No.");
        regNo.setHelperText("Format: XX-X-X-XXX-XXXX (e.g., 03-3-1-079-2025)");
        regNo.addClassName("regNo");
        regNo.setWidth("400px");

        PasswordField password = new PasswordField();
        password.setPlaceholder("Enter Password ");
        password.addClassName("password");
        password.setWidth("400px");

        Button loginBtn = new Button("Sign In");
        loginBtn.addClassName("login-button");
        loginBtn.setWidth("200px");

        loginBtn.addClickListener(e -> {
            String dep = department.getValue();
            String reg = regNo.getValue();
            String passwordUser = password.getValue();

            if (dep == null || dep.isEmpty()) {
                Notification.show("Please Select a Department");
                return;
            }

            if (reg == null || reg.isEmpty()) {
                Notification.show("Please Enter a Registration Number");
                return;
            }

            if (passwordUser == null || passwordUser.isEmpty()) {
                Notification.show("Please Enter a Password");
                return;
            }

            if (authenticate(dep, reg, passwordUser)) {
                Notification.show("Welcome!");
                UI.getCurrent().navigate("main");
            } else {
                Notification.show("Invalid Credentials");
            }

        });

        add(header, department, regNo, password, loginBtn);
    }

    private boolean authenticate(String department, String registrationNumber, String password) {
        return studentService.authenticateUser(department, registrationNumber, password);
    }

}
