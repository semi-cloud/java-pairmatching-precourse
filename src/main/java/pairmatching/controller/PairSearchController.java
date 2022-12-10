package pairmatching.controller;

import pairmatching.domain.MatchInfo;
import pairmatching.domain.Mode;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class PairSearchController implements ControllerV1 {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PairMatchingService pairMatchingService;

    public PairSearchController (PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void process() {
        try {
            outputView.printCourseAndMatchingInfo();
            MatchInfo matchInfo = InputUtils.read(MatchInfo::create, inputView::getCourseAndMission);
            List<List<String>> matchResult = pairMatchingService.getMatchResult(matchInfo, Mode.PAIR_SEARCH);
            outputView.printMatchingResult(matchResult);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            process();
        }
    }
}
