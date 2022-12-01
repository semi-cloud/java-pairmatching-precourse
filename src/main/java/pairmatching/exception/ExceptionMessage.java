package pairmatching.exception;

public enum ExceptionMessage {

    COMMAND_SIZE("명령어는 주어진 1,2,3,Q 만 입력 가능합니다."),
    COMMAND_FORM("명령어는 주어진 1,2,3,Q 중 하나만 입력 가능합니다.");

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String get() {
        return "[ERROR]" + message;
    }
}
