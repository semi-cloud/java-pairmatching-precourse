package pairmatching.domain;

import pairmatching.exception.ExceptionMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(final String name) {
        this.name = name;
    }

    public static Course of(String name) {
        return Arrays.stream(Course.values())
                .filter(x -> x.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_COURSE.get()));
    }

    public static List<String> getCourse() {
        return Arrays.stream(Course.values())
                .map(x -> x.name)
                .collect(Collectors.toList());
    }
}
