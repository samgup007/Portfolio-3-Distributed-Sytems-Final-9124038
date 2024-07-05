package de.fhws.fiw.fds.demo.server.api.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SuttonLink;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.Status;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonRootName("university")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "university")
public class Uni extends AbstractModel {

    
    private String department_name;
    private int outgoingStudents;
    private int rank;
    private String universityName;
    private String uni_location;
    private int incomingStudents;
    private String departmentURL;
    private String contactperson;
    private LocalDate firstday_nextSpringSemester;
    private LocalDate firstday_nextautumnSemester;
    private double tuition_fees;

    @SuttonLink(
            value = "universities/${id}",
            rel = "self"
    )
    private transient Link selfLink;

    @SuttonLink(
            value = "universities/${id}/modules",
            rel = "getModulesOfUniversity"
    )
    private transient Link modules;

    public Uni() {
    }

    public Uni(int rank, double tuition_fees, LocalDate firstday_nextautumnSemester, LocalDate firstday_nextSpringSemester, int incomingStudents, int outgoingStudents, String contactperson, String departmentURL, String department_name, String uni_location, String universityName)
    {
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

    public String getUniversityName()
    {
        return universityName;
    }

    public void setUniversityName(String universityName)
    {
        this.universityName = universityName;
    }

    public String getCountry()
    {
        return uni_location;
    }

    public void setCountry(String uni_location)
    {
        this.uni_location = uni_location;
    }

    public String getDepartmentName()
    {
        return department_name;
    }

    public void setDepartmentName(String department_name)
    {
        this.department_name = department_name;
    }

    public String getDepartmentWebsite()
    {
        return departmentURL;
    }

    public void setDepartmentWebsite(String departmentURL)
    {
        this.departmentURL = departmentURL;
    }

    public String getContactPerson()
    {
        return contactperson;
    }

    public void setContactPerson(String contactperson)
    {
        this.contactperson = contactperson;
    }

    public int getStudentsOutgoing()
    {
        return outgoingStudents;
    }

    public void setStudentsOutgoing(int outgoingStudents)
    {
        this.outgoingStudents = outgoingStudents;
    }

    public int getStudentsIncoming()
    {
        return incomingStudents;
    }

    public void setStudentsIncoming(int incomingStudents)
    {
        this.incomingStudents = incomingStudents;
    }

    public LocalDate getNextSpringSemesterStart()
    {
        return firstday_nextSpringSemester;
    }

    public void setNextSpringSemesterStart(LocalDate firstday_nextSpringSemester)
    {
        this.firstday_nextSpringSemester = firstday_nextSpringSemester;
    }

    public LocalDate getNextAutumnSemesterStart()
    {
        return firstday_nextautumnSemester;
    }

    public void setNextAutumnSemesterStart(LocalDate firstday_nextautumnSemester)
    {
        this.firstday_nextautumnSemester = firstday_nextautumnSemester;
    }

    public double getAnnualTuitionFees()
    {
        return tuition_fees;
    }

    public void setAnnualTuitionFees(double tuition_fees)
    {
        this.tuition_fees = tuition_fees;
    }

    public int getUniversityRank()
    {
        return rank;
    }

    public void setUniversityRank(int rank)
    {
        this.rank = rank;
    }

    public Link getSelfLink()
    {
        return selfLink;
    }

    public void setSelfLink(Link selfLink)
    {
        this.selfLink = selfLink;
    }

    public Link getModules()
    {
        return modules;
    }

    public void setModules(Link modules)
    {
        this.modules = modules;
    }

    public static void validateUniversity(Uni university) throws SuttonWebAppException
    {
        if (university.getStudentsIncoming() < 0 ) {
            throw new SuttonWebAppException(Status.BAD_REQUEST, "There can't be less than 0 students incoming");
        }

        if (university.getStudentsOutgoing() < 0 ) {
            throw new SuttonWebAppException(Status.BAD_REQUEST, "There can't be less than 0 students outgoing");
        }

        if(university.getUniversityRank() < 0) {
            throw new SuttonWebAppException(Status.BAD_REQUEST, "Ranking can't be negative");
        }
    }

    @Override
    public String toString()
    {
        return "University{" +
                "universityName='" + universityName + '\'' +
                ", uni_location='" + uni_location + '\'' +
                ", department_name='" + department_name + '\'' +
                ", departmentURL='" + departmentURL + '\'' +
                ", contactperson='" + contactperson + '\'' +
                ", outgoingStudents=" + outgoingStudents +
                ", incomingStudents=" + incomingStudents +
                ", firstday_nextSpringSemester=" + firstday_nextSpringSemester +
                ", firstday_nextautumnSemester=" + firstday_nextautumnSemester +
                ", tuition_fees=" + tuition_fees +
                ", rank=" + rank +
                '}';
    }
}
