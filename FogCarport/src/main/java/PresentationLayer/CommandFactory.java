/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FunctionManager;
import FunctionLayer.MaterialSampleException;
import FunctionLayer.OrderSampleException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ludvig
 */
public class CommandFactory {

  private static CommandFactory instance = null;
  private final Map<String, Command> commands = new HashMap();

    private CommandFactory() {
        commands.put("main", new MainPageCommand("JSP/mainpage.jsp"));
        commands.put("styklist", new StyklistPageCommand("JSP/styklist.jsp"));
        commands.put("allmaterials", new StockMaterialsPageCommand("JSP/stockmaterialspage.jsp"));
        commands.put("editMaterial", new EditStockMaterialPageCommand("JSP/editMaterial.jsp"));
        commands.put("updateMaterial", new UpdateMaterialCommand("JSP/stockmaterialspage.jsp"));
        commands.put("deleteMaterial", new DeleteStockMaterialCommand("JSP/stockmaterialspage.jsp"));
        commands.put("createMaterial", new CreateStockMaterialCommand("JSP/stockmaterialspage.jsp"));
        commands.put( "styklistpage", new StyklistPageCommand("JSP/styklist.jsp"));
        commands.put("order", new OrderPageCommand("JSP/shop.jsp"));
        commands.put("editlineitem", new EditLineItemCommand("JSP/editlineitem.jsp"));
        commands.put("category", new CategoryCommand("JSP/category.jsp"));
        commands.put("stockListWood", new StockListWoodCommand("JSP/stockListWood.jsp"));
        commands.put("stockListScrews", new StockListScrewsCommand("JSP/stockListScrews.jsp"));
        commands.put("graphic", new GraphicCommand("JSP/carportSVGGraphic.jsp"));
        commands.put("stockListTagpakke", new StockListTagpakkeCommand("JSP/stockListTagpakke.jsp"));
        commands.put("AllOrders", new AllOrdersCommand("JSP/allOrdersPage.jsp"));
        commands.put("updateLineitem", new UpdateLineitemCommand("JSP/styklist.jsp"));
        commands.put("finalPrice", new PriceCommand("JSP/PriceFinalizePage.jsp"));
        commands.put("percent", new FinalizeOrderCommand("JSP/allOrdersPage.sjp"));
        commands.put("login", new LoginCommand("JSP/index.jsp"));
    }

    public static synchronized Command commandFrom(String key) {
    if (key == null) key = "back";
    if (instance == null) instance = new CommandFactory();
    return instance.commands.get(key);
    }

}
