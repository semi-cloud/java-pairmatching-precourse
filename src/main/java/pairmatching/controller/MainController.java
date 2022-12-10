package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.domain.Mode;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    private final InputView inputView = new InputView();
    private final PairMatchingService pairMatchingService = new PairMatchingService();
    private final Map<String, ControllerV1> controllerMap = new HashMap<>();

    public MainController() {
        controllerMap.put(Mode.PAIR_MATCH.getFunc(), new PairMatchingController(pairMatchingService));
        controllerMap.put(Mode.PAIR_SEARCH.getFunc(), new PairSearchController(pairMatchingService));
        controllerMap.put(Mode.PARI_INIT.getFunc(), new PairInitController(pairMatchingService));
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
        Mode mode = command.getMode();
        ControllerV1 controller = controllerMap.get(mode.getFunc());
        controller.process();
    }
}
