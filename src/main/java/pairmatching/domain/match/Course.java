package pairmatching.domain.match;

import pairmatching.exception.ExceptionMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드", "backend-crew.md"),
    FRONTEND("프론트엔드", "frontend-crew.md");

    private final String name;
    private final String fileName;

    Course(final String name, final String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public static Course of(String name) {
        return Arrays.stream(Course.values())
                .filter(x -> x.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_COURSE.get()));
    }

    public static List<String> getCourseName() {
        return Arrays.stream(Course.values())
                .map(x -> x.name)
                .collect(Collectors.toList());
    }

    public String getFileName() {
        return fileName;
    }
}
