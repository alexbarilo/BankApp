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
import java.util.ArrayList;
import java.util.List;

public class ClientListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        String requestReceiver = null;
        HttpSession httpSession = req.getSession();
        Client currentClient = null;

        String clientAction = req.getParameter("client-action");
        String selectedClientID = req.getParameter("client-selection");

        ClientManagerImpl clientManager = new ClientManagerImpl();
        List<Client> listOfClients = new ArrayList<Client>();

        switch (clientAction) {

            case "Add client" :
                requestReceiver = "new_client.jsp";
                break;

            case "Delete client" :
                requestReceiver = "client_list.jsp";
                clientManager.deleteClient(selectedClientID);
                listOfClients = clientManager.getListOfClients();
                httpSession.setAttribute("listOfClients", listOfClients);
                break;

            case "Update client" :
                requestReceiver = "new_client.jsp";
                currentClient = clientManager.getClient(selectedClientID);
                req.setAttribute("currentClient", currentClient);
                break;

            case "Show client details" :
                requestReceiver = "client_account_buttons.jsp";
                currentClient = clientManager.getClient(selectedClientID);
                httpSession.setAttribute("currentClient", currentClient);
                break;
        }

        dispatcher = req.getRequestDispatcher(requestReceiver);
        dispatcher.forward(req, resp);
    }
}
