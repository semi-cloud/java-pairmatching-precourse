package pairmatching.controller;

import pairmatching.controller.controllable.ControllableV3;
import pairmatching.domain.MatchInfo;
import pairmatching.domain.Mode;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;
import pairmatching.view.MatchingInfoOutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairSearchControllable implements ControllableV3 {

    private final InputView inputView = new InputView();
    private final MatchingInfoOutputView matchingInfoOutputView = new MatchingInfoOutputView();
    private final PairMatchingService pairMatchingService;

    public PairSearchControllable(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public String process(Map<String, Object> modelMap) {
        try {
            matchingInfoOutputView.render(new HashMap<>());
            MatchInfo matchInfo = InputUtils.read(MatchInfo::create, inputView::getCourseAndMission);
            List<List<String>> matchResult = pairMatchingService.getMatchResult(matchInfo, Mode.PAIR_SEARCH);
            modelMap.put("matchResult", matchResult);
            return "result";
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return process(modelMap);
        }
    }
}
