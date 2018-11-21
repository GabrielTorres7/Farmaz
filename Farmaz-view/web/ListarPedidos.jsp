<%-- 
    Document   : ListarPedidos
    Created on : 21/11/2018, 16:54:34
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.model.dominio.Endereco"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterEnderecoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Cliente"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterClienteProxy"%>
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
                <h3>Gerenciar Pedidos</h3>
            </center>
            <form name="frmPedidos" method='post' action='ServletWeb' class="form-horizontal">
                <input type='hidden' name='table' value='Pedidos'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
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
                        
                        for (Pedido pedido: listPedido) {
                            cliente = manterCliente.getClienteById(pedido.getClienteId());
                            endereco = manterEndereco.getEnderecosByClienteId(cliente.getId()).get(0);
                            if(pedido.getIdtStatus() == 'F'){
                                status = "Realizado";
                            }else if(pedido.getIdtStatus() == 'P'){
                                status = "Preparando envio";
                            }else if(pedido.getIdtStatus() == 'A'){
                                status = "A caminho";
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
                                    <%=endereco.getRua()%>
                                </td>
                                <td>
                                    <%=endereco.getNumero()%>
                                </td>
                                <td>
                                    <%=endereco.getBairro()%>
                                </td>
                                <td>
                                    <a href="/Farmaz-view/ServletWeb?acao=ItensPedido&Pedido=<%=pedido.getPedidoId()%>">Itens</a>
                                </td> 
      
                            </tr>
                            <% }

                            %>
                    </table>
                    <% }else { %>
                    <div class="alert">
                        <h2>Nenhum pedido em andamento.</h2>
                    </div>
                    <% } %>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input class='btn btn-success' type="submit" value="Voltar" onclick="SetAcao('ListarProdutosFarmacia',document.frmPedidos)">
                    </div>
                </div>    
            </form>
        </div>
    </body>
</html>
