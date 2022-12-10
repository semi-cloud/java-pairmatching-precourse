package pairmatching.handler;

import pairmatching.controller.ModelAndView;

public interface HandlerAdapter {
    boolean supports(Object handler);

    ModelAndView handle(Object handler);
}
