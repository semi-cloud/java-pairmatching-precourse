package pairmatching.domain;

import pairmatching.exception.ExceptionMessage;

public class Command {

    private final static String REG_XP_COMMAND = "[123Q]";

    private final Function command;

    public Command(String command) {
        validateCommand(command);
        this.command = Function.of(command);
    }

    private void validateCommand(String command) {
        if (!command.matches(REG_XP_COMMAND)) {
            throw new IllegalArgumentException(ExceptionMessage.COMMAND_FORM.get());
        }
    }
}
