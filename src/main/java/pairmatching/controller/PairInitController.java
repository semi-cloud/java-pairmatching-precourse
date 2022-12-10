package pairmatching.controller;

import pairmatching.service.PairMatchingService;
import pairmatching.view.MatchingInitOutputView;

import java.util.HashMap;

public class PairInitController implements ControllerV1 {
    private final MatchingInitOutputView outputView = new MatchingInitOutputView();
    private final PairMatchingService pairMatchingService;

    public PairInitController(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void process() {
        pairMatchingService.initPairMatchResult();
        outputView.render(new HashMap<>());
    }
}
