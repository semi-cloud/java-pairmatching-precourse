package pairmatching.domain;

import java.util.Objects;

public class Crew {
    private final Course course;

    private final String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Crew)) {
            return false;
        }
        Crew crew = (Crew) obj;
        return Objects.equals(this.course, crew.course)
                && Objects.equals(this.name, crew.name);
    }

    public String getName() {
        return name;
    }
}
