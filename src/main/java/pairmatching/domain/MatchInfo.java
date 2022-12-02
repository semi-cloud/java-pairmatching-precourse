package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class MatchInfo {
    private final Course course;
    private final Level level;
    private final String mission;

    private MatchInfo(Course course, Level level, String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static MatchInfo create(List<String> courseInfo) {
        Course course = Course.of(courseInfo.get(0));
        Level level = Level.of(courseInfo.get(1), courseInfo.get(2));
        return new MatchInfo(course, level, courseInfo.get(2));
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MatchInfo)) {
            return false;
        }
        MatchInfo course = (MatchInfo) obj;
        return Objects.equals(this.course, course.course)
                && Objects.equals(this.level, course.level)
                && Objects.equals(this.mission, course.mission);
    }
}
