package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.domain.MatchInfo;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.FileUtils;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.io.IOException;
import java.util.List;

public class PairController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PairMatchingService pairMatchingService;

    public PairController(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    public void run() {
        try {
            Command command = inputCommand();
            outputView.printCourseAndMissionInfo();
            MatchInfo matchInfo = inputCourse();
            List<String> crews = getCrewsByCourse(matchInfo);
            pairMatchingService.matchPair(crews, matchInfo);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Command inputCommand() {
        try {
            String input = inputView.getCommand();
            return new Command(input);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return inputCommand();
        }
    }

    private MatchInfo inputCourse() {
        try {
            List<String> input = inputView.getCourseAndMission();
            return MatchInfo.create(input);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return inputCourse();
        }
    }

    private List<String> getCrewsByCourse(MatchInfo matchInfo) throws IOException {
        String fileName = matchInfo.getCourse().getFileName();
        return FileUtils.readFile(fileName);
    }
}
