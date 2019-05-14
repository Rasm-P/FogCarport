/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import DBAccess.DatabaseFacade;
import FunctionLayer.FunctionManager;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.MaterialSampleException;
import FunctionLayer.OrderSampleException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ludvig
 */
public class UpdateMaterialCommand implements Command {

    private String target;

    UpdateMaterialCommand(String target) {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, FunctionManager manager) throws LoginSampleException, OrderSampleException, MaterialSampleException, ClassNotFoundException {
        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        float width = Float.parseFloat(request.getParameter("width"));
        float height = Float.parseFloat(request.getParameter("height"));
        String entity = request.getParameter("entity");
        String type = request.getParameter("type");
        float price = Float.parseFloat(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));

        //DatabaseFacade df = new DatabaseFacade();
        manager.updateMaterialData(id, description, width, height, entity, type, price, qty);

        ArrayList<Material> materials = manager.getAllMaterials();
        session.setAttribute("stockMaterialList", materials);

        return target;
    }
}
