package pairmatching.view.output;

import pairmatching.view.Viewable;

import java.util.Map;

public class MatchingInitOutputView implements Viewable {

    private static final String PAIR_INIT = "\n초기화 되었습니다.\n";

    @Override
    public void render(Map<String, Object> model) {
        System.out.println(PAIR_INIT);
    }
}
