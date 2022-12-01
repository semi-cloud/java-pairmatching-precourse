package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class CourseInfo {
    private final Course course;
    private final Level level;

    private CourseInfo(Course course, Level level) {
        this.course = course;
        this.level = level;
    }

    public static CourseInfo create(List<String> courseInfo) {
        Course course = Course.of(courseInfo.get(0));
        Level level = Level.of(courseInfo.get(1), courseInfo.get(2));
        return new CourseInfo(course, level);
    }

    public boolean isBackEndCourse() {
        return course == Course.BACKEND;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CourseInfo)) {
            return false;
        }
        CourseInfo course = (CourseInfo) obj;
        return Objects.equals(this.course, course.course)
                && Objects.equals(this.level, course.level);
    }
}
