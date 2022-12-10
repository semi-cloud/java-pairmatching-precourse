package pairmatching.controller;

import pairmatching.controller.controllable.ControllableV3;
import pairmatching.service.PairMatchingService;

import java.util.Map;

public class PairInitControllable implements ControllableV3 {
    private final PairMatchingService pairMatchingService;

    public PairInitControllable(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public String process(Map<String, Object> modelMap) {
        pairMatchingService.initPairMatchResult();
        return "init";
    }
}
