package com.pieas.student_registration.Views.AdminViews;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ManageDepartmentView extends VerticalLayout {

    public ManageDepartmentView() {
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

        Set<String> selectedValues = degreeTitleSelect.getValue();

        Button addButton = new Button("Add Department");
        addButton.addClassName("addDepartmentButton");

        VerticalLayout degreeNameLayout = addDegreeNameFields();
        List<String> degreeNames = getDegreeNamesFromLayout(degreeNameLayout);

        addButton.addClickListener(e -> {
            // Add department logic here
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
        headerRow.expand(new H2("Add Degree Name")); // Push button to the right

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
            // Skip the header row (first component)
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

        DepartmentEntity departments[] = new DepartmentEntity[10];

        for (int count = 0; count < 10; count++) {

            departments[count] = new DepartmentEntity();

            departments[count].setDepartmentName("Computer and Information Sciences");
            departments[count].setDegreeTitle(new java.util.ArrayList<String>(
                    java.util.Arrays.asList("BS", "MS", "PHD")));
            departments[count].setDegreeName(new java.util.ArrayList<String>(
                    java.util.Arrays.asList("BS Computer and Information Sciences",
                            "MS Computer and Information Sciences",
                            "PHD Computer and Information Sciences")));
        }

        tempLayout.add(new HorizontalLayout(
                new Span("Department Name"),
                new Span("Degrees Offered"),
                new Span("Degree Title"),
                new Span("Edit")));
        for (DepartmentEntity department : departments) {
            tempLayout.add(displayDepartmentTemplate(department));
        }

        return tempLayout;
    }

    private HorizontalLayout displayDepartmentTemplate(DepartmentEntity department) {
        HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
        tempHorizontalLayout.setWidthFull();
        tempHorizontalLayout.setHeightFull();

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

        Button btn = new Button(new Icon(VaadinIcon.TRASH));

        btn.addClassName("department-display-table-button");
        btn.addClickListener(e -> {

        });

        tempHorizontalLayout.add(btn);
        return tempHorizontalLayout;
    }

}
