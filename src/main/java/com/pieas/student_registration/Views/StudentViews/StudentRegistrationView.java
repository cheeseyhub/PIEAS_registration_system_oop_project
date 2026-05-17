package com.pieas.student_registration.Views.StudentViews;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.DepartmentService;
import com.pieas.student_registration.Services.StudentService;
import com.pieas.student_registration.UI.Utils.AuthUtil;
import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("registration")
@StyleSheet("styles/style.css")
@PageTitle("Student Registration")

public class StudentRegistrationView extends HorizontalLayout implements BeforeEnterObserver {
    private String currentUser;

    @Autowired
    private StudentService studentService;

    @Autowired
    DepartmentService departmentService;

    private StudentEntity studentData;

    @Override
    public void beforeEnter(BeforeEnterEvent e) {
        AuthUtil.requireLogin(e);
    }

    public StudentRegistrationView(StudentService studentService, DepartmentService departmentService) {
        try {
            this.studentService = studentService;
            this.departmentService = departmentService;
            this.currentUser = AuthUtil.getCurrentStudentName();
            this.studentData = studentService.getLoggedUser();

        } catch (Exception e) {
            UI.getCurrent().navigate("");
            return;
        }

        add(
                new Sidebar("registration"),
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
            tempLayoutContainer.addClassName("registration-main");
            tempLayoutContainer.setWidthFull();
            tempLayoutContainer.setHeightFull();

            tempLayoutContainer.add(Header(), UserInformationForm());

            return tempLayoutContainer;
        }

        private HorizontalLayout Header() {
            HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
            tempHorizontalLayout.setWidthFull();
            tempHorizontalLayout.setHeightFull();

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

        private VerticalLayout UserInformationForm() {
            VerticalLayout tempVerticalLayout = new VerticalLayout();
            tempVerticalLayout.addClassName("registrationForm");
            tempVerticalLayout.setWidthFull();
            tempVerticalLayout.setHeightFull();

            Section personalInfoSection = new Section();
            personalInfoSection.addClassName("registrationForm-section");

            TextField name = new TextField("Name");
            name.setPlaceholder("Name");
            name.setReadOnly(true);
            name.setValue(studentData.getName());

            TextField fatherName = new TextField("Father Name");
            fatherName.setPlaceholder("Father Name");
            fatherName.setRequired(true);
            fatherName.setValue(studentData.getFatherName());

            DatePicker dateOfBirth = new DatePicker("Date of Birth");
            dateOfBirth.setPlaceholder("Date of Birth");
            dateOfBirth.setRequired(true);
            dateOfBirth.setValue(studentData.getDateOfBirth());

            DatePicker.DatePickerI18n i18n = new DatePicker.DatePickerI18n();

            i18n.setDateFormat("EEEE, MMMM dd- yyyy");
            i18n.setMonthNames(Arrays.asList("January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"));
            i18n.setWeekdays(
                    Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

            dateOfBirth.setI18n(i18n);

            dateOfBirth.setMax(LocalDate.now().minusYears(17));
            dateOfBirth.setInitialPosition(LocalDate.now().minusYears(25));

            Select<String> gender = new Select<>("Gender", "Male", "Female");
            gender.setPlaceholder("Gender");
            gender.setRequiredIndicatorVisible(true);
            gender.setValue(studentData.getGender());

            NumberField cnic = new NumberField("CNIC");
            cnic.setPlaceholder("XXXXXXXXXXXXX");
            cnic.setAllowedCharPattern("[0-9-]");
            cnic.setRequired(true);
            cnic.setValue(studentData.getCnic());

            TextField domicile = new TextField("Domicile");
            domicile.setPlaceholder("Domicile");
            domicile.setRequired(true);
            domicile.setValue(studentData.getDomicile());

            personalInfoSection.add(name, fatherName, dateOfBirth, gender, cnic, domicile);

            Section educationInfoSection = new Section();
            educationInfoSection.addClassName("registrationForm-section");

            TextField department = new TextField("Department");
            department.setPlaceholder("Department");
            department.setRequired(true);

            for (DepartmentEntity dep : departmentService.getAllDepartments()) {
                if (dep.getDegreeName().contains(studentData.getDegreeName())) {
                    department.setValue(dep.getDepartmentName());
                    break;
                }

                department.setPlaceholder("Department");
                department.setRequiredIndicatorVisible(true);
                department.setValue(studentData.getDepartment());
                department.setReadOnly(true);

                Select<String> degreeTitle = new Select<>("Degree Title");
                List<String> degreeTitles = new ArrayList<>();
                for (DepartmentEntity dept : departmentService.getAllDepartments()) {
                    degreeTitles.addAll(dept.getDegreeTitle());
                }
                degreeTitle.setItems(degreeTitles);
                degreeTitle.setPlaceholder("Degree Title");
                degreeTitle.setRequiredIndicatorVisible(true);
                degreeTitle.setValue(studentData.getDegreeTitle());

                Select<String> degreeName = new Select<>("Degree Name");
                List<String> degreeNames = new ArrayList<>();
                for (DepartmentEntity dept : departmentService.getAllDepartments()) {
                    degreeNames.addAll(dept.getDegreeName());
                }

                degreeName.setItems(degreeNames);
                degreeName.setPlaceholder("Degree Name");
                degreeName.setRequiredIndicatorVisible(true);
                degreeName.setValue(studentData.getDegreeName());

                TextField rollNo = new TextField("Roll No.");
                rollNo.setRequired(true);
                rollNo.setValue(studentData.getRollNo());

                TextField regNo = new TextField("Registration Number");
                regNo.setValue(studentData.getRegistrationNumber());
                regNo.setRequired(true);
                regNo.setReadOnly(true);

                TextField libraryId = new TextField("Library ID");
                libraryId.setValue(studentData.getLibraryId());
                libraryId.setRequired(true);

                educationInfoSection.add(
                        department, degreeTitle, degreeName,
                        rollNo, regNo, libraryId);

                Section contactInfoSection = new Section();
                contactInfoSection.addClassName("registrationForm-section-contact");

                EmailField emailAddress = new EmailField("Email");
                emailAddress.setRequired(true);
                emailAddress.setPlaceholder("Enter Email Address");
                emailAddress.setValue(studentData.getPersonalEmail());

                EmailField pieasEmailAddress = new EmailField("PIEAS Email Address");
                pieasEmailAddress.setRequired(true);
                pieasEmailAddress.setPlaceholder("Enter PIEAS Email Address");
                pieasEmailAddress.setValue(studentData.getPieasEmail());

                NumberField phoneNumber = new NumberField("Phone Number");
                phoneNumber.setPlaceholder("+92--- -------");
                phoneNumber.setRequired(true);
                phoneNumber.setValue(studentData.getContactNo());

                NumberField emergencyPhoneNumber = new NumberField("Emergency Contact Number");
                emergencyPhoneNumber.setPlaceholder("+92--- -------");
                emergencyPhoneNumber.setRequired(true);
                emergencyPhoneNumber.setValue(studentData.getEmergencyContact());

                TextArea address = new TextArea("Address");
                address.setRequired(true);
                address.setPlaceholder("Enter Address");
                address.setValue(studentData.getAddress());
                contactInfoSection.add(
                        emailAddress, pieasEmailAddress, phoneNumber, emergencyPhoneNumber, address);

                Button updateInformationButton = new Button("Update Information");
                updateInformationButton.addClassName("registrationForm-updateButton");

                updateInformationButton.addClickListener(e -> {
                    try {
                        studentData.setFatherName(fatherName.getValue());
                        studentData.setDateOfBirth(dateOfBirth.getValue());
                        studentData.setGender(gender.getValue());
                        studentData.setCnic(cnic.getValue());
                        studentData.setDomicile(domicile.getValue());
                        studentData.setDegreeTitle(degreeTitle.getValue());
                        studentData.setDegreeName(degreeName.getValue());
                        studentData.setRollNo(rollNo.getValue());
                        studentData.setLibraryId(libraryId.getValue());
                        studentData.setPersonalEmail(emailAddress.getValue());
                        studentData.setPieasEmail(pieasEmailAddress.getValue());
                        studentData.setContactNo(phoneNumber.getValue());
                        studentData.setEmergencyContact(emergencyPhoneNumber.getValue());
                        studentData.setAddress(address.getValue());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                tempVerticalLayout.add(
                        new H5("Personal Information"),
                        new Hr(),
                        personalInfoSection,

                        new H5("Education Information"),
                        new Hr(),
                        educationInfoSection,

                        new H5("Contact Information"),
                        new Hr(),
                        contactInfoSection, updateInformationButton);

            }
            return tempVerticalLayout;
        }

    }
}