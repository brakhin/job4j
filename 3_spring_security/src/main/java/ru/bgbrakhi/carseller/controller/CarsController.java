package ru.bgbrakhi.carseller.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.services.CarModelServiceImpl;
import ru.bgbrakhi.carseller.services.CarServiceImpl;
import ru.bgbrakhi.carseller.services.ICarModelService;
import ru.bgbrakhi.carseller.services.ICarService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarsController {
    public final static String COANTANT_FILTER_ALL_MARKS = "Все";

    @Autowired
    private ServletContext servletContext;

    @Autowired
    ICarService carService;

    @Autowired
    ICarModelService carModelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showItems(ModelMap model, Principal principal) {
        loadData(null, model, principal);
        return "cars";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addItem(@ModelAttribute UserFilter filter, ModelMap model, Principal principal) {
        loadData(filter, model, principal);
        return "cars";
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream in = servletContext.getResourceAsStream(String.format("image_upload\\%s", request.getParameter("file")));
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    private void loadData(UserFilter filter, ModelMap model, Principal principal) {
        List<Car> cars = carService.findWithFilter(filter, true);
        List<CarModel> models = carModelService.findForType("");
        model.addAttribute("login", principal == null ? "" : String.format(" [ %s ]", principal.getName()));
        model.addAttribute("cars", cars);
        model.addAttribute("marks", getMarks(models));
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
