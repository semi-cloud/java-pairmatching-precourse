package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"4", "100", "A", "$", "11", "12"})
    void validateForm(String input) {
        Assertions.assertThatThrownBy(() -> new Command(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 명령어는 주어진 1,2,3,Q 중 하나만 입력 가능합니다.");
    }
}
