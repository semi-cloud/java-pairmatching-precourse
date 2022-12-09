package pairmatching.domain;

import java.util.*;
import java.util.stream.Collectors;

public class MatchResult {
    private List<Pair> pairResult = new ArrayList<>();

    public void addResult(List<Pair> pairs, MatchInfo matchInfo) {
        if (!pairResult.isEmpty()) {
            pairResult = pairResult.stream()
                    .filter(x -> !x.getMatchInfo().equals(matchInfo))
                    .collect(Collectors.toList());
        }
        pairResult.addAll(pairs);
    }

    public boolean isAlreadyMatchedSameLevel(Level level, List<Crew> pair) {
        return pairResult.stream()
                .anyMatch(x -> x.isSameLevel(level, pair));
    }

    public boolean hasExistingMatchResult(MatchInfo matchInfo) {
        return pairResult.stream()
                .anyMatch(x -> x.getMatchInfo().equals(matchInfo));
    }

    public List<Pair> getResultByMatchInfo(MatchInfo matchInfo) {
        return pairResult.stream()
                .filter(x -> x.getMatchInfo().equals(matchInfo))
                .collect(Collectors.toList());
    }

    public void init() {
        pairResult = new ArrayList<>();
    }
}
