package pairmatching.controller;

import pairmatching.domain.MatchInfo;
import pairmatching.domain.Mode;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.FileUtils;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;
import pairmatching.view.MatchingInfoOutputView;
import pairmatching.view.MatchingResultOutputView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairMatchingController implements ControllerV1 {
    private final InputView inputView = new InputView();
    private final MatchingInfoOutputView matchingInfoOutputView = new MatchingInfoOutputView();
    private final MatchingResultOutputView matchingResultOutputView = new MatchingResultOutputView();
    private final PairMatchingService pairMatchingService;
    private static final String STOP_MATCH = "아니오";

    public PairMatchingController(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void process() {
        try {
            matchingInfoOutputView.render(new HashMap<>());
            MatchInfo matchInfo = getMatchInfo();
            List<String> crews = getCrewsByCourse(matchInfo);
            pairMatchingService.matchPair(crews, matchInfo);
            List<List<String>> matchResult = pairMatchingService.getMatchResult(matchInfo, Mode.PAIR_MATCH);
            printView(matchResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    private List<String> getCrewsByCourse(MatchInfo matchInfo) throws IOException {
        String fileName = matchInfo.getCourse().getFileName();
        return FileUtils.readFile(fileName);
    }

    private void printView(List<List<String>> matchResult) {
        Map<String, Object> model = new HashMap<>();
        model.put("result", matchResult);
        matchingResultOutputView.render(model);
    }
}
