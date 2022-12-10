package pairmatching.view;

import java.util.List;
import java.util.Map;

public class MatchingResultOutputView implements Viewable {
    private static final String MATCH_OUTPUT = "\n페어 매칭 결과입니다.";
    private static final String CREW_SEPARATE = " : ";

    @Override
    public void render(Map<String, Object> model) {
        @SuppressWarnings("unchecked")
        List<List<String>> matchResult = (List<List<String>>) model.get("matchResult");

        System.out.println(MATCH_OUTPUT);
        matchResult.forEach(x ->
                System.out.println(String.join(CREW_SEPARATE, x)));
        System.out.println();
    }
}
