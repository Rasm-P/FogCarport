<%-- 
    Document   : PriceFinalizePage
    Created on : 13-05-2019, 09:54:18
    Author     : Ludvig
--%>

<%@page import="FunctionLayer.Order"%>
<jsp:include page='/JSP/siteheader.jsp'></jsp:include>

<jsp:include page='/JSP/sitemenus.jsp'></jsp:include>

    <div class="container">            

    <%  if (request.getAttribute("order") != null) {
            Order order = (Order) request.getAttribute("order");
            float price = (float) request.getAttribute("price");

            out.println("<h4> Samlede omkostninger af styklisten </h4>");
            out.println("<h5>" + price + "</h5>");
            out.println("<br>");
            out.println("<h5> Indtast procent </h5>");

            out.println("<form action=\"FrontController\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"percentage\" value=\"\">");
            //out.println("<input type=\"hidden\" name=\"price\" value=\"" + order.getPrice() + "\">");
            out.println("<input type=\"hidden\" name=\"command\" value=\"percent\">");
            out.println("<input type=\"hidden\" name=\"thisOrder\" value=\"" + order.getOrder_id() + "\">");
            out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Udregn ordrepris\">");
            out.println("</form>");

            out.println("<br>");
            out.println("<br>");
            out.println("<br>");

            if (request.getAttribute("orderPrice") != null) {
                out.println("<form action=\"FrontController\" method=\"POST\">");
                out.println("<input type=\"hidden\" name=\"command\" value=\"final\">");
                out.println("<input type=\"hidden\" name=\"thisOrder\" value=\"" + order.getOrder_id() + "\">");
                out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"F�rdigg�r ordre\" >");
                out.println("</form>");
            }
        }
    %>

</table>
</div>
