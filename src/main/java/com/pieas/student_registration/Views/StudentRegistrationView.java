package com.pieas.student_registration.Views;

import com.pieas.student_registration.Views.TemplateClasses.*;
import com.vaadin.copilot.shaded.helger.base.email.EmailAddress;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("registration")
@StyleSheet("styles/style.css")
@PageTitle("Student Registration")

public class StudentRegistrationView extends HorizontalLayout {
    StudentRegistrationView() {
        this.setWidthFull();
        this.setHeightFull();
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
            tempLayoutContainer.setWidthFull();
            tempLayoutContainer.setHeightFull();

            tempLayoutContainer.add(Header(), UserInformationForm());

            return tempLayoutContainer;
        }

        private HorizontalLayout Header() {
            HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
            tempHorizontalLayout.setWidthFull();
            tempHorizontalLayout.setWidthFull();

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
            formLayoutContainer.addClassName("registration-form");
            formLayoutContainer.setWidthFull();
            formLayoutContainer.setWidthFull();

            TextField name = new TextField("Name");
            TextField fatherName = new TextField("Father Name");
            EmailField emailAddress = new EmailField("Email");
            TextField rollNo = new TextField("Roll No.");

            NumberField regNo = new NumberField("Registration Number");
            NumberField cnic = new NumberField("CNIC");
            TextField Address = new TextField("Registration Number");
            TextField Domicile = new TextField("Domicile");
            NumberField phoneNumber = new NumberField("Phone Number");
            NumberField emergencyPhoneNumber = new NumberField("Emergency Contact Number");

            DatePicker dateOfBirth = new DatePicker("Date Of Birth");

            ComboBox<> department = new ComboBox<>(

            );

            formLayoutContainer.add(
                    name, fatherName, emailAddress, rollNo, regNo, dateOfBirth, cnic, Address, Domicile, phoneNumber,
                    emergencyPhoneNumber);
            return formLayoutContainer;
        }
    }

}