package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Location location;

    public Project() {
        // jpa constructor
    }

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