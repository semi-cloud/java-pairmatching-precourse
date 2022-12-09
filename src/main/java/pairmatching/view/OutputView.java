package pairmatching.view;

import pairmatching.domain.Course;
import pairmatching.domain.Level;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String MATCH_OUTPUT = "\n페어 매칭 결과입니다.";
    private static final String LINE = "#############################################";
    private static final String SEPARATE = " | ";
    private static final String COURSE = "과정: %s";
    private static final String MISSION = "미션:";
    private static final String LEVEL = "  - %s: %s";
    private static final String CREW_SEPARATE = " : ";

    public void printCourseAndMatchingInfo() {
        System.out.println("\n" + LINE);
        printCourse();
        printMatchingInfo();
        System.out.println(LINE);
    }

    public void printCourse() {
        String course = String.format(COURSE, String.join(SEPARATE, Course.getCourseName()));
        System.out.println(course);
    }

    public void printMatchingInfo() {
        System.out.println(MISSION);
        Map<String, List<String>> missionByLevel = Level.getMissionByLevel();
        missionByLevel.keySet()
                .forEach(key -> System.out.printf(
                        (LEVEL) + "%n", key, String.join(SEPARATE, missionByLevel.get(key))));
    }

    public void printMatchingResult(List<List<String>> matchResult) {
        System.out.println(MATCH_OUTPUT);
        matchResult.forEach(x ->
                System.out.println(String.join(CREW_SEPARATE, x)));
        System.out.println();
    }
}
