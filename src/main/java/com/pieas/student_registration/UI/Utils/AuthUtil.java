package com.pieas.student_registration.UI.Utils;

import com.pieas.student_registration.Exceptions.NotLoggedIn;
import com.pieas.student_registration.Services.StudentService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    private static StudentService studentService;

    // Spring will inject this
    public AuthUtil(StudentService service) {
        AuthUtil.studentService = service;
    }

    public static boolean isLoggedIn() {
        if (studentService == null)
            return false;
        return studentService.checkStudentLoggedIn();
    }

    public static String getCurrentRegistrationNo() throws NotLoggedIn {
        if (studentService == null)
            throw new NotLoggedIn("Service not available");
        return studentService.getLoggedStudentRegistrationNo();
    }

    public static String getCurrentStudentName() throws NotLoggedIn {
        if (studentService == null)
            throw new NotLoggedIn("Service not available");
        return studentService.getLoggedStudentName();
    }

    public static void requireLogin(BeforeEnterEvent event) {
        if (!isLoggedIn()) {
            event.forwardTo("login");
            Notification.show("Please login to access this page", 3000, Notification.Position.MIDDLE);
        }
    }

    public static void logout() {
        VaadinSession.getCurrent().setAttribute("studentRegistrationNo", null);
        UI.getCurrent().navigate("login");
    }
}