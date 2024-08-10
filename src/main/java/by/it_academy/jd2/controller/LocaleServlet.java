package by.it_academy.jd2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static by.it_academy.jd2.util.PathUtil.LOCALE_SERVLET;

@WebServlet(urlPatterns = LOCALE_SERVLET)
public class LocaleServlet extends HttpServlet {
    public static final String LANGUAGE_PARAMETER = "lang";
    public static final String HEADER_PREVIOUS_PAGE = "referer";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String language = req.getParameter(LANGUAGE_PARAMETER);

        req.getSession().setAttribute(LANGUAGE_PARAMETER, language);
        String prevPage = req.getHeader(HEADER_PREVIOUS_PAGE);
        resp.sendRedirect(prevPage);

    }
}
