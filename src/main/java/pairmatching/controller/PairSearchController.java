package pairmatching.controller;

import pairmatching.domain.MatchInfo;
import pairmatching.domain.MatchResult;
import pairmatching.domain.Mode;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;
import pairmatching.view.MatchingInfoOutputView;
import pairmatching.view.MatchingResultOutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairSearchController implements ControllerV1 {

    private final InputView inputView = new InputView();
    private final MatchingInfoOutputView matchingInfoOutputView = new MatchingInfoOutputView();
    private final MatchingResultOutputView matchingResultOutputView = new MatchingResultOutputView();
    private final PairMatchingService pairMatchingService;

    public PairSearchController (PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void process() {
        try {
            matchingInfoOutputView.render(new HashMap<>());
            MatchInfo matchInfo = InputUtils.read(MatchInfo::create, inputView::getCourseAndMission);
            List<List<String>> matchResult = pairMatchingService.getMatchResult(matchInfo, Mode.PAIR_SEARCH);
            printView(matchResult);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            process();
        }
    }

    private void printView(List<List<String>> matchResult) {
        Map<String, Object> model = new HashMap<>();
        model.put("result", matchResult);
        matchingResultOutputView.render(model);
    }
}
