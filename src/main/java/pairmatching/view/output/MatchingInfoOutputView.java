package pairmatching.view.output;

import pairmatching.domain.match.Course;
import pairmatching.domain.match.Level;
import pairmatching.view.Viewable;

import java.util.List;
import java.util.Map;

public class MatchingInfoOutputView implements Viewable {

    private static final String LINE = "#############################################";
    private static final String SEPARATE = " | ";
    private static final String COURSE = "과정: %s";
    private static final String MISSION = "미션:";
    private static final String LEVEL = "  - %s: %s";

    @Override
    public void render(Map<String, Object> model) {
        System.out.println("\n" + LINE);
        printCourse();
        printMatchingInfo();
        System.out.println(LINE);
    }

    private void printCourse() {
        String course = String.format(COURSE, String.join(SEPARATE, Course.getCourseName()));
        System.out.println(course);
    }

    private void printMatchingInfo() {
        System.out.println(MISSION);
        Map<String, List<String>> missionByLevel = Level.getMissionByLevel();
        missionByLevel.keySet()
                .forEach(key -> System.out.printf(
                        (LEVEL) + "%n", key, String.join(SEPARATE, missionByLevel.get(key))));
    }
}
