package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.view.InputView;

public class PairController {

    private final InputView inputView = new InputView();

    public void run() {
        // 1. 기능 선택
        Command command = inputCommand();
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
}
