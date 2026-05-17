package com.pieas.student_registration.Views.StudentViews;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Services.DepartmentService;
import com.pieas.student_registration.Services.StudentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.ButtonVariant;

@Route("")
public class LoginView extends HorizontalLayout {

    @Autowired
    private StudentService studentService;

    @Deprecated
    private DepartmentService departmentService;

    public LoginView(StudentService stdService, DepartmentService departmentService) {
        this.studentService = stdService;
        this.departmentService = departmentService;
        setSizeFull();

        LoginSidebar sidebar = new LoginSidebar();
        LoginForm loginForm = new LoginForm(studentService);

        sidebar.setWidth("50%");
        sidebar.setHeightFull();

        loginForm.setWidth("50%");
        loginForm.setHeightFull();

        add(sidebar, loginForm);
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

        private Button loginButton;
        private Select<String> departmentSelect;
        private TextField regNoField;
        private PasswordField passwordField;
        private StudentService studentService;

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

            Select<String> departmentSelect = new Select<>("Department");
            List<String> temp = new ArrayList<>();
            for (DepartmentEntity dep : departmentService.getAllDepartments()) {
                for (String degree : dep.getDegreeName()) {
                    temp.add(degree);
                }
            }

            this.departmentSelect = departmentSelect;
            departmentSelect.setItems(temp);
            departmentSelect.setWidthFull();
            departmentSelect.setPlaceholder("Select your department");

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

            formContainer.add(header, instructionText, departmentSelect, regNoField, passwordField, loginButton);
            add(formContainer);

            loginButton.addClickListener(event -> {

                String department = departmentSelect.getValue();
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
                        studentService.storeStudentData(registrationNumber);
                        UI.getCurrent().navigate("main");
                    } else {
                        Notification.show("Invalid credentials");
                        // passwordField.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Login Exception: " + e.getMessage());
                    System.err.println("Exception type: " + e.getClass().getName());
                    Notification.show("Login error: " + e.getMessage());
                } finally {
                    loginButton.setEnabled(true);
                    loginButton.setText("Sign In");
                }
            });
        }
    }
}