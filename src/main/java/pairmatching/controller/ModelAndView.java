package pairmatching.controller;

import pairmatching.view.OutputViewResolver;
import pairmatching.view.Viewable;

import java.util.Map;

public class ModelAndView {
    private final String viewName;
    private Map<String, Object> modelMap;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public ModelAndView(String viewName, Map<String, Object> modelMap) {
        this.viewName = viewName;
        this.modelMap = modelMap;
    }

    public void render() {
        OutputViewResolver viewResolver = OutputViewResolver.getInstance();
        Viewable view = viewResolver.get(viewName);
        view.render(modelMap);
    }
}
