package com.pieas.student_registration.Views;

import org.springframework.beans.factory.annotation.Autowired;
import com.pieas.student_registration.Services.StudentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.ButtonVariant;

@Route("login")
public class LoginView extends HorizontalLayout {

    public LoginView(@Autowired StudentService studentService) {
        setSizeFull();

        LoginSidebar sidebar = new LoginSidebar();
        LoginForm loginForm = new LoginForm(studentService);

        sidebar.setWidth("50%");
        sidebar.setHeightFull();

        loginForm.setWidth("50%");
        loginForm.setHeightFull();

        add(sidebar, loginForm);
    }
}

class LoginSidebar extends VerticalLayout {

    public LoginSidebar() {
        setWidthFull();
        setHeightFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);
        setPadding(true);
        setSpacing(true);

        getStyle().set("background", "linear-gradient(135deg, #667eea 0%, #764ba2 100%)");
        getStyle().set("color", "white");

        H1 heading = new H1("Excellence in Engineering and Sciences");
        heading.getStyle().set("color", "white");
        heading.getStyle().set("font-size", "2.5rem");
        heading.getStyle().set("text-align", "center");

        Paragraph paragraph = new Paragraph(
                "Manage Your Registration, Course Enrollment, and Academic Results - All in One Place.");
        paragraph.getStyle().set("color", "rgba(255,255,255,0.9)");
        paragraph.getStyle().set("text-align", "center");
        paragraph.getStyle().set("font-size", "1.1rem");
        paragraph.getStyle().set("max-width", "80%");

        Paragraph copyrightNote = new Paragraph("© 2026 Pakistan Institute of Engineering & Applied Sciences");
        copyrightNote.getStyle().set("color", "rgba(255,255,255,0.7)");
        copyrightNote.getStyle().set("font-size", "0.9rem");
        copyrightNote.getStyle().set("margin-top", "50px");

        add(heading, paragraph, copyrightNote);
    }
}

class LoginForm extends VerticalLayout {

    private final StudentService studentService;
    private Button loginButton;
    private ComboBox<String> departmentCombo;
    private TextField regNoField;
    private PasswordField passwordField;

    public LoginForm(StudentService studentService) {
        this.studentService = studentService;

        setWidthFull();
        setHeightFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);
        setPadding(true);
        setSpacing(true);

        getStyle().set("background", "white");

        VerticalLayout formContainer = new VerticalLayout();
        formContainer.setWidth("100%");
        formContainer.setMaxWidth("450px");
        formContainer.setPadding(true);
        formContainer.setSpacing(true);
        formContainer.setAlignItems(FlexComponent.Alignment.STRETCH);

        H2 header = new H2("Sign in to Your Account");
        header.getStyle().set("margin", "0 0 10px 0");
        header.getStyle().set("color", "#333");

        Paragraph instructionText = new Paragraph("Use your department and registration number to continue.");
        instructionText.getStyle().set("color", "#666");
        instructionText.getStyle().set("margin", "0 0 20px 0");

        departmentCombo = new ComboBox<>("Department");
        departmentCombo.setItems(
                "BS Computer and Information Sciences",
                "BS Mechanical Engineering",
                "BS Materials and Metallurgy Engineering",
                "BS Chemical Engineering",
                "BS Physics",
                "BS Electrical Engineering");
        departmentCombo.setWidthFull();
        departmentCombo.setPlaceholder("Select your department");
        departmentCombo.setClearButtonVisible(true);

        regNoField = new TextField("Registration Number");
        regNoField.setPattern("\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}");
        regNoField.setMaxLength(16);
        regNoField.setPlaceholder("03-3-1-079-2025");
        regNoField.setHelperText("Format: XX-X-X-XXX-XXXX");
        regNoField.setWidthFull();

        passwordField = new PasswordField("Password");
        passwordField.setPlaceholder("Enter your password");
        passwordField.setWidthFull();

        loginButton = new Button("Sign In");
        loginButton.setWidthFull();
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        formContainer.add(header, instructionText, departmentCombo, regNoField, passwordField, loginButton);
        add(formContainer);

        loginButton.addClickListener(event -> {

            String department = departmentCombo.getValue();
            String registrationNumber = regNoField.getValue();
            String password = passwordField.getValue();

            if (department == null || department.isEmpty()) {
                Notification.show("Please select a department", 3000, Notification.Position.MIDDLE);
                return;
            }

            if (registrationNumber == null || registrationNumber.isEmpty()) {
                Notification.show("Please enter registration number", 3000, Notification.Position.MIDDLE);
                return;
            }

            if (!registrationNumber.matches("\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}")) {
                Notification.show("Invalid registration number format", 3000, Notification.Position.MIDDLE);
                return;
            }

            if (password == null || password.isEmpty()) {
                Notification.show("Please enter password", 3000, Notification.Position.MIDDLE);
                return;
            }

            loginButton.setEnabled(false);
            loginButton.setText("Signing in...");

            try {
                if (this.studentService.authenticateUser(department, registrationNumber, password)) {
                    Notification.show("Welcome!");
                    UI.getCurrent().navigate("dashboard");
                } else {
                    Notification.show("Invalid credentials");
                    passwordField.clear();
                }
            } catch (Exception e) {
                Notification.show("Login error. Please try again.");
            } finally {
                loginButton.setEnabled(true);
                loginButton.setText("Sign In");
            }
        });
    }
}