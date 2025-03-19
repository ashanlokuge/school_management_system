package com.example.schoolmanagementsystem.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private final StringProperty name;
    private final StringProperty studentId;
    private final StringProperty grade;
    private final StringProperty mobileNumber;

    public Student(String name, String studentId, String grade, String mobileNumber) {
        this.name = new SimpleStringProperty(name);
        this.studentId = new SimpleStringProperty(studentId);
        this.grade = new SimpleStringProperty(grade);
        this.mobileNumber = new SimpleStringProperty(mobileNumber);
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty studentIdProperty() { return studentId; }
    public StringProperty gradeProperty() { return grade; }
    public StringProperty mobileNumberProperty() { return mobileNumber; }

    public String getName() { return name.get(); }
    public String getStudentId() { return studentId.get(); }
    public String getGrade() { return grade.get(); }
    public String getMobileNumber() { return mobileNumber.get(); }
}
