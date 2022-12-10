package pairmatching.controller.controllable;

import java.util.Map;

@FunctionalInterface
public interface ControllableV3 {
    String process(Map<String, Object> modelMap);
}