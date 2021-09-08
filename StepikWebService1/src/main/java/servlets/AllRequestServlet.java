package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import templater.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllRequestServlet extends HttpServlet {
    private String login = "";

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message","");

        response.getWriter().println(request.getParameter("key"));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        if(message == null || message.isEmpty()){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else{
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message",message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURI().toString());
        pageVariables.put("pathInfo",request.getPathInfo());
        pageVariables.put("sessionId",request.getSession().getId());
        pageVariables.put("parameters",request.getParameterMap().toString());
        return pageVariables;
    }
}


