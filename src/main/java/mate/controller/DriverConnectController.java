package mate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.service.CarService;
import mate.service.DriverService;
import mate.service.ManufacturerService;

public class DriverConnectController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final String DRIVER_ID = "driverId";
    private static final String CAR_ID = "id";
    private final CarService carService =
            (CarService) injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);
    private long carId;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/driverConnect.jsp").forward(req, resp);
        carId = Long.parseLong(req.getParameter(CAR_ID));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id =
                req.getParameter(DRIVER_ID);
        if (id.length() < 1) {
            throw new RuntimeException("Driver id field was empty");
        }
        carService.addDriverToCar(driverService.get(Long.parseLong(id)),
                carService.get(carId));
        resp.sendRedirect(req.getContextPath() + "/cars/carList");
    }
}