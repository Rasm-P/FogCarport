<%-- 
    Document   : carportSVGGraphic
    Created on : 08-05-2019, 10:24:40
    Author     : Ludvig
--%>

<%@page import="FunctionLayer.CarportAlgorithm"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Material"%>
<%@page import="FunctionLayer.Material"%>
<%@page import="FunctionLayer.Stykliste"%>
<jsp:include page='/JSP/siteheader.jsp'></jsp:include>

<jsp:include page='/JSP/sitemenus.jsp'></jsp:include>

    <h2>Din tegning!</h2>

<%
    //Order order = new Order(1, 6000, 7800, 2300, 0, 5300, 2100);
    //Order(int order_id, float width, float length, float height, float roofTilt, float shedWidth, float shedLength)
    //carportAlgorithm(float width, float length, float roofTilt, float shedwidth, float shedLength, int styklist_id)
    
    Order order = null;

    if (session.getAttribute("order") != null) {
        order = (Order) session.getAttribute("order");
    }
%>

    <div>
           <svg width="<% out.println(order.getLength() / 10); %>" height="<% out.println(order.getHeight() / 10); %>">

        <circle cx="0" cy="0" r="4" stroke="black" stroke-width="3" fill="red" />
        <circle cx="<% out.println(order.getLength() / 10); %>" cy="0" r="4" stroke="black" stroke-width="3" fill="red" />
        <circle cx="0" cy="<% out.println(2300 / 10); %>" r="4" stroke="black" stroke-width="3" fill="red" />
        <circle cx="<% out.println(order.getLength() / 10); %>" cy=" <% out.println(2300 / 10); %> " r="4" stroke="black" stroke-width="3" fill="red" />

        <%
            double sum = 0;
            for (int i = 0; i < (int) (order.getShedLength() / 60); i++) {
        %>
        <rect x=" <% out.println(((order.getLength() - order.getShedLength() - 300) + sum) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="blue"/>
        <%
                sum += 60;
            }
        %>
        <rect x=" <% out.println(1000.0 / 7800.0 * order.getLength() / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="red"/>
        <rect x=" <% out.println((order.getLength() - 300) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="red"/>
        <rect x=" <% out.println((order.getLength() - order.getShedLength() - 300) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="red"/>
        <rect x=" <% out.println(((1000.0 / 7800.0 * order.getLength()) + (order.getStyklist().getStyklist().get(0).getWidth()) + (3100.0 / 7800 * order.getLength())) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="red"/>
        <rect x='0' y='0' width='<% out.println((Math.sqrt(Math.pow(2300 - 2200, 2) + Math.pow(order.getLength(), 2))) / 10); %>' height='<% out.println((order.getStyklist().getStyklist().get(13).getHeight()) / 10);%>'  transform='rotate( <% out.println(90 - Math.toDegrees(Math.atan((7800 / (2300 - 2200)))));%> )'/>
        <rect x='<% out.println(25 / 10); %>' y='<% out.println(order.getStyklist().getStyklist().get(13).getHeight() / 10); %>' width='<% out.println(((Math.sqrt(Math.pow(2300 - 2200, 2) + Math.pow(order.getLength(), 2))) / 10) - 50 / 10); %>' height='<% out.println((order.getStyklist().getStyklist().get(11).getHeight()) / 10);%>'  transform='rotate( <% out.println(90 - Math.toDegrees(Math.atan((7800 / (2300 - 2200)))));%> )'/>
        </svg>
    </div>

    <br>

    <div>
        <svg width="<% out.println(order.getWidth() / 10); %>" height="<% out.println(order.getHeight() / 10); %>">
        <circle cx="0" cy="0" r="4" stroke="black" stroke-width="3" fill="red" />
        <circle cx="<% out.println(order.getWidth() / 10); %>" cy="0" r="4" stroke="black" stroke-width="3" fill="red" />
        <circle cx="0" cy="<% out.println(2300 / 10); %>" r="4" stroke="black" stroke-width="3" fill="red" />
        <circle cx="<% out.println(order.getWidth() / 10); %>" cy=" <% out.println(2300 / 10);%> " r="4" stroke="black" stroke-width="3" fill="red" />
        <%
            double sum2 = 0;
            for (int j = 0; j < (int) (order.getShedWidth() / 60); j++) {
        %>
        <rect x=" <% out.println(((700 / 2) + sum2) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="blue"/>
        <%
                sum2 += 60;
            }
        %>
        <rect x=" <% out.println((700 / 2) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="red"/>
        <rect x=" <% out.println((order.getWidth() - order.getStyklist().getStyklist().get(0).getWidth() - (700 / 2)) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10); %> " stroke="black" fill="red"/>
        <rect x=" <% out.println(((order.getWidth() / 2) - order.getStyklist().getStyklist().get(0).getWidth() / 2) / 10); %> " y=" <% out.println((order.getHeight() - (order.getStyklist().getStyklist().get(0).getLength() - 900)) / 10); %> " height=" <% out.println(((order.getStyklist().getStyklist().get(0).getLength() - 900) / 10)); %> " width=" <% out.println(order.getStyklist().getStyklist().get(0).getWidth() / 10);%> " stroke="black" fill="red"/>
        <rect x='0' y='0' width='<% out.println((Math.sqrt(Math.pow(2300 - 2200, 2) + Math.pow(order.getWidth(), 2))) / 10); %>' height='<% out.println((order.getStyklist().getStyklist().get(13).getHeight()) / 10);%>' />
        <rect x='<% out.println(25 / 10); %>' y='<% out.println(order.getStyklist().getStyklist().get(13).getHeight() / 10); %>' width='<% out.println(((Math.sqrt(Math.pow(2300 - 2200, 2) + Math.pow(order.getWidth(), 2))) / 10) - 50 / 10); %>' height='<% out.println((order.getStyklist().getStyklist().get(11).getHeight()) / 10);%>' />

        </svg>
    </div>

<jsp:include page='/JSP/sitefooter.jsp'></jsp:include>
