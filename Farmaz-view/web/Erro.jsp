<%-- 
    Document   : Erro
    Created on : 22/07/2018, 21:24:13
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String erro;
    erro = (String) request.getAttribute("erro");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
    </head>
    <body>
        <h1>Erro:</h1>
        <h2><%=erro%></h2>
    </body>
</html>
