package mate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.service.ManufacturerService;

public class ManufacturerDeleteController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final String ID_PARAM = "id";
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        manufacturerService
                .delete(manufacturerService
                        .get(Long.parseLong(req.getParameter(ID_PARAM))).getId());
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/manufacturerList.jsp")
                .include(req, resp);
    }
}