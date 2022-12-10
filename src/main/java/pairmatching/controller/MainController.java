package pairmatching.controller;

import pairmatching.controller.controllable.ControllableV3;
import pairmatching.domain.Command;
import pairmatching.domain.Mode;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    private final InputView inputView = new InputView();
    private final Map<String, ControllableV3> controllerMap = new HashMap<>();  // 유연하지 못하다.

    public MainController() {
        PairMatchingService pairMatchingService = new PairMatchingService();
        controllerMap.put(Mode.PAIR_MATCH.getFunc(), new PairMatchingControllable(pairMatchingService));
        controllerMap.put(Mode.PAIR_SEARCH.getFunc(), new PairSearchControllable(pairMatchingService));
        controllerMap.put(Mode.PARI_INIT.getFunc(), new PairInitControllable(pairMatchingService));
    }

    public void run() {
        while(true) {
            Command command = InputUtils.read(Command::new, inputView::getCommand);
            if (command.getMode() == Mode.QUIT) {
                break;
            }
            runByCommand(command);
        }
    }

    public void runByCommand(Command command) {
        Map<String, Object> modelMap = new HashMap<>();
        Mode mode = command.getMode();
        ControllableV3 controller = controllerMap.get(mode.getFunc());

        String viewName = controller.process(modelMap);
        ModelAndView modelAndView = new ModelAndView(viewName, modelMap);
        modelAndView.render();
    }
}
