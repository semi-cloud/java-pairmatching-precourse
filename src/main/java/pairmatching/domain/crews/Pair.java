package pairmatching.domain.crews;

import pairmatching.domain.match.Level;
import pairmatching.domain.match.MatchInfo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {

    private final MatchInfo matchInfo;

    private final List<Crew> pairCrews;

    public Pair(MatchInfo matchInfo, List<Crew> pairCrews) {
        this.matchInfo = matchInfo;
        this.pairCrews = pairCrews;
    }

    public boolean isSameLevel(Level level, List<Crew> pairs) {
        return matchInfo.getLevel().equals(level) && pairCrews.equals(pairs);
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(pairCrews);
    }

    public List<String> getCrewNames() {
        return pairCrews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    public MatchInfo getMatchInfo() {
        return matchInfo;
    }
}
