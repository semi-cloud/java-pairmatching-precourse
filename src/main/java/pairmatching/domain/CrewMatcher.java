package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrewMatcher {
    private final List<String> crews;

    public CrewMatcher(List<String> crews) {
        this.crews = crews;
    }

    public List<Pair> getRandomMatchCrew(MatchInfo matchInfo) {
        List<String> shuffledName = Randoms.shuffle(crews);
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < shuffledName.size(); i += 2) {
            List<String> pair = shuffledName.subList(i, i + 2);
            pairs.add(convertToPair(matchInfo, pair));

            if (shuffledName.size() % 2 != 0 && i == shuffledName.size() - 3) {
                List<String> oddPair = shuffledName.subList(i, shuffledName.size());
                pairs.add(convertToPair(matchInfo, oddPair));
            }
        }
        return pairs;
    }

    private Pair convertToPair(MatchInfo matchInfo, List<String> crews) {
        return new Pair(matchInfo, crews.stream()
                .map(name -> new Crew(matchInfo.getCourse(), name))
                .collect(Collectors.toList()));
    }
}
