package pairmatching.exception;

public enum ExceptionMessage {

    COMMAND_FORM("명령어는 주어진 1,2,3,Q 중 하나만 입력 가능합니다."),
    INVALID_COURSE("주어진 과정명만 입력 가능합니다."),
    INVALID_LEVEL_OR_MISSION("주어진 레벨과 미션명만 입력 가능합니다."),
    INVALID_RE_MATCH_COMMAND("네 혹은 아니오로만 입력 가능합니다."),
    PAIR_MATCHING_FAIL("페어 매칭에 실패하였습니다.");

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String get() {
        return String.format("[ERROR] %s", message);
    }
}
