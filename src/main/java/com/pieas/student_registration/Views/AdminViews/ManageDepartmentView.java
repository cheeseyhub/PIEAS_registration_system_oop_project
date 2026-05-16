package com.pieas.student_registration.Views.AdminViews;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Services.DepartmentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ManageDepartmentView extends VerticalLayout {

    @Autowired
    DepartmentService departmentService;

    public ManageDepartmentView(DepartmentService departmentService) {
        this.departmentService = departmentService;

        this.addClassName("admin-layout-main");
        this.setWidthFull();
        this.setHeightFull();
        this.setSpacing(false);

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();

        header.addClassName("addDepartmentHeader");

        Button addNewDepartment = new Button("Add New Department");
        addNewDepartment.addClassName("add-new-department-button");

        addNewDepartment.addClickListener(e -> {
            this.removeAll();
            this.add(header);
            this.add(addDepartment());
        });

        header.add(new H2("Manage Department"), addNewDepartment);
        this.add(header);
        this.add(displayDepartment());
    }

    private VerticalLayout addDepartment() {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.setHeightFull();
        tempLayout.addClassName("adddepartmentforum");
        tempLayout.setWidthFull();

        TextField department = new TextField("Department Name");
        department.setRequired(true);

        MultiSelectComboBox<String> degreeTitleSelect = new MultiSelectComboBox<>("Select items");
        degreeTitleSelect.setItems("BS", "MS", "PHD");

        Button addButton = new Button("Add Department");
        addButton.addClassName("addDepartmentButton");

        VerticalLayout degreeNameLayout = addDegreeNameFields();

        addButton.addClickListener(e -> {
            List<String> degreeNames = getDegreeNamesFromLayout(degreeNameLayout);
            List<String> selectedValues = new ArrayList<>(degreeTitleSelect.getValue());

            for (String degree : degreeNames)
                Notification.show(degree);

            for (String degree : selectedValues)
                Notification.show(degree);

            departmentService.addDepartment(new DepartmentEntity(department.getValue(),
                    selectedValues, degreeNames));
            UI.getCurrent().getPage().reload();

        });

        tempLayout.add(department, degreeTitleSelect, degreeNameLayout, addButton);
        return tempLayout;
    }

    private VerticalLayout addDegreeNameFields() {
        VerticalLayout degreeNameLayout = new VerticalLayout();
        degreeNameLayout.setWidthFull();
        degreeNameLayout.setSpacing(true);

        HorizontalLayout headerRow = new HorizontalLayout();
        headerRow.setAlignItems(Alignment.CENTER);
        headerRow.setWidthFull();

        Button addDegreeNameButton = new Button(new Icon(VaadinIcon.PLUS));
        addDegreeNameButton.addClassName("add-degree-button");

        headerRow.add(new H2("Add Degree Name"), addDegreeNameButton);
        headerRow.expand(new H2("Add Degree Name"));

        degreeNameLayout.add(headerRow);

        addNewDegreeNameRow(degreeNameLayout);

        addDegreeNameButton.addClickListener(e -> {
            addNewDegreeNameRow(degreeNameLayout);
        });

        return degreeNameLayout;
    }

    private void addNewDegreeNameRow(VerticalLayout parentLayout) {
        HorizontalLayout row = new HorizontalLayout();
        row.setWidthFull();
        row.setAlignItems(Alignment.BASELINE);
        row.setSpacing(true);

        TextField degreeName = new TextField();
        degreeName.setPlaceholder("Enter degree name");
        degreeName.setRequired(true);
        degreeName.setWidthFull();

        Button removeButton = new Button(new Icon(VaadinIcon.TRASH));
        removeButton.addClassName("remove-degree-row-button");
        removeButton.addClickListener(e -> {
            parentLayout.remove(row);
        });

        row.add(degreeName, removeButton);
        row.expand(degreeName);

        parentLayout.add(row);
    }

    private List<String> getDegreeNamesFromLayout(VerticalLayout degreeNameLayout) {
        List<String> degreeNames = new ArrayList<>();

        for (Component component : degreeNameLayout.getChildren().toArray(Component[]::new)) {
            if (component instanceof HorizontalLayout) {
                HorizontalLayout row = (HorizontalLayout) component;

                boolean isHeaderRow = false;
                for (Component child : row.getChildren().toArray(Component[]::new)) {
                    if (child instanceof H2 || child instanceof Button) {
                        isHeaderRow = true;
                        break;
                    }
                }

                if (isHeaderRow) {
                    continue;
                }

                for (Component child : row.getChildren().toArray(Component[]::new)) {
                    if (child instanceof TextField) {
                        TextField textField = (TextField) child;
                        String value = textField.getValue();
                        if (value != null && !value.trim().isEmpty()) {
                            degreeNames.add(value.trim());
                        }
                        break;
                    }
                }
            }
        }

        return degreeNames;
    }

    private VerticalLayout displayDepartment() {
        VerticalLayout tempLayout = new VerticalLayout();
        tempLayout.addClassName("department-display-table");
        tempLayout.setWidthFull();
        tempLayout.setHeightFull();

        tempLayout.add(new HorizontalLayout(
                new Span("Department Name"),
                new Span("Degrees Offered"),
                new Span("Degree Title"),
                new Span("Edit")));
        for (DepartmentEntity department : departmentService.getAllDepartments()) {
            tempLayout.add(displayDepartmentTemplate(department));
        }

        return tempLayout;
    }

    private HorizontalLayout displayDepartmentTemplate(DepartmentEntity department) {
        HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
        tempHorizontalLayout.setWidthFull();

        tempHorizontalLayout.addClassName("department-display-row-template");
        tempHorizontalLayout.add(new Span(department.getDepartmentName()));

        VerticalLayout degreeTitleLayout = new VerticalLayout();
        degreeTitleLayout.addClassName("degree-title-layout");

        for (String degreeTitle : department.getDegreeTitle()) {
            degreeTitleLayout.add(new Span(degreeTitle));
        }
        tempHorizontalLayout.add(degreeTitleLayout);

        VerticalLayout degreeNameLayout = new VerticalLayout();
        degreeNameLayout.setSpacing(false);
        degreeNameLayout.addClassName("degree-name-layout");

        for (String degreeName : department.getDegreeName()) {
            degreeNameLayout.add(new Span(degreeName));
        }
        tempHorizontalLayout.add(degreeNameLayout);

        Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));

        deleteButton.addClassName("department-display-table-button");
        deleteButton.addClickListener(e -> {
            departmentService.deleteDepartment(department.getId());
            UI.getCurrent().getPage().reload();
        });

        tempHorizontalLayout.add(deleteButton);
        return tempHorizontalLayout;
    }

}
