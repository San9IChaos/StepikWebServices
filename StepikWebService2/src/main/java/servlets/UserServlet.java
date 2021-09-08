package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal","UnusedDeclaration"})
    private final AccountService accountService;

    public UserServlet(AccountService accountService){
        this.accountService=accountService;
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        accountService.addNewUser(new UserProfile(login));
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    }
    }
