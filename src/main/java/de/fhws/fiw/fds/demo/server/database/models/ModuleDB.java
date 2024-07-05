package de.fhws.fiw.fds.demo.server.database.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "modules")
public class ModuleDB extends AbstractDBModel {

    @Column(name = "name_of_module")
    private String name_of_module;

    @Column(name = "sems_offered")
    private int sems_offered;

    @Column(name = "credits")
    private int credits;

    public ModuleDB() {
    }

    public String getModuleName() {
        return name_of_module;
    }

    public void setModuleName(String name_of_module) {
        this.name_of_module = name_of_module;
    }

    public int getSemesters() {
        return sems_offered;
    }

    public void setSemesters(int sems_offered) {
        this.sems_offered = sems_offered;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
