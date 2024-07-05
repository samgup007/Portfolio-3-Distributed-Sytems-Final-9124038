package de.fhws.fiw.fds.demo.server.database.models;

import java.time.LocalDate;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "university")
public class UniversityDB extends AbstractDBModel {

    @Column(name = "rank")
    private int rank;

    @Column(name = "universityName")
    private String universityName;

    @Column(name = "uni_location")
    private String uni_location;

    @Column(name = "department_name")
    private String department_name;

    @Column(name = "departmentURL")
    private String departmentURL;

    @Column(name = "contactperson")
    private String contactperson;

    @Column(name = "outgoingStudents")
    private int outgoingStudents;

    @Column(name = "incomingStudents")
    private int incomingStudents;

    @Column(name = "firstday_nextSpringSemester")
    private LocalDate firstday_nextSpringSemester;

    @Column(name = "firstday_nextautumnSemester")
    private LocalDate firstday_nextautumnSemester;

    @Column(name = "tuition_fees")
    private double tuition_fees;

    public UniversityDB() {
    }

    public int getUniversityRank() {
        return rank;
    }

    public void setUniversityRank(int rank) {
        this.rank = rank;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCountry() {
        return uni_location;
    }

    public void setCountry(String uni_location) {
        this.uni_location = uni_location;
    }

    public String getDepartmentName() {
        return department_name;
    }

    public void setDepartmentName(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartmentWebsite() {
        return departmentURL;
    }

    public void setDepartmentWebsite(String departmentURL) {
        this.departmentURL = departmentURL;
    }

    public String getContactPerson() {
        return contactperson;
    }

    public void setContactPerson(String contactperson) {
        this.contactperson = contactperson;
    }

    public int getStudentsOutgoing() {
        return outgoingStudents;
    }

    public void setStudentsOutgoing(int outgoingStudents) {
        this.outgoingStudents = outgoingStudents;
    }

    public int getStudentsIncoming() {
        return incomingStudents;
    }

    public void setStudentsIncoming(int incomingStudents) {
        this.incomingStudents = incomingStudents;
    }

    public LocalDate getNextSpringSemesterStart() {
        return firstday_nextSpringSemester;
    }

    public void setNextSpringSemesterStart(LocalDate firstday_nextSpringSemester) {
        this.firstday_nextSpringSemester = firstday_nextSpringSemester;
    }

    public LocalDate getNextAutumnSemesterStart() {
        return firstday_nextautumnSemester;
    }

    public void setNextAutumnSemesterStart(LocalDate firstday_nextautumnSemester) {
        this.firstday_nextautumnSemester = firstday_nextautumnSemester;
    }

    public double getAnnualTuitionFees() {
        return tuition_fees;
    }

    public void setAnnualTuitionFees(double tuition_fees) {
        this.tuition_fees = tuition_fees;
    }
}
