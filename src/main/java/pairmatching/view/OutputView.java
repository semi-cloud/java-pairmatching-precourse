package pairmatching.view;

import pairmatching.domain.Course;
import pairmatching.domain.Level;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String LINE = "#############################################";
    private static final String SEPARATE = " | ";
    private static final String COURSE = "과정: %s";
    private static final String MISSION = "미션:";
    private static final String LEVEL = "  - %s: %s";

    public void printCourseAndMissionInfo() {
        System.out.println(LINE);
        String course = String.format(COURSE, String.join(SEPARATE, Course.getCourseName()));
        System.out.println(course);

        System.out.println(MISSION);
        Map<String, List<String>> missionByLevel = Level.getMissionByLevel();
        missionByLevel.keySet()
                        .forEach(key -> System.out.printf(
                                (LEVEL) + "%n",
                                key,
                                String.join(SEPARATE, missionByLevel.get(key))));
        System.out.println(LINE);
    }
}
