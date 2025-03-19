package com.example.schoolmanagementsystem.Model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Student {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();

    private final StringProperty grade = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> enrollmentDate = new SimpleObjectProperty<>();


    public Student(int id, String firstName, String lastName, String grade, String email,
                   String phone, LocalDate birthDate, LocalDate enrollmentDate) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.grade.set(grade);
        this.email.set(email);
        this.phone.set(phone);
        this.birthDate.set(birthDate);
        this.enrollmentDate.set(enrollmentDate);
    }


    public IntegerProperty idProperty() { return id; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }

    public StringProperty gradeProperty() { return grade; }
    public StringProperty emailProperty() { return email; }


}
