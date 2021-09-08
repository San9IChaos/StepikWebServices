package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SessionServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionServlet(AccountService accountService){
        this.accountService=accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if(profile==null){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else{
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login==null & password==null){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if(profile==null){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Status code (200)\n Unauthorized");
            return;
        }
        accountService.addSession(request.getSession().getId(), profile);
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Status code (401)\n Authorized: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if(profile==null){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else{
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
