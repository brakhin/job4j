package ru.bgbrakhi.carseller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.service.ICarModelService;
import ru.bgbrakhi.carseller.service.ICarService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarsController {
    public final static String COANTANT_FILTER_ALL_MARKS = "Все";

    @Autowired
    ICarService carService;

    @Autowired
    ICarModelService carModelService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        List<Car> cars = carService.findWithFilter(null, true);
        List<CarModel> models = carModelService.findForType("");

        model.addAttribute("cars", cars);
        model.addAttribute("marks", getMarks(models));
        return "cars";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public String addItem(@ModelAttribute UserFilter filter, ModelMap model) {
        List<Car> cars = carService.findWithFilter(filter, true);
        List<CarModel> models = carModelService.findForType("");

        model.addAttribute("cars", cars);
        model.addAttribute("marks", getMarks(models));
        return "cars";
    }

    private List<CarMark> getMarks(List<CarModel> models) {
        List<CarMark> result = models.stream()
                .map(i -> i.getCarmark())
                .distinct()
                .collect(Collectors.toList());
        result.add(0, new CarMark(COANTANT_FILTER_ALL_MARKS));
        return result;
    }
}
