<%-- 
    Document   : HistoricoCompras
    Created on : 02/09/2018, 12:50:48
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterFarmaciaProxy"%>
<%@page import="java.util.Collections"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Farmacia"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page ="MenuCliente.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Farmaz</title>
        
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" language="JavaScript" src="js/script.js"></script>
    </head>
    <body>
         <div class="container">   
            <center>
                <h3>Histórico de Pedidos</h3>
            </center>
            
            <form name="frmHistorico" method='post' action='ServletWeb' class="form-horizontal">
                <input type='hidden' name='table' value='Historico'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <table class="table table-striped"> 
                    <tr>
                        <td>
                            Farmácia
                        </td>
                        <td>
                            Data
                        </td>
                        <td>
                            Satus do pedido
                        </td>
                        <td>
                            Valor
                        </td>
                        <td>
                            &nbsp
                        </td>
                    </tr>
                    <%
                        List<Pedido> historico = (List<Pedido>) request.getAttribute("Historico");
                        ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
                        Farmacia farmacia = new Farmacia();
                        String status = " ";
                        Collections.reverse(historico);
                        
                        for (Pedido pedido: historico) {
                            farmacia = manterFarmacia.getFarmaciaById(pedido.getFarmaciaId());
                            if(pedido.getIdtStatus() == 'F'){
                                status = "Realizado";
                            }else if(pedido.getIdtStatus() == 'P'){
                                status = "Preparando envio";
                            }else if(pedido.getIdtStatus() == 'A'){
                                status = "A caminho";
                            }else if(pedido.getIdtStatus() == 'C'){
                                status = "Concluído";
                            }
                    %>
                            <tr>
                                <td>
                                    <%=farmacia.getNome()%>
                                </td>
                                <td>
                                    <%=pedido.getDataHora()%>
                                </td>
                                <td>
                                    <%=status%>
                                </td>
                                <td>
                                    <%=pedido.getValor()%>
                                </td>
                                <td>
                                    <a href="/Farmaz-view/ServletWeb?acao=ItensPedido&Pedido=<%=pedido.getPedidoId()%>">Itens</a>
                                </td> 
      
                            </tr>
                            <% }

                            %>
                    </table>
                            
                <div class="form-group">
                    <div class="col-sm-10">
                        <input class='btn btn-success' type="submit" value="Voltar" onclick="SetAcao('Voltar',document.frmHistorico)">
                    </div>
                </div>    
            </form>
        </div>
    </body>
</html>
