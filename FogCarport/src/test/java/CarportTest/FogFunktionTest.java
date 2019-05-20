package CarportTest;

import DBAccess.Connector;
import DBAccess.MaterialMapper;
import DBAccess.OrderMapper;
import DBAccess.StyklisteMapper;
import DBAccess.UserMapper;
import FunctionLayer.CarportAlgorithm;
import FunctionLayer.Encryption;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.MaterialSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderSampleException;
import FunctionLayer.StyklistException;
import FunctionLayer.Stykliste;
import FunctionLayer.User;
import java.rmi.AccessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import static java.util.Objects.hash;
import static org.hamcrest.CoreMatchers.any;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
//import org.mockito.Mock;
//import static org.mockito.Mockito.when;
//import org.mockito.MockitoAnnotations;

/**
 *
 * @author Rasmus2
 */
public class FogFunktionTest {

    private String url = "jdbc:mysql://167.99.209.155/fogTest?useUnicode=yes&characterEncoding=utf-8";
    private String user = "fogtest";
    private String password = "fogTest123!";

    @Test
    public void testCarportAlgorithm() throws MaterialSampleException {
        CarportAlgorithm car = new CarportAlgorithm();
        Stykliste styklist = car.carportAlgorithm(6000, 7800, 0, 5300, 2100, 1);
        assertNotNull(styklist);
        assertThat(styklist.getStyklist().get(4).getItem_description(), is("firkantskiver 40x40x11mm"));
        assertThat(styklist.getStyklist().get(4).getStryklistQty(), is(20));

        Stykliste styklist2 = car.carportAlgorithm(6000, 7800, 30, 5300, 2100, 1);
        assertNotNull(styklist2);
        assertThat(styklist2.getStyklist().get(7).getItem_description(), is("universal 190 mm venstre"));
        assertThat(styklist2.getStyklist().get(7).getStryklistQty(), is(8));
    }

    @Test
    public void testMaterial() {
        Material mat = new Material(0, "TestMaterial", 0.0f, 0.0f, "testEntity", "test", 0f, 1);
        assertNotNull(mat);
        mat.setStyklistQty(9);
        mat.setConstructionDescription("This description!");
        mat.setLineItemID(11);
        assertThat(mat.getStryklistQty(), is(9));
        assertThat(mat.GettConstructionDescription(), is("This description!"));
        assertThat(mat.getLineItemID(), is(11));
    }

    @Test
    public void testOrder() {
        Order order = new Order(1, 6000, 7800, 2300, 0, 5300, 2100);
        assertNotNull(order);
        order.setOrderStatus(true);
        LocalDate today = LocalDate.now();
        order.setOrderdate(Date.valueOf(today));
        order.setPrice(15000f);
        assertThat(order.isOrderStatus(), is(true));
        assertThat(order.getOrderdate(), is(Date.valueOf(today)));
        assertThat(order.getPrice(), is(15000f));
    }

    @Test
    public void testStykliste() {
        ArrayList<Material> arr = new ArrayList<>();
        Stykliste styk = new Stykliste(arr, 1);
        assertNotNull(styk);
        assertThat(styk.getStyklist().size(), is(0));
    }

    @Test
    public void testGetSalt() {
        Encryption enc = new Encryption();
        String wordScheme = enc.getSalt(8);
        assertNotNull(wordScheme);
    }

    @Test
    public void testGenerateSecurePassword() {
        Encryption en = new Encryption();
        String pass = "1234";
        String salt = "zTajvPTS";
        byte[] securePassword = en.hash(pass.toCharArray(), salt.getBytes());
        String securePass = Base64.getEncoder().encodeToString(securePassword);
        assertNotNull(securePass);
        assertThat(securePass, is("Hvt/wArJKY9e1CxGHzaoS2Sswcudy62ZAgh6wWQ3kVM="));
    }

    @Test
    public void testVerifyUserPassword() {
        Encryption en = new Encryption();
        String pass = "1234";
        String salt = "zTajvPTS";
        byte[] securePassword = en.hash(pass.toCharArray(), salt.getBytes());
        String securePass = Base64.getEncoder().encodeToString(securePassword);
        assertNotNull(securePass);
        assertTrue(en.verifyUserPassword(pass, securePass, salt));
    }

    @Test
    public void testUser() {
        User user = new User("Test", 7, "TestUser");
        assertNotNull(user);
        Encryption en = new Encryption();
        String pass = "1234";
        String salt = "zTajvPTS";
        byte[] securePassword = en.hash(pass.toCharArray(), salt.getBytes());
        String securePass = Base64.getEncoder().encodeToString(securePassword);
        user.setPassword(securePass);
        assertTrue(en.verifyUserPassword(pass, user.getPassword(), salt));
    }

}
