package pairmatching.domain;

import java.util.List;

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
}
