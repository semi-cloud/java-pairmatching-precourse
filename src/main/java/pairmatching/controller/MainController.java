package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.domain.Mode;
import pairmatching.handler.HandlerAdapter;
import pairmatching.handler.adapter.ControllerV2HandlerAdapter;
import pairmatching.handler.adapter.ControllerV3HandlerAdapter;
import pairmatching.service.PairMatchingService;
import pairmatching.utils.InputUtils;
import pairmatching.view.InputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    private final InputView inputView = new InputView();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public MainController() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        PairMatchingService pairMatchingService = new PairMatchingService();
        handlerMappingMap.put(Mode.PAIR_MATCH.getFunc(), new PairMatchingControllable(pairMatchingService));
        handlerMappingMap.put(Mode.PAIR_SEARCH.getFunc(), new PairSearchControllable(pairMatchingService));
        handlerMappingMap.put(Mode.PARI_INIT.getFunc(), new PairInitControllable(pairMatchingService));
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV2HandlerAdapter());
        handlerAdapters.add(new ControllerV3HandlerAdapter());
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
        Object handler = getHandler(command);
        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        ModelAndView modelAndView = handlerAdapter.handle(handler);
        modelAndView.render();
    }

    private Object getHandler(Command command) {
        Mode mode = command.getMode();
        return handlerMappingMap.get(mode.getFunc());
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. Handler=" + handler);
    }
}
