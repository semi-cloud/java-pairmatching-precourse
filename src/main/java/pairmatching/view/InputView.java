package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Mode;
import pairmatching.exception.ExceptionMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_COMMAND = "기능을 선택하세요.";
    private static final String COURSE = "과정, 레벨, 미션을 선택하세요.\n" + "ex) 백엔드, 레벨1, 자동차경주";
    private static final String RE_MATCH = "\n매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" + "네 | 아니오";
    private static final String FUNCTION_FORM = "%s. %s%n";
    private static final String SPLIT_FORM = ",";
    private static final String CONTINUE_MATCH = "네";
    private static final String STOP_MATCH = "아니오";

    public String getCommand() {
        System.out.println(INPUT_COMMAND);
        printFunctions();
        return Console.readLine().trim();
    }

    private void printFunctions() {
        Arrays.stream(Mode.values())
                .forEach(x -> System.out.printf(FUNCTION_FORM, x.getFunction(), x.getName()));
    }

    public List<String> getCourseAndMission() {
        System.out.println(COURSE);
        String input = Console.readLine();

        return Arrays.stream(input.split(SPLIT_FORM))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String getRematch() {
        System.out.println(RE_MATCH);
        String input = Console.readLine().trim();
        validateRematch(input);
        return input;
    }

    private void validateRematch(String input) {
        if (!input.equals(CONTINUE_MATCH) && !input.equals(STOP_MATCH)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_RE_MATCH_COMMAND.get());
        }
    }
}
