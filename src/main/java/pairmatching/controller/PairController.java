package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.exception.ExceptionMessage;
import pairmatching.utils.FileUtils;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.io.IOException;
import java.util.List;

public class PairController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        try {
            Command command = inputCommand();
            outputView.printCourseAndMissionInfo();
            MatchInfo matchInfo = inputCourse();
            CrewMatcher crewMatcher = getCrewsByCourse(matchInfo);
            matchCrew(crewMatcher, matchInfo);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void matchCrew(CrewMatcher crewMatcher, MatchInfo matchInfo) {
        MatchResult matchResult = new MatchResult();
        for (int i = 0; i < 3 ; i ++) {
            List<Pair> pairs = crewMatcher.getRandomMatchCrew(matchInfo);
            if (canMatchPair(pairs, matchResult, matchInfo)) {
                matchResult.addResult(pairs);
                return;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.PAIR_MATCHING_FAIL.get());
    }

    private boolean canMatchPair(List<Pair> randomPairs, MatchResult matchResult, MatchInfo matchInfo) {
        return randomPairs.stream()
                .anyMatch(x -> matchResult.isAlreadyMatchedSameLevel(matchInfo.getLevel(), x.getCrews()));
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

    private CrewMatcher getCrewsByCourse(MatchInfo matchInfo) throws IOException {
        if (matchInfo.isBackEndCourse()) {
            return new CrewMatcher(FileUtils.readFile("backend-crew.md"));
        }
        return new CrewMatcher(FileUtils.readFile("frontend-crew.md"));
    }
}
