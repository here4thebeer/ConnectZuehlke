package ch.zuehlke.fullstack.ConnectZuehlke.domain;

public class Project {

    private String title;
    private Location location;

    public Project(String title, Location location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public Location getLocation() {
        return location;
    }

}