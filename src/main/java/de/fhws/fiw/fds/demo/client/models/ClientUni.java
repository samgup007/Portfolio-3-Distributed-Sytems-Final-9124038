package de.fhws.fiw.fds.demo.client.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientUni extends AbstractClientModel {

    private String universityName;
    private String uni_location;
    private String department_name;
    private int outgoingStudents;
    private int incomingStudents;
    private String departmentURL;
    private String contactperson;
    private LocalDate firstday_nextSpringSemester;
    private LocalDate firstday_nextautumnSemester;
    private int rank;

    private double tuition_fees;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link modules;

    public ClientUni() {
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

    public int getUniversityRank() {
        return rank;
    }

    public void setUniversityRank(int rank) {
        this.rank = rank;
    }

    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    @JsonIgnore
    public Link getModules() {
        return modules;
    }

    public void setModules(Link modules) {
        this.modules = modules;
    }

    public ClientUni(int rank, double tuition_fees, LocalDate firstday_nextautumnSemester,
    LocalDate firstday_nextSpringSemester, int incomingStudents, int outgoingStudents,
    String contactperson, String departmentURL, String department_name, String uni_location,
    String universityName) {
        this.rank = rank;
        this.tuition_fees = tuition_fees;
        this.firstday_nextautumnSemester = firstday_nextautumnSemester;
        this.firstday_nextSpringSemester = firstday_nextSpringSemester;
        this.incomingStudents = incomingStudents;
        this.outgoingStudents = outgoingStudents;
        this.contactperson = contactperson;
        this.departmentURL = departmentURL;
        this.department_name = department_name;
        this.uni_location = uni_location;
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "UniversityClientModel{" +
                "universityName='" + universityName + '\'' +
                ", departmentURL='" + departmentURL + '\'' +
                ", firstday_nextSpringSemester=" + firstday_nextSpringSemester +
                ", firstday_nextautumnSemester=" + firstday_nextautumnSemester +
                ", tuition_fees=" + tuition_fees +
                ", rank=" + rank +
                ", contactperson='" + contactperson + '\'' +
                ", outgoingStudents=" + outgoingStudents +
                ", incomingStudents=" + incomingStudents +
                ", selfLink=" + selfLink +
                ", modules=" + modules +
                ", uni_location='" + uni_location + '\'' +
                ", department_name='" + department_name + '\'' +
                '}';
    }
}
