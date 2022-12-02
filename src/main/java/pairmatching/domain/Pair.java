package pairmatching.domain;

import java.util.Collections;
import java.util.List;

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
}
