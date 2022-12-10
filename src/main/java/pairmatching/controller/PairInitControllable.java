package pairmatching.controller;

import pairmatching.service.PairMatchingService;

public class PairInitControllable implements ControllableV2 {
    private final PairMatchingService pairMatchingService;

    public PairInitControllable(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public ModelAndView process() {
        pairMatchingService.initPairMatchResult();
        return new ModelAndView("init");
    }
}
