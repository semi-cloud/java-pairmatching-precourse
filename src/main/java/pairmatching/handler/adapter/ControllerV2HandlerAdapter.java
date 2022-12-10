package pairmatching.handler.adapter;

import pairmatching.controller.ModelAndView;
import pairmatching.controller.controllable.ControllableV2;
import pairmatching.handler.HandlerAdapter;

public class ControllerV2HandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllableV2);
    }

    @Override
    public ModelAndView handle(Object handler) {
        ControllableV2 controller = (ControllableV2) handler;
        return controller.process();
    }
}
