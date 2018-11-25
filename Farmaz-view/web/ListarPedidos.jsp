<%-- 
    Document   : ListarPedidos
    Created on : 21/11/2018, 16:54:34
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterProdutoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.ItemPedido"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterItemPedidoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Endereco"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterEnderecoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Cliente"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterClienteProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page ="MenuFarmacia.jsp"/>
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
                <h3>Gerenciar Pedidos</h3>
            </center>
            <form name="frmPedidos" method='post' action='ServletWeb' class="form-horizontal">
                <input type='hidden' name='table' value='Pedidos'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <input type='hidden' name='status' value=''>
                <%  
                List<Pedido> listPedido = (List<Pedido>) request.getAttribute("pedidos");
                if(listPedido != null){ %>
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
                            Troco
                        </td>
                        <td>
                            Rua
                        </td>
                        <td>
                            Número
                        </td>
                        <td>
                            Bairro
                        </td>
                        <td>
                            &nbsp
                        </td>
                    </tr>
                    <%
                        ManterClienteProxy manterCliente = new ManterClienteProxy();
                        ManterEnderecoProxy manterEndereco = new ManterEnderecoProxy();
                        Cliente cliente = new Cliente();
                        Endereco endereco = new Endereco();
                        String status = " ";
                        String pagamento = "";
                        
                        for (Pedido pedido: listPedido) {
                            cliente = manterCliente.getClienteById(pedido.getClienteId());
                            endereco = manterEndereco.getEnderecosByClienteId(cliente.getId()).get(0);
   
                            if(pedido.getTroco() == 0){
                                pagamento = "Cartão";
                            }else{
                                pagamento = Integer.toString(pedido.getTroco());
                            }
                    %>
                            <tr>
                                <td>
                                    <%=cliente.getNome()%>
                                </td>
                                <td>
                                    <%=pedido.getDataHora()%>
                                </td>
                                <td><%
                                    if(pedido.getIdtStatus() == 'F'){ %>
                                    <select name="status" id="status" required>
                                            <option value="F" selected="selected">Realizado</option>
                                            <option value="P">Preparando envio</option>
                                            <option value="A">A caminho</option>
                                            <option value="C">Concluído</option>
                                        </select><%
                                    }else if(pedido.getIdtStatus() == 'P'){ %>
                                        <select name="status" id="status" required>
                                            <option value="F">Realizado</option>
                                            <option value="P" selected="selected">Preparando envio</option>
                                            <option value="A">A caminho</option>
                                            <option value="C">Concluído</option>
                                        </select><%
                                    }else if(pedido.getIdtStatus() == 'A'){ %>
                                        <select name="status" id="status" required>
                                            <option value="F">Realizado</option>
                                            <option value="P">Preparando envio</option>
                                            <option value="A" selected="selected">A caminho</option>
                                            <option value="C">Concluído</option>
                                        </select>
                                   <% }%>
                                </td>
                                <td>
                                    <%=pedido.getValor()%>
                                </td>
                                <td>
                                    <%=pagamento%>
                                </td>
                                <td>
                                    <%=endereco.getRua()%>
                                </td>
                                <td>
                                    <%=endereco.getNumero()%>
                                </td>
                                <td>
                                    <%=endereco.getBairro()%>
                                </td>
                                <td>
                                    <a data-toggle="modal" data-target="#itensModal<%=pedido.getPedidoId()%>" href="#itensModal<%=pedido.getPedidoId()%>">Itens</a>
                                </td> 
                                <td>
                                    <a href="/Farmaz-view/ServletWeb?acao=AlterarStatusPedido&cod=<%=pedido.getPedidoId()%>">
                                        <i class="glyphicon glyphicon-check"></i>
                                    </a>
                                </td>
                            </tr>
                            <% }

                            %>
                    </table>
                    
                <div class="form-group">
                    <div class="col-sm-10">
                        <input class='btn btn-success' type="submit" value="Voltar" onclick="SetAcao('ListarProdutosFarmacia',document.frmPedidos)">
                    </div>
                </div>    
            </form>
        </div>
        
        <% for(Pedido pedido: listPedido){%>
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
        <% }else { %>
        <div class="alert">
            <h2>Nenhum pedido em andamento.</h2>
        </div>
        <% } %>
    </body>
</html>
