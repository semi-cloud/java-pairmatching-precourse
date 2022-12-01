package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Level {
    LEVEL1("레벨1", List.of("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", List.of("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", List.of()),
    LEVEL4("레벨4", List.of("성능개선", "배포")),
    LEVEL5("레벨5", List.of());

    private final String name;
    private final List<String> mission;

    Level(final String name, final List<String> mission) {
        this.name = name;
        this.mission = mission;
    }

    public static Map<String, List<String>> getMissionByLevel() {
        Map<String, List<String>> mission = new LinkedHashMap<>();
        for (Level level : Level.values()) {
            mission.put(level.name, level.mission);
        }
        return mission;
    }
}
