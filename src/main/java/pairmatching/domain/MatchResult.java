package pairmatching.domain;

import java.util.*;

public class MatchResult {
    private final Set<Pair> pairResult = new HashSet<>();

    public void addResult(List<Pair> pairs) {
        pairResult.addAll(pairs);
    }

    public boolean isAlreadyMatchedSameLevel(Level level, List<Crew> pair) {
        return pairResult.stream()
                .anyMatch(x -> x.isSameLevel(level, pair));
    }
}
