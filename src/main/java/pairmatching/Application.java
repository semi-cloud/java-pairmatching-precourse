package pairmatching;

import pairmatching.controller.PairController;
import pairmatching.domain.MatchResult;
import pairmatching.service.PairMatchingService;

public class Application {
    public static void main(String[] args) {
        PairMatchingService service = new PairMatchingService(new MatchResult());
        PairController pairController = new PairController(service);
        pairController.run();
    }
}
