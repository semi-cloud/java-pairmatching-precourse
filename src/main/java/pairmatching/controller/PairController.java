package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.domain.MatchInfo;
import pairmatching.domain.Mode;
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
    private static final String STOP_MATCH = "아니오";

    public PairController(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    public void run() {
        try {
            while(true) {
                Command command = inputCommand();
                runByCommand(command);

                if (command.getMode() == Mode.QUIT) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void runByCommand(Command command) throws IOException {
        if (command.getMode() == Mode.PAIR_MATCH) {
            pairMatch();
        } else if (command.getMode() == Mode.PAIR_SEARCH) {
            pairSearch();
        } else {
            pairInit();
        }
    }

    private void pairMatch() throws IOException {
        outputView.printCourseAndMatchingInfo();
        MatchInfo matchInfo = getMatchInfo();
        List<String> crews = getCrewsByCourse(matchInfo);

        pairMatchingService.matchPair(crews, matchInfo);
        List<List<String>> matchResult = pairMatchingService.getMatchResult(matchInfo, Mode.PAIR_MATCH);
        outputView.printMatchingResult(matchResult);
    }

    private MatchInfo getMatchInfo() {
        while (true) {
            MatchInfo matchInfo = inputCourse();
            if (continueMatch(matchInfo)) {
                return matchInfo;
            }
        }
    }

    private boolean continueMatch(MatchInfo matchInfo) {
        if (pairMatchingService.isMatchResultExist(matchInfo)) {
            return !inputRematch().equals(STOP_MATCH);
        }
        return true;
    }

    public void pairSearch() {
        try {
            outputView.printCourseAndMatchingInfo();
            MatchInfo matchInfo = inputCourse();
            List<List<String>> matchResult = pairMatchingService.getMatchResult(matchInfo, Mode.PAIR_SEARCH);
            outputView.printMatchingResult(matchResult);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            pairSearch();
        }
    }

    public void pairInit() {
        pairMatchingService.initPairMatchResult();
        outputView.printInitSuccess();
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

    private String inputRematch() {
        try {
            return inputView.getRematch();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return inputRematch();
        }
    }

    private List<String> getCrewsByCourse(MatchInfo matchInfo) throws IOException {
        String fileName = matchInfo.getCourse().getFileName();
        return FileUtils.readFile(fileName);
    }
}
