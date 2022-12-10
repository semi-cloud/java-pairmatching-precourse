package pairmatching.handler.adapter;

import pairmatching.controller.ModelAndView;
import pairmatching.controller.controllable.ControllableV3;
import pairmatching.handler.HandlerAdapter;

import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllableV3);
    }

    @Override
    public ModelAndView handle(Object handler) {
        ControllableV3 controller = (ControllableV3) handler;
        Map<String, Object> modelMap = new HashMap<>();

        String viewName = controller.process(modelMap);
        return new ModelAndView(viewName, modelMap);
    }
}
