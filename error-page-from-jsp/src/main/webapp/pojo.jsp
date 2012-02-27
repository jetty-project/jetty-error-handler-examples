<%@page import="org.eclipse.jetty.example.Fruit"%>
<html>
<%
   Fruit fruit = new Fruit();
   String description = fruit.getDescription(pageContext);
%>
</html>