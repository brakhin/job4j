package ru.bgbrakhi.carseller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.models.User;
import ru.bgbrakhi.carseller.service.Storage;
import ru.bgbrakhi.carseller.service.Validator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Controller
public class CarsController {
    private final Validator logic = Validator.getInstance();

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        List<Car> cars = logic.getAllCars(null, true);
        List<CarModel> models = logic.getModels("");
        model.addAttribute("cars", cars);
        model.addAttribute("marks", getMarks(models));
        return "cars";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public String addItem(@ModelAttribute UserFilter filter, ModelMap model) {
        List<Car> cars = logic.getAllCars(filter, true);
        List<CarModel> models = logic.getModels("");
        model.addAttribute("cars", cars);
        model.addAttribute("marks", getMarks(models));
        return "cars";
    }

    private List<CarMark> getMarks(List<CarModel> models) {
        List<CarMark> result = models.stream()
                .map(i -> i.getCarmark())
                .distinct()
                .collect(Collectors.toList());
        result.add(0, new CarMark(Storage.COANTANT_FILTER_ALL_MARKS));
        return result;
    }
}
