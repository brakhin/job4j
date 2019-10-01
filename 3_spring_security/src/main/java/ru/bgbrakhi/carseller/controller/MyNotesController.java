package ru.bgbrakhi.carseller.controller;

// https://www.baeldung.com/spring-file-upload

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.bgbrakhi.carseller.models.*;
import ru.bgbrakhi.carseller.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.List;

@Controller
public class MyNotesController {
    private static final String UPLOAD_DIRECTORY  = "/image_upload";

    @Autowired
    private ICarService carService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private ICarBodyService carBodyService;

    @Autowired
    private ICarMarkService carMarkService;

    @Autowired
    private ICarModelService carModelService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/mynotes", method = RequestMethod.GET)
    public String showItems(Principal principal, ModelMap model) {
        loadData(principal, model);
        return "mynotes";
    }

    @RequestMapping(value = "/mynotes", method = RequestMethod.POST)
    public String addData(@RequestParam  String edCity,
                          @RequestParam  String edType,
                          @RequestParam  String edMark,
                          @RequestParam  String edModel,
                          @RequestParam  String edBody,
                          @RequestParam  String edYear,
                          @RequestParam  String edPrice,
                          @RequestParam CommonsMultipartFile edFile,
                          HttpSession session,
                          Principal principal,
                          ModelMap model) throws Exception {

        ServletContext context = session.getServletContext();
        String path = context.getRealPath(UPLOAD_DIRECTORY);
        String filename = edFile.getOriginalFilename();

        byte[] bytes = edFile.getBytes();
        if (bytes.length > 0) {
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(path + File.separator + filename)
                    )
            );
            stream.write(bytes);
            stream.flush();
            stream.close();
        }

        Car car = new Car();

        CarType carType = carTypeService.getByName(edType);
        if (carType == null) {
            carType = new CarType();
            carType.setName(edType);
        }

        CarMark carMark = carMarkService.getByName(edMark);
        if (carMark == null) {
            carMark = new CarMark();
            carMark.setName(edMark);
        }

        City city = cityService.getByName(edCity);
        if (city == null) {
            city = new City();
            city.setName(edCity);
        }

        CarBody carBody = carBodyService.getByName(edBody);
        if (carBody == null) {
            carBody = new CarBody();
            carBody.setName(edBody);
        }
//        carBody = carBodyService.save(carBody);

        CarModel carModel = carModelService.findByCartypeAndCarmarkAndName(carType, carMark, edModel);
        if (carModel == null) {
            carModel = new CarModel();
            carModel.setCartype(carType);
            carModel.setCarmark(carMark);
            carModel.setName(edModel);
        }

        User user = userService.findByLogin(principal.getName());

        car.setUser(user);
        car.setCity(city);
        car.setCarmodel(carModel);
        car.setCarbody(carBody);
        car.setYear(Integer.parseInt(edYear));
        car.setPrice(Integer.parseInt(edPrice));
        car.setFilename(filename);


        Car result = carService.save(car);

        loadData(principal, model);
        return "mynotes";
    }

    private void loadData(Principal principal, ModelMap model) {
        List<Car> cars = carService.findForUser(principal.getName());
        List<City> cities = cityService.getAll();
        List<CarType> carTypes = carTypeService.getAll();

        model.addAttribute("login", principal == null ? "" : String.format(" [ %s ]", principal.getName()));
        model.addAttribute("cars", cars);
        model.addAttribute("cities", cities);
        model.addAttribute("types", carTypes);
    }
}
