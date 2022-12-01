package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.domain.CourseInfo;
import pairmatching.view.InputView;

import java.util.List;

public class PairController {
    private final InputView inputView = new InputView();

    public void run() {
        Command command = inputCommand();
        CourseInfo courseInfo = inputCourse();
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
}
