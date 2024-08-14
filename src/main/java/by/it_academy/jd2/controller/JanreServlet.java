package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.ServiceJanre;
import by.it_academy.jd2.service.api.IJanreService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static by.it_academy.jd2.controller.FormForVoteServlet.ATTRIBUTE_REQUEST_JANRES;
import static by.it_academy.jd2.controller.FormForVoteServlet.PARAMETR_JANRE;

@WebServlet(urlPatterns = "/service/janre")
public class JanreServlet extends HttpServlet {

    public static final String ATTRIBUTE_ERROR_JANRE = "janreErr";

    IJanreService janreService = ServiceJanre.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        req.setAttribute(ATTRIBUTE_REQUEST_JANRES, janreService.get());
        req.getRequestDispatcher("/WEB-INF/jsp/janre.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String janre = req.getParameter(PARAMETR_JANRE);

        try {
            janreService.create(janre);
            doGet(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute(ATTRIBUTE_ERROR_JANRE, "Жанр не добавлен");
            doGet(req, resp);
        }
    }
}
