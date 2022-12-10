package pairmatching.controller.controllable;

import pairmatching.controller.ModelAndView;

@FunctionalInterface
public interface ControllableV2 {
    ModelAndView process();
}
