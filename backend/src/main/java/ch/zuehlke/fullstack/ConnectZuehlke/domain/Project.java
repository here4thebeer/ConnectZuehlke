package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Location location;
    private String zuehlkeCompany;
    private String projectCode;
    private String industry;
    private List<String> skills;
    private String pictureURL;
    private String logoURL;
    private String projectURL;
    private int amountOfEmployees;
    private boolean isDistributed;
    private String projectDescription;

    public Project(String title, Location location, String zuehlkeCompany, String projectCode, String industry, List<String> skills, String pictureURL, String logoURL, String projectURL, int amountOfEmployees, boolean isDistributed, String projectDescription) {
        this.title = title;
        this.location = location;
        this.zuehlkeCompany = zuehlkeCompany;
        this.projectCode = projectCode;
        this.industry = industry;
        this.skills = skills;
        this.pictureURL = pictureURL;
        this.logoURL = logoURL;
        this.projectURL = projectURL;
        this.amountOfEmployees = amountOfEmployees;
        this.isDistributed = isDistributed;
        this.projectDescription = projectDescription;
    }

    public Project() {
        // jpa constructor
    }

    public Project(String title, Location location) {
        this.title = title;
        this.location = location;
        this.skills = Collections.emptyList();
    }

    public String getTitle() {
        return title;
    }

    public Location getLocation() {
        return location;
    }

    public String getZuehlkeCompany() {
        return zuehlkeCompany;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getIndustry() {
        return industry;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public String getProjectURL() {
        return projectURL;
    }

    public int getAmountOfEmployees() {
        return amountOfEmployees;
    }

    public boolean isDistributed() {
        return isDistributed;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", location=" + location +
                ", zuehlkeCompany='" + zuehlkeCompany + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", industry='" + industry + '\'' +
                ", skills=" + skills +
                ", pictureURL='" + pictureURL + '\'' +
                ", logoURL='" + logoURL + '\'' +
                ", projectURL='" + projectURL + '\'' +
                ", amountOfEmployees=" + amountOfEmployees +
                ", isDistributed=" + isDistributed +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }

}