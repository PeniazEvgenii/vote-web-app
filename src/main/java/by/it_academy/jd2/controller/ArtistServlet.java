package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.ServiceSinger;
import by.it_academy.jd2.service.api.ISingerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/service/artist")
public class ArtistServlet extends HttpServlet {
    public static final String ATTRIBUTE_ERROR_ARTIST = "artistErr";
    public static final String ATTRIBUTE_ADD_ARTIST = "artistAdd";
    public static final String ATTRIBUTE_LIST_ARTIST = "artist";
    public static final String PARAMETER_ARTIST = "artist";

    private static final ISingerService singerService = ServiceSinger.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        req.setAttribute(ATTRIBUTE_LIST_ARTIST, singerService.get());
        req.getRequestDispatcher("/WEB-INF/jsp/artist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artist = req.getParameter(PARAMETER_ARTIST);

        try {
            Long id = singerService.create(artist);
            req.setAttribute(ATTRIBUTE_ADD_ARTIST, "Исполнитель добавлен в систему под id = " + id);
            doGet(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute(ATTRIBUTE_ERROR_ARTIST, "Исполнитель не добавлен");
            doGet(req, resp);
        }
    }
}
