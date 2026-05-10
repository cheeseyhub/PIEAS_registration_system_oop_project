package com.pieas.student_registration.Views;

import java.time.LocalDate;
import java.util.Arrays;

import com.pieas.student_registration.Views.TemplateClasses.*;
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
    StudentRegistrationView() {
        this.setWidthFull();
        this.setHeightFull();
        this.getStyle().set("height", "max-content");
        this.setSpacing(false);
        this.setPadding(false);

        this.add(new Sidebar(), new MainView());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent e) {

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

            TextField fatherName = new TextField("Father Name");

            DatePicker dateOfBirth = new DatePicker("Date of Birth");
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

            NumberField cnic = new NumberField("CNIC");
            cnic.setPlaceholder("XXXXX-XXXXXXX-X");
            cnic.setAllowedCharPattern("[0-9-]");
            cnic.setRequired(true);

            TextField domicile = new TextField("Domicile");

            personalInfoSection.add(name, fatherName, dateOfBirth, gender, cnic, domicile);

            Section educationInfoSection = new Section();
            educationInfoSection.addClassName("registrationForm-section");

            Select<String> department = new Select<>("Department", "BS CIS", "BS MEE", "BS EE", "BS ME");
            department.setPlaceholder("Department");

            Select<String> degreeTitle = new Select<>("Degree Title", "BS", "MS", "Phd");
            degreeTitle.setRequiredIndicatorVisible(true);

            TextField degreeName = new TextField("Degree Name");
            degreeName.setRequired(true);

            TextField rollNo = new TextField("Roll No.");
            rollNo.setRequired(true);
            rollNo.setValue("BS-25-SB-100246");

            TextField regNo = new TextField("Registration Number");
            regNo.setValue("03-3-1-069-2025");
            regNo.setRequired(true);

            TextField libraryId = new TextField("Library ID");

            educationInfoSection.add(
                    department, degreeTitle, degreeName,
                    rollNo, regNo, libraryId);

            Section contactInfoSection = new Section();
            contactInfoSection.addClassName("registrationForm-section-contact");

            EmailField emailAddress = new EmailField("Email");
            emailAddress.setRequired(true);

            EmailField pieasEmailAddress = new EmailField("PIEAS Email Address");
            pieasEmailAddress.setRequired(true);
            pieasEmailAddress.setPlaceholder("bscs1122@pieas.edu.pk");

            NumberField phoneNumber = new NumberField("Phone Number");
            phoneNumber.setPlaceholder("+92--- -------");
            phoneNumber.setRequired(true);

            NumberField emergencyPhoneNumber = new NumberField("Emergency Contact Number");
            emergencyPhoneNumber.setPlaceholder("+92--- -------");
            emergencyPhoneNumber.setRequired(true);

            TextArea address = new TextArea("Address");
            address.setRequired(true);
            contactInfoSection.add(
                    emailAddress, pieasEmailAddress, phoneNumber, emergencyPhoneNumber, address);

            Button updateInformationButton = new Button("Update Information");
            updateInformationButton.addClassName("registrationForm-updateButton");

            // Updates the user information
            updateInformationButton.addClickListener(e -> {

            });

            // CHECKS IF ADMIN ALLOWED USERS TO EDIT VALUE
            // CAN USE SATIC VARIABLE FOR ALL STUDENTS
            // if(!Student.canEdit) means not allowed set value read only

            if (!true) {
                name.setReadOnly(true);
                fatherName.setReadOnly(true);
                dateOfBirth.setReadOnly(true);
                cnic.setReadOnly(true);
                domicile.setReadOnly(true);
                department.setReadOnly(true);
                degreeTitle.setReadOnly(true);
                degreeName.setReadOnly(true);
                rollNo.setReadOnly(true);
                rollNo.setEnabled(false);
                regNo.setReadOnly(true);
                libraryId.setReadOnly(true);
                pieasEmailAddress.setReadOnly(true);
            }

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

            return tempVerticalLayout;
        }
    }

}
