package pairmatching.controller;

import pairmatching.service.PairMatchingService;
import pairmatching.view.OutputView;

public class PairInitController implements ControllerV1 {
    private final OutputView outputView = new OutputView();
    private final PairMatchingService pairMatchingService;

    public PairInitController(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void process() {
        pairMatchingService.initPairMatchResult();
        outputView.printInitSuccess();
    }
}
