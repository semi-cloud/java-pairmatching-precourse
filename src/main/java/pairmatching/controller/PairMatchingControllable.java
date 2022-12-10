package pairmatching.controller;

import pairmatching.controller.controllable.ControllableV3;
import pairmatching.domain.match.MatchInfo;
import pairmatching.domain.game.Mode;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.FileUtils;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;
import pairmatching.view.output.MatchingInfoOutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairMatchingControllable implements ControllableV3 {
    private final InputView inputView = new InputView();
    private final MatchingInfoOutputView matchingInfoOutputView = new MatchingInfoOutputView();
    private final PairMatchingService pairMatchingService;
    private static final String STOP_MATCH = "아니오";

    public PairMatchingControllable(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public String process(Map<String, Object> modelMap) {
        matchingInfoOutputView.render(new HashMap<>());
        MatchInfo matchInfo = getMatchInfo();
        List<String> crews = getCrewsByCourse(matchInfo);
        pairMatchingService.matchPair(crews, matchInfo);
        List<List<String>> matchResult = pairMatchingService.getMatchResult(matchInfo, Mode.PAIR_MATCH);
        modelMap.put("matchResult", matchResult);
        return "result";
    }

    private MatchInfo getMatchInfo() {
        while (true) {
            MatchInfo matchInfo = InputUtils.read(MatchInfo::create, inputView::getCourseAndMission);
            if (continueMatch(matchInfo)) {
                return matchInfo;
            }
        }
    }

    private boolean continueMatch(MatchInfo matchInfo) {
        if (pairMatchingService.isMatchResultExist(matchInfo)) {
            return !InputUtils.read(inputView::getRematch).equals(STOP_MATCH);
        }
        return true;
    }

    private List<String> getCrewsByCourse(MatchInfo matchInfo) {
        String fileName = matchInfo.getCourse().getFileName();
        return FileUtils.readFile(fileName);
    }
}
