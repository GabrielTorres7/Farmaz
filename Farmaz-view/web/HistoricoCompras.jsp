<%-- 
    Document   : HistoricoCompras
    Created on : 02/09/2018, 12:50:48
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.model.dominio.ItemPedido"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterProdutoProxy"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterItemPedidoProxy"%>
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
         <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h1>Você comprou todos esses produtos,</h1>
                        <h2>Veja aqui no Histórico de compras</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
        </div>  
         <div class="container" style="padding:40px;">   
         
            <form name="frmHistorico" method='post' action='ServletWeb' class="form-horizontal">
                <input type='hidden' name='table' value='Historico'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <table class="table table-striped"> 
                    <tr>
                        <th>
                            Farmácia
                        </th>
                        <th>
                            Data
                        </th>
                        <th>
                            Satus do pedido
                        </th>
                        <th>
                            Valor
                        </th>
                        <th>
                            &nbsp
                        </th>
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
                                    <a data-toggle="modal" data-target="#itensModal<%=pedido.getPedidoId()%>" href="#itensModal<%=pedido.getPedidoId()%>">Itens</a>
                                </td> 
      
                            </tr>
                            <% }

                            %>
                    </table>
                            
                <div class="form-group">
                    <div class="col-sm-10">
                        <input class='btn btn-primary btn-block' style="width:100px;"type="submit" value="Voltar" onclick="SetAcao('Voltar',document.frmHistorico)">
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
        <%}%>
    </body>
</html>
