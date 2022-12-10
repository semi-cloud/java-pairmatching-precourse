package pairmatching.view.output;

import pairmatching.view.Viewable;

import java.util.HashMap;
import java.util.Map;

public class OutputViewResolver {
    private final Map<String, Viewable> viewMap = new HashMap<>();
    private static final OutputViewResolver outputViewFactory = new OutputViewResolver();

    private OutputViewResolver() {
        viewMap.put("info", new MatchingInfoOutputView());
        viewMap.put("init", new MatchingInitOutputView());
        viewMap.put("result", new MatchingResultOutputView());
    }

    public static OutputViewResolver getInstance() {
        return outputViewFactory;
    }

    public Viewable get(String viewName) {
        return viewMap.get(viewName);
   }
}
