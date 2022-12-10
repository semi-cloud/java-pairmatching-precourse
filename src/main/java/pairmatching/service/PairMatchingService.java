package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.crews.Crew;
import pairmatching.domain.crews.Pair;
import pairmatching.domain.game.Mode;
import pairmatching.domain.match.MatchInfo;
import pairmatching.domain.match.MatchResult;
import pairmatching.exception.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PairMatchingService {

    private final MatchResult matchResult = new MatchResult();

    public boolean isMatchResultExist(MatchInfo matchInfo) {
        return matchResult.hasExistingMatchResult(matchInfo);
    }

    public void matchPair(List<String> crews, MatchInfo matchInfo) {
        for (int i = 0; i < 3 ; i ++) {
            List<Pair> pairs = getRandomMatchCrew(crews, matchInfo);
            if (!canMatchPair(pairs, matchInfo)) {
                matchResult.addResult(pairs, matchInfo);
                return;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.PAIR_MATCHING_FAIL.get());
    }

    public List<Pair> getRandomMatchCrew(List<String> crews, MatchInfo matchInfo) {
        List<String> shuffledName = Randoms.shuffle(crews);
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < shuffledName.size(); i += 2) {
            List<String> pair = shuffledName.subList(i, i + 2);
            pairs.add(convertToPair(matchInfo, pair));

            if (shuffledName.size() % 2 != 0 && i == shuffledName.size() - 3) {
                List<String> oddPair = shuffledName.subList(i, shuffledName.size());
                pairs.add(convertToPair(matchInfo, oddPair));
                break;
            }
        }
        return pairs;
    }

    private Pair convertToPair(MatchInfo matchInfo, List<String> crews) {
        return new Pair(matchInfo, crews.stream()
                .map(name -> new Crew(matchInfo.getCourse(), name))
                .collect(Collectors.toList()));
    }

    public boolean canMatchPair(List<Pair> randomPairs, MatchInfo matchInfo) {
        return randomPairs.stream()
                .anyMatch(x -> matchResult.isAlreadyMatchedSameLevel(matchInfo.getLevel(), x.getCrews()));
    }

    public List<List<String>> getMatchResult(MatchInfo matchInfo, Mode mode) {
        if (!isMatchResultExist(matchInfo) && mode == Mode.PAIR_SEARCH) {
            throw new IllegalArgumentException(ExceptionMessage.NO_MATCHING_RESULT.get());
        }

        List<Pair> pairResult = matchResult.getResultByMatchInfo(matchInfo);
        return pairResult.stream()
                .map(Pair::getCrewNames)
                .collect(Collectors.toList());
    }

    public void initPairMatchResult() {
        matchResult.init();
    }
}
