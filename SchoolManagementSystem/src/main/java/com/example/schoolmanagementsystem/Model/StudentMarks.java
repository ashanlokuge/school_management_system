package com.example.schoolmanagementsystem.Model;

import java.util.HashMap;
import java.util.Map;

public class StudentMarks {
    private int studentId;
    private String name;
    private int total;
    private double average;
    private int rank;
    private Map<String, Integer> subjectMarks = new HashMap<>();

    public StudentMarks(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public Map<String, Integer> getSubjectMarks() { return subjectMarks; }

    public void addSubjectMarks(String subject, int marks) {
        subjectMarks.put(subject, marks);
    }

    public Integer getMarksForSubject(String subject) {
        return subjectMarks.get(subject);
    }
}