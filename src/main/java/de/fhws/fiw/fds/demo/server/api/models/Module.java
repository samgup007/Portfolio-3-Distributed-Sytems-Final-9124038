package de.fhws.fiw.fds.demo.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SecondarySelfLink;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SelfLink;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.Status;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonRootName("module")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "module")
public class Module extends AbstractModel {

    private String name_of_module;
    private int sems_offered; 
    private int credits; 

    @SecondarySelfLink(
            primaryPathElement = "universities",
            secondaryPathElement = "modules"
    )
    private transient Link selfLinkOnSecond;

    @SelfLink(pathElement = "modules")
    private transient Link selfLink;

    public Module() {
    }

    public Module(String name_of_module, int sems_offered, int credits) {
        this.name_of_module = name_of_module;
        this.sems_offered = sems_offered;
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    public void setModuleName(String name_of_module) {
        this.name_of_module = name_of_module;
    }

    public void setSemesters(int sems_offered) {
        this.sems_offered = sems_offered;
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


    public static void validateModule(Module module) throws SuttonWebAppException {
        if (module.getSemesters() != 1 && module.getSemesters() != 2) {
            throw new SuttonWebAppException(Status.BAD_REQUEST, "Invalid sems_offered. It must be 1 for spring or 2 for autumn.");
        }

        if (module.getCredits() <= 0) {
            throw new SuttonWebAppException(Status.BAD_REQUEST, "Invalid credits. It must be greater than 0.");
        }
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name_of_module='" + name_of_module + '\'' +
                ", sems_offered=" + sems_offered +
                ", credits=" + credits +
                '}';
    }
}
