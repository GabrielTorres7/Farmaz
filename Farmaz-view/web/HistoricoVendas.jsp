<%-- 
    Document   : HistoricoVendas
    Created on : 25/11/2018, 11:11:53
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterProdutoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.ItemPedido"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterItemPedidoProxy"%>
<%@page import="java.util.Collections"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Cliente"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterClienteProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page ="MenuFarmacia.jsp"/>
    <head>
        <meta charset="utf-8">
        <title>Farmaz</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicons -->
        <link href="images/icone.png" rel="icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">

        <!-- Bootstrap CSS File -->
        <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Libraries CSS Files -->
        <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <!-- Main Stylesheet File -->
        <link href="css/style.css" rel="stylesheet">
        
        <script type="text/javascript" language="JavaScript" src="js/script.js"></script>

        <!-- =======================================================
          Template Name: Spot
          Template URL: https://templatemag.com/spot-bootstrap-freelance-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->
    </head>
    <body>
         <div class="container">   
            <center>
                <h3>Histórico de Pedidos Concluidos</h3>
            </center>
            
            <form name="frmHistorico" method='post' action='ServletWeb' class="form-horizontal">
                <input type='hidden' name='table' value='Historico'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <% List<Pedido> historico = (List<Pedido>) request.getAttribute("Historico");
                    if(historico != null){ %>
                <table class="table table-striped"> 
                    <tr>
                        <td>
                            Cliente
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
                        
                        ManterClienteProxy manterCliente = new ManterClienteProxy();
                        Cliente cliente = new Cliente();
                        String status = " ";
                        Collections.reverse(historico);
                        
                        for (Pedido pedido: historico) {
                            cliente = manterCliente.getClienteById(pedido.getClienteId());
                   
                            if(pedido.getIdtStatus() == 'C'){
                                status = "Concluído";
                            }
                    %>
                            <tr>
                                <td>
                                    <%=cliente.getNome()%>
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
                                    <a data-toggle="modal" data-target="#itensModal<%=pedido.getPedidoId()%>" href="#itensModal<%=pedido.getPedidoId()%>">Itens</a>
                                </td> 
      
                            </tr>
                            <% }

                            %>
                    </table>
                            
                <div class="form-group">
                    <div class="col-sm-10">
                        <input class='btn btn-success' type="submit" value="Voltar" onclick="SetAcao('ListarProdutosFarmacia',document.frmHistorico)">
                    </div>
                </div>    
            </form>
        </div>
                   
        <% for(Pedido pedido: historico){%>
            <div class="modal fade" id="itensModal<%=pedido.getPedidoId()%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel" style="text-align: center">Itens do pedido</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row centered">
                                <table class="table table-striped"> 
                                <tr>
                                    <td>
                                        Produto
                                    </td>
                                    <td>
                                        Quantidade
                                    </td>
                                </tr>
                                <%
                                    ManterItemPedidoProxy manterItemPedido = new ManterItemPedidoProxy();
                                    ManterProdutoProxy manterProduto = new ManterProdutoProxy();
                                    ItemPedido itemPedido = new ItemPedido();
                                    List<ItemPedido> listItens = manterItemPedido.getItensPedidoByPedidoId(pedido.getPedidoId());
                                    for(int i=0; i<listItens.size(); i++){
                                        itemPedido = listItens.get(i);
                                        %>
                                        <tr>
                                            <td>
                                                <%=manterProduto.getProdutoById(itemPedido.getProdutoId()).getNome()%>
                                            </td>
                                            <td>
                                                <%=itemPedido.getQuantidade()%>
                                            </td>
                                        </tr>
                                        <% }

                                        %>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
            <!-- /.modal-dialog -->
            </div>
            <%} 
        } else{%>
        <div class="alert">
            <h2>Voçê ainda não finalizou nenhum pedido!</h2>
        </div>
        <%} %>
    </body>
</html>
