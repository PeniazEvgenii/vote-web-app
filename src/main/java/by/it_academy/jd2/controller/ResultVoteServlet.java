package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.TextTimeString;
import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.service.ServiceGetData;
import by.it_academy.jd2.service.api.IServiceGetData;
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

    IServiceGetData serviceGetData = ServiceGetData.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map.Entry<ESingers, Integer>> sortSingers = serviceGetData.getData().getSortSingers();
        List<Map.Entry<EJanres, Integer>> sortJanres = serviceGetData.getData().getSortJanres();
        List<TextTimeString> textAndTimeVotes = serviceGetData.getData().getTextAndTimeVotes();

        req.setAttribute(ATTRIBUTE_SINGER_SORT, sortSingers);
        req.setAttribute(ATTRIBUTE_JANRE_SORT, sortJanres);
        req.setAttribute(ATTRIBUTE_TEXT_SORT, textAndTimeVotes);

        req.getRequestDispatcher(JspUtil.getPath("resultVote")).forward(req, resp);

    }
}
