package pairmatching.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

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
                .orElseThrow(NoSuchElementException::new);
    }

    public String getFunction() {
        return function;
    }

    public String getName() {
        return name;
    }
}
