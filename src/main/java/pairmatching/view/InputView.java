package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Function;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_COMMAND = "기능을 선택하세요.";
    private static final String COURSE = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";
    private static final String FUNCTION_FORM = "%s. %s%n";
    private static final String SPLIT_FORM = ",";

    public String getCommand() {
        System.out.println(INPUT_COMMAND);
        printFunctions();
        return Console.readLine();
    }

    private void printFunctions() {
        Arrays.stream(Function.values())
                .forEach(x -> System.out.printf(FUNCTION_FORM, x.getFunction(), x.getName()));
    }

    public List<String> getCourseAndMission() {
        System.out.println(COURSE);
        String input = Console.readLine();

        return Arrays.stream(input.split(SPLIT_FORM))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
