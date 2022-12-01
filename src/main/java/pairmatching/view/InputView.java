package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Function;
import pairmatching.exception.ExceptionMessage;

import java.util.Arrays;

public class InputView {

    private static final String INPUT_COMMAND = "기능을 선택하세요.";
    private static final String FUNCTION_FORM = "%s. %s%n";

    public String getCommand() {
        System.out.println(INPUT_COMMAND);
        printFunctions();
        return Console.readLine();
    }

    private void printFunctions() {
        Arrays.stream(Function.values())
                .forEach(x -> System.out.printf(FUNCTION_FORM, x.getFunction(), x.getName()));
    }
}
