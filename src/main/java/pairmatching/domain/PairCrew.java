package pairmatching.domain;

import java.util.List;

public class PairCrew {

    private final List<Crew> pairCrews;

    public PairCrew(List<Crew> pairCrews) {
        this.pairCrews = pairCrews;
    }

    // 이미 두 크루가 같은 레벨에서 매칭이 되었는지 반환?
}
