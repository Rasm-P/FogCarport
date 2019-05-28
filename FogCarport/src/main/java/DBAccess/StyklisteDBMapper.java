/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.Material;
import FunctionLayer.StyklistException;
import FunctionLayer.Stykliste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ludvig
 */
public class StyklisteDBMapper extends StyklisteMapper {

    private Connector dbc = new Connector();

    private static StyklisteDBMapper instance = null;

    /**
     * Returns instance of StyklisteDBMapper
     *
     * @return StyklisteDBMapper
     */
    public synchronized static StyklisteDBMapper getInstance() {
        if (instance == null) {
            instance = new StyklisteDBMapper();
        }
        return instance;
    }

    /**
     * Sets connection
     *
     * @param connection
     */
    public void setMapperConnection(Connection connection) {
        dbc.setConnection(connection);
    }

    /**
     * Updates row in the database, requires all possible parameters
     *
     * @param item_id
     * @param item_description
     * @param width
     * @param height
     * @param entity
     * @param materialtype
     * @param price
     * @param orderquantity
     * @param order_id
     */
    @Override
    public void editLineItemsFromOrderID(int item_id, String item_description, float width, float length, float height,
            String entity, String materialtype, float price, int orderquantity, int order_id) {
        try {
            Connection con = dbc.connection();
            String sql = "UPDATE lineitems SET item_id=?, item_description=?, width=?, length=? "
                    + "height=?, entity=?, materialtype=?, price=?, orderquantity=? where order_id=? and item_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, item_id);
            ps.setString(2, item_description);
            ps.setFloat(3, width);
            ps.setFloat(4, length);
            ps.setFloat(5, height);
            ps.setString(6, entity);
            ps.setString(7, materialtype);
            ps.setFloat(8, price);
            ps.setInt(9, orderquantity);
            ps.setInt(10, order_id);
            ps.setInt(11, item_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {

        }
    }

    /**
     * Saves a stykliste in the database which is assigned to an order by id
     *
     * @param styklist
     * @param order_id
     */
    @Override
    public void saveLineItemsInDB(Stykliste styklist, int order_id) {
        try {
            ArrayList<Material> lineitems = styklist.getStyklist();

            Connection con = dbc.connection();

            String sql = "INSERT INTO lineitems(item_id, order_id, item_description, width, length, height, entity, materialtype, price, orderquantity, versionnr)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < lineitems.size(); i++) {
                ps.setInt(1, lineitems.get(i).getItem_id());
                ps.setInt(2, order_id);
                ps.setString(3, lineitems.get(i).getItem_description());
                ps.setFloat(4, lineitems.get(i).getWidth());
                ps.setFloat(5, lineitems.get(i).getLength());
                ps.setFloat(6, lineitems.get(i).getHeight());
                ps.setString(7, lineitems.get(i).getEntity());
                ps.setString(8, lineitems.get(i).getMaterialType());
                ps.setFloat(9, lineitems.get(i).getPrice());
                ps.setInt(10, lineitems.get(i).getStryklistQty());
                ps.setInt(11, lineitems.get(i).getVersionnr());

                ps.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {

        }

    }

    /**
     * Returns a Material object based of its id
     *
     * @param lineItemID
     * @return Material
     * @throws StyklistException
     */
    @Override
    public Material getMaterialFromLineItems(int lineItemID) throws StyklistException {
        try {
            String sql = "Select * from lineitems where lineitems_id=?";
            Connection conn = dbc.connection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, lineItemID);
            ResultSet rs = ps.executeQuery();
            Material material = null;
            while (rs.next()) {
                material = new Material(rs.getInt(2), rs.getString(4), rs.getFloat(5), rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getFloat(10), rs.getInt(11));
                material.setStyklistQty(rs.getInt(10));
                material.setLineItemID(rs.getInt(1));
                material.setLength(rs.getFloat(6));
            }
            return material;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new StyklistException(ex.getMessage());
        }

    }

}
