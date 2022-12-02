package pairmatching.domain;

import java.util.*;
import java.util.stream.Collectors;

public class MatchResult {
    private final List<Pair> pairResult = new ArrayList<>();

    public void addResult(List<Pair> pairs) {
        pairResult.addAll(pairs);
    }

    public boolean isAlreadyMatchedSameLevel(Level level, List<Crew> pair) {
        return pairResult.stream()
                .anyMatch(x -> x.isSameLevel(level, pair));
    }

    public List<Pair> getResult(MatchInfo matchInfo) {
        return pairResult.stream()
                .filter(x -> x.getMatchInfo().equals(matchInfo))
                .collect(Collectors.toList());
    }
}
