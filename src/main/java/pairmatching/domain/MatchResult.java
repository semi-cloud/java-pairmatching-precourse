package pairmatching.domain;

import java.util.*;
import java.util.stream.Collectors;

public class MatchResult {
    private final List<Pair> pairResult = new ArrayList<>();

    public void addResult(List<Pair> pairs, MatchInfo matchInfo) {
        if (!pairResult.isEmpty()) {
            getResultByMatchInfo(matchInfo).forEach(pairResult::remove);
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
}
