package pairmatching.domain.game;

import pairmatching.domain.game.Mode;
import pairmatching.exception.ExceptionMessage;

public class Command {
    private static final String REG_XP_COMMAND = "[123Q]";

    private final Mode mode;

    public Command(String command) {
        validateCommand(command);
        this.mode = Mode.of(command);
    }

    private void validateCommand(String command) {
        if (!command.matches(REG_XP_COMMAND)) {
            throw new IllegalArgumentException(ExceptionMessage.COMMAND_FORM.get());
        }
    }

    public Mode getMode() {
        return mode;
    }
}
