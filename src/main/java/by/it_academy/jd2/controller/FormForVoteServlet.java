package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.service.ServiceJanre;
import by.it_academy.jd2.service.ServiceSinger;
import by.it_academy.jd2.service.api.IJanreService;
import by.it_academy.jd2.service.api.IServiceVote;
import by.it_academy.jd2.service.ServiceVote;
import by.it_academy.jd2.service.api.ISingerService;
import by.it_academy.jd2.util.JspUtil;
import by.it_academy.jd2.validation.ValidFormException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Enumeration;

import static by.it_academy.jd2.util.PathUtil.RESULT_VOTE_SERVLET;
import static by.it_academy.jd2.util.PathUtil.VOTE_SERVLET;

@WebServlet(urlPatterns = VOTE_SERVLET)
public class FormForVoteServlet extends HttpServlet {
    public static final String PARAMETR_JANRE = "janre";
    public static final String PARAMETR_SINGER = "singer";
    public static final String PARAMETR_INFO_ABOUT_MYSESLF = "info";
    public static final String ATTRIBUTE_REQUEST_JANRES = "janres";
    public static final String ATTRIBUTE_REQUEST_SINGERS = "singers";
    public static final String ATTRIBUTE_REQUEST_VOTE_ERRORS = "voteErrors";
    public static final String JSP_PAGE_WITH_FORM = "vote";

    private final IServiceVote serviceVote = ServiceVote.getInstance();
    private final ISingerService singerService = ServiceSinger.getInstance();
    private final IJanreService janreService = ServiceJanre.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        req.setAttribute(ATTRIBUTE_REQUEST_SINGERS, singerService.get());
        req.setAttribute(ATTRIBUTE_REQUEST_JANRES, janreService.get());

        req.getRequestDispatcher(JspUtil.getPath(JSP_PAGE_WITH_FORM)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InfoFromUserDTO infoFromUserDto = InfoFromUserDTO.builder()
                .setSinger(req.getParameter(PARAMETR_SINGER))
                .setJanres(req.getParameterValues(PARAMETR_JANRE))
                .setDateTime(LocalDateTime.now())
                .setInfo(req.getParameter(PARAMETR_INFO_ABOUT_MYSESLF))
                .build();

        try {
            serviceVote.create(infoFromUserDto);
            resp.sendRedirect(req.getContextPath() + RESULT_VOTE_SERVLET);
        } catch (ValidFormException validationException) {
            req.setAttribute(ATTRIBUTE_REQUEST_VOTE_ERRORS, validationException.getErrors());
            doGet(req, resp);
        } catch (IllegalArgumentException e) {
            try (PrintWriter writer = resp.getWriter()) {
                writer.write("<p>error: " + e.getMessage() + "</p>");
            }
        }

    }
}
