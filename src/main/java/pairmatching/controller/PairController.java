package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.utils.FileUtils;
import pairmatching.view.InputView;

import java.io.IOException;
import java.util.List;

public class PairController {
    private final InputView inputView = new InputView();

    public void run() {
        try {
            Command command = inputCommand();
            CourseInfo courseInfo = inputCourse();
            Crews crews = getCrewsByCourse(courseInfo);
            MatchResult matchResult = new MatchResult();

            List<PairCrew> pairCrews = crews.randomMatchCrew(courseInfo.getCourse());
            for (PairCrew pairCrew : pairCrews) {
                if (matchResult.canMatchPair(courseInfo, pairCrew)) {
                    matchResult.updateResult(courseInfo, pairCrews);
                }
            }
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

    private CourseInfo inputCourse() {
        try {
            List<String> input = inputView.getCourseAndMission();
            return CourseInfo.create(input);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return inputCourse();
        }
    }

    private Crews getCrewsByCourse(CourseInfo courseInfo) throws IOException {
        if (courseInfo.isBackEndCourse()) {
            return new Crews(FileUtils.readFile("backend-crew.md"));
        }
        return new Crews(FileUtils.readFile("frontend-crew.md"));
    }
}
