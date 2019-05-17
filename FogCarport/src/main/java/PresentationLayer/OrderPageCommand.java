/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FunctionManager;
import FunctionLayer.LoginSampleException;
import FunctionLayer.MaterialSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderSampleException;
import FunctionLayer.Stykliste;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ludvig
 */
class OrderPageCommand implements Command {

    private int id = 1;

    private String target;

    OrderPageCommand(String target) {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, FunctionManager manager) throws LoginSampleException, OrderSampleException, MaterialSampleException {      
        
        HttpSession session = request.getSession();
        if(loginStatus(session)) {
            return "index.jsp";
        }
        float width = Float.parseFloat(request.getParameter("width"));
        float length = Float.parseFloat(request.getParameter("length"));
        float shedLength = Float.parseFloat(request.getParameter("shedLength"));
        float shedWidth = Float.parseFloat(request.getParameter("shedWidth"));
        float roofTilt = Integer.parseInt(request.getParameter("roof"));
        String name = request.getParameter("name");
        float height = 2300;

        if (width > 7500 || width < 2400 || length > 7800 || length < 2400 || shedLength > 6900 || shedLength < 1500 || shedWidth > 7200 || shedWidth < 2100 || roofTilt > 45 || roofTilt < 0) {
            //throw new MaterialSampleException("Fejl i mål");
            
            return target;
        } else {

            //CarportAlgorithm ca = new CarportAlgorithm();
            User user = new User(name, 1, "");
           Order order = new Order(id, width, length, height, roofTilt, shedWidth, shedLength);
            order.setUser(user);
            Stykliste sl = manager.carportAlgorithm(width, length, roofTilt, shedWidth, shedLength, id);
            order.setStyklist(sl);
            
            //DatabaseFacade dbf = new DatabaseFacade();
            manager.saveOrder(order);

            session.setAttribute("order", order);
            session.setAttribute("stykliste", sl);
            session.setAttribute("shopOrder", order);
            
        }
        return target;

    }

    @Override
    public boolean loginStatus(HttpSession session) {
         if(session.getAttribute("user") != null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean accesToPage(HttpSession session, String accesForRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

    


