package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Function;
import pairmatching.exception.ExceptionMessage;

import java.util.Arrays;

public class InputView {

    private static final String INPUT_COMMAND = "기능을 선택하세요.";
    private static final String FUNCTION_FORM = "%s. %s%n";
    private static final int COMMAND_SIZE = 1;

    public String getCommand() {
        System.out.println(INPUT_COMMAND);
        printFunctions();
        String command = Console.readLine();
        validateCommandSize(command);
        return command;
    }

    private void validateCommandSize(String command) {
        if (command.length() != COMMAND_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.COMMAND_SIZE.get());
        }
    }

    private void printFunctions() {
        Arrays.stream(Function.values())
                .forEach(x -> System.out.printf(FUNCTION_FORM, x.getFunction(), x.getName()));
    }
}
