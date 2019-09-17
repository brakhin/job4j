package ru.bgbrakhi.carseller.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bgbrakhi.carseller.models.CarBody;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.services.ICarBodyService;
import ru.bgbrakhi.carseller.services.ICarMarkService;
import ru.bgbrakhi.carseller.services.ICarModelService;
import ru.bgbrakhi.carseller.services.ICarService;

import java.security.Principal;
import java.util.List;

@Controller
public class AjaxController {

    private static final String CONSTANT_SWAP_INACTIVE = "swap_inactive";
    private static final String CONSTANT_GET_MODELS = "get_models";
    private static final String CONSTANT_GET_MARKS = "get_marks";
    private static final String CONSTANT_GET_BODIES = "get_bodies";

    @Autowired
    ICarService carService;

    @Autowired
    ICarModelService carModelService;

    @Autowired
    ICarMarkService carMarkService;

    @Autowired
    ICarBodyService carBodyService;

    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public @ResponseBody String  ajaxGetHandler(@RequestParam String command, @RequestParam String data) {
        String result = "";
        if (CONSTANT_GET_MODELS.equals(command)) {
            List<CarModel> carModels = carModelService.findForType(data);
            result = new Gson().toJson(carModels);
        } else if (CONSTANT_GET_MARKS.equals(command)) {
            List<CarMark> carMarks = carMarkService.findForType(data);
            result = new Gson().toJson(carMarks);
        } else if (CONSTANT_GET_BODIES.equals(command)) {
            List<CarBody> carBodies = carBodyService.getCarBodyByType(data);
            result = new Gson().toJson(carBodies);
        }

        return result;
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody String ajaxPostHandler(@RequestParam String command, @RequestParam String data,
                                                Principal principal) {

        if (CONSTANT_SWAP_INACTIVE.equals(command)) {
            carService.swapInactive(Long.parseLong(data), principal.getName());
        }

        return "";
    }
}
