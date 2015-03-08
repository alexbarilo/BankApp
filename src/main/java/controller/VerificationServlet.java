package controller;

import model.entity.Client;
import model.service.ClientManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class VerificationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String requestReceiver = null;

        if (login.equals("user")){
            HttpSession httpSession = req.getSession();
            ClientManagerImpl clientManager = new ClientManagerImpl();
            requestReceiver = "client_list.jsp";
            List<Client> listOfClients = clientManager.getListOfClients();
            httpSession.setAttribute("listOfClients", listOfClients);
        } else {
            String message = "Sorry, you input incorrect login" +
                    "Please, try again";
            String redirectionPage = "index.jsp";
            req.setAttribute("message", message);
            req.setAttribute("redirectionPage", redirectionPage);
            requestReceiver = "error_page.jsp";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(requestReceiver);
        dispatcher.forward(req, resp);
    }
}
