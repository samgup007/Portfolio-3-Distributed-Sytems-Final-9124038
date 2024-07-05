package de.fhws.fiw.fds.demo.client.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientModule extends AbstractClientModel {

    private String name_of_module;
    private int sems_offered;
    private int credits; 

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLinkOnSecond;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLink;

    public ClientModule() {
    }

    public ClientModule(String name_of_module, int sems_offered, int credits) {
        this.name_of_module = name_of_module;
        this.sems_offered = sems_offered;
        this.credits = credits;
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

    public String getModuleName() {
        return name_of_module;
    }


    public int getSemesters() {
        return sems_offered;
    }

    public void setModuleName(String name_of_module) {
        this.name_of_module = name_of_module;
    }

    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }

    @JsonIgnore
    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }
}
