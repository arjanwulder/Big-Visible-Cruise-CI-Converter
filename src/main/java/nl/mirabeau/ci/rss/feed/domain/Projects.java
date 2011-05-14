package nl.mirabeau.ci.rss.feed.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Projects")
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "Project")
    private List<Project> projectList;

    public Projects(List<Project> projectList) {
        this.projectList = projectList;
    }


    public Projects() {
    }


    public List<Project> getProjectList() {
        return projectList;
    }


    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

}
