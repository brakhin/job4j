package ru.bgbrakhi.carseller.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.bgbrakhi.carseller.models.*;
import ru.bgbrakhi.carseller.service.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDataController extends HttpServlet {
    private final Validator logic = Validator.getInstance();

    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "image_upload";
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            List<FileItem> formItems = upload.parseRequest(request);
            String fileName1 = "";
            if (formItems != null && formItems.size() > 0) {

                Map<String, String> map = new HashMap<>();

                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        if (!item.getName().isEmpty()) {
                            String fileName = new File(item.getName()).getName();
                            fileName1 += fileName;
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            map.put("file", fileName1);
                        }
                    } else {
                        String fieldname = item.getFieldName().toLowerCase();
                        String fieldvalue = item.getString();
                        if (fieldname.startsWith("ed")) {
                            map.put(fieldname.substring(2), fieldvalue);
                        }
                    }
                }
                HttpSession session = request.getSession();
                Car car = new Car();
                CarModel carModel = new CarModel();
                CarType carType = new CarType();
                carType.setName(map.get("type"));
                CarMark carMark = new CarMark();
                carMark.setName(map.get("mark"));
                City city = new City();
                city.setName(map.get("city"));
                CarBody carBody = new CarBody();
                carBody.setName(map.get("body"));
                carModel.setCartype(carType);
                carModel.setCarmark(carMark);
                carModel.setName(map.get("model"));
                car.setCity(city);
                car.setCarmodel(carModel);
                car.setCarbody(carBody);
                car.setYear(Integer.parseInt(map.get("year")));
                car.setPrice(Integer.parseInt(map.get("price")));
                car.setFilename("null".equals(map.get("file")) ? "" : map.get("file"));
                logic.getCar(new CarData((String) session.getAttribute("login"), map.get("city"), map.get("type"),
                        map.get("mark"), map.get("model"), map.get("body"), Integer.parseInt(map.get("year")),
                        Integer.parseInt(map.get("price")), "null".equals(map.get("file")) ? "" : map.get("file")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("mynotes.html").forward(request, response);
    }
}
