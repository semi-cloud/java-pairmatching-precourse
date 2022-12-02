package pairmatching.domain;

import pairmatching.exception.ExceptionMessage;

import java.util.Arrays;

public enum Function {
    PAIR_MATCH("1", "페어 매칭"),
    PAIR_SEARCH("2", "페어 조회"),
    PARI_INIT("3", "페어 초기화"),
    QUIT("Q", "종료");

    private final String function;
    private final String name;

    Function(final String function, final String name) {
        this.function = function;
        this.name = name;
    }

    public static Function of(String function) {
        return Arrays.stream(Function.values())
                .filter(x -> x.function.equals(function))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_LEVEL_OR_MISSION.get()));
    }

    public String getFunction() {
        return function;
    }

    public String getName() {
        return name;
    }
}
