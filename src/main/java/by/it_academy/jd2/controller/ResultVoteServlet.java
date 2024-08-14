package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.TextTimeString;
import by.it_academy.jd2.service.ServiceGetData;
import by.it_academy.jd2.service.ServiceJanre;
import by.it_academy.jd2.service.ServiceSinger;
import by.it_academy.jd2.service.api.IJanreService;
import by.it_academy.jd2.service.api.IServiceGetData;
import by.it_academy.jd2.service.api.ISingerService;
import by.it_academy.jd2.util.JspUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static by.it_academy.jd2.util.PathUtil.RESULT_VOTE_SERVLET;

@WebServlet(urlPatterns = RESULT_VOTE_SERVLET)
public class ResultVoteServlet extends HttpServlet {
    public static final String ATTRIBUTE_JANRE_SORT = "janresort";
    public static final String ATTRIBUTE_SINGER_SORT = "singersort";
    public static final String ATTRIBUTE_TEXT_SORT = "textsort";
    public static final String ATTRIBUTE_MAP_SINGERS = "test1";
    public static final String ATTRIBUTE_MAP_JANRES = "test2";
    public static final String JSP_PAGE_WITH_RESULT = "resultVote";

    IServiceGetData serviceGetData = ServiceGetData.getInstance();
    ISingerService singerService = ServiceSinger.getInstance();
    IJanreService janreService = ServiceJanre.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TextTimeString> textAndTimeVotes = serviceGetData.getData().getTextAndTimeVotes();
        List<Map.Entry<Long, Long>> sortSing = serviceGetData.getData().getSortSing();
        List<Map.Entry<Long, Long>> sortJanr = serviceGetData.getData().getSortJanr();

        req.setAttribute(ATTRIBUTE_MAP_SINGERS, singerService.get());
        req.setAttribute(ATTRIBUTE_MAP_JANRES, janreService.get());
        req.setAttribute(ATTRIBUTE_SINGER_SORT, sortSing);
        req.setAttribute(ATTRIBUTE_JANRE_SORT, sortJanr);
        req.setAttribute(ATTRIBUTE_TEXT_SORT, textAndTimeVotes);

        req.getRequestDispatcher(JspUtil.getPath(JSP_PAGE_WITH_RESULT)).forward(req, resp);

    }
}
