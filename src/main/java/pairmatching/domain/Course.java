package pairmatching.domain;

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

    public static List<String> getCourse() {
        return Arrays.stream(Course.values())
                .map(x -> x.name)
                .collect(Collectors.toList());
    }
}
