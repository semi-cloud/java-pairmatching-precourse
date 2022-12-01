package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchResult {
    private final Map<CourseInfo, List<PairCrew>> result = new HashMap<>();

    public void updateResult(CourseInfo courseInfo, List<PairCrew> pair) {
        result.put(courseInfo, pair);
    }

    public boolean canMatchPair(CourseInfo courseInfo, PairCrew pair) {
        if (result.get(courseInfo) == null) {
            return true;
        }
        return !result.get(courseInfo).contains(pair);
    }
}
