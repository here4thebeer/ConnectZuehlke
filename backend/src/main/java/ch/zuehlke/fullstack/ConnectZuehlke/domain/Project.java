package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.io.Serializable;
import java.util.StringJoiner;

public class Project implements Serializable {

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

    @Override
    public String toString() {
        return new StringJoiner(", ", Project.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("location=" + location)
                .toString();
    }
}