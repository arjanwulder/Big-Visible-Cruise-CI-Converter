package nl.mirabeau.ci.rss.feed.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "lastBuildStatus")
    private String lastBuildStatus;
    @XmlAttribute(name = "category")
    private String category;
    @XmlAttribute(name = "activity")
    private String activity;
    @XmlAttribute(name = "lastBuildLabel")
    private String lastBuildLabel;
    @XmlAttribute(name = "lastBuildTime")
    private Date lastBuildTime;
    @XmlAttribute(name = "nextBuildTime")
    private Date nextBuildTime;
    @XmlAttribute(name = "webUrl")
    private String webUrl;
    @XmlAttribute(name = "CurrentMessage")
    private String currentMessage;
    @XmlAttribute(name = "BuildStage")
    private String buildStage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastBuildStatus() {
        return lastBuildStatus;
    }

    public void setLastBuildStatus(String lastBuildStatus) {
        this.lastBuildStatus = lastBuildStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getLastBuildLabel() {
        return lastBuildLabel;
    }

    public void setLastBuildLabel(String lastBuildLabel) {
        this.lastBuildLabel = lastBuildLabel;
    }

    public Date getLastBuildTime() {
        return lastBuildTime;
    }

    public void setLastBuildTime(Date lastBuildTime) {
        this.lastBuildTime = lastBuildTime;
    }

    public Date getNextBuildTime() {
        return nextBuildTime;
    }

    public void setNextBuildTime(Date nextBuildTime) {
        this.nextBuildTime = nextBuildTime;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String getBuildStage() {
        return buildStage;
    }

    public void setBuildStage(String buildStage) {
        this.buildStage = buildStage;
    }
}
