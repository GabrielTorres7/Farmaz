<%-- 
    Document   : MeuCarrinho
    Created on : 19/08/2018, 13:50:08
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.model.dominio.Farmacia"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Produto"%>
<%@page import="br.cefetmg.farmaz.model.serviceImpl.ManterFarmaciaImpl"%>
<%@page import="br.cefetmg.farmaz.model.daoImpl.FarmaciaDAOImpl"%>
<%@page import="br.cefetmg.farmaz.model.serviceImpl.ManterProdutoImpl"%>
<%@page import="br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Disponibilidade"%>
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
                <h3>Meu Carrinho</h3>
            </center>
            
            <form name="frmCarrinho" method='post' action='ServletWeb' class="form-horizontal">
                <input type='hidden' name='table' value='MeuCarrinho'>
                <input type="hidden" name="ultimoJsp" value="Carrinho">
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <table class="table table-striped"> 
                    <tr>
                        <td>
                            Nome
                        </td>
                        <td>
                            Farmácia
                        </td>
                        <td>
                            Quantidade
                        </td>
                        <td>
                            Preço
                        </td>
                        <td>
                            &nbsp
                        </td>
                    </tr>
                    <%
                        Double total = 0.0;
                        Produto produto;
                        Farmacia farmacia;
                        String nomeProduto;
                        String nomeFarmacia;
                        ManterProdutoImpl manterProduto = new ManterProdutoImpl(ProdutoDAOImpl.getInstance());
                        ManterFarmaciaImpl manterFarmacia = new ManterFarmaciaImpl(FarmaciaDAOImpl.getInstance());
                        List<Disponibilidade> carrinho = (List<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
                        
                        for (Disponibilidade item: carrinho) {
                            produto = manterProduto.getProdutoById(item.getProdutoSeq());
                            farmacia = manterFarmacia.getFarmaciaById(item.getFarmaciaCadastro());
                            nomeProduto = produto.getNome();
                            nomeFarmacia = farmacia.getNome();
                            total += item.getPreco();
                    %>
                            <tr>
                                <td>
                                    <%=nomeProduto%>
                                </td>
                                <td>
                                    <%=nomeFarmacia%>
                                </td>
                                <td>
                                    <%=item.getEstoque()%>
                                </td>
                                <td>
                                    <%=item.getPreco()%>
                                </td>
                                <td>
                                   <input type='button' value='Excluir' onclick='Excluir("<%=item.getId()%>",document.frmCarrinho)'>
                                </td> 
      
                            </tr>
                            <%      request.getSession().setAttribute("Total", total);
                        }
                    %>
                </table>
                <div class="form-group">
                    <div class="col-sm-4">
                        <p>Valor total: <%=total%></p>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-sm-10">
                        <input class='btn btn-primary' type="submit" value="Finalizar Compra" onclick="SetAcao('FinalizarCompra',document.frmCarrinho)">
                    </div>
                </div>
                            
                <div class="form-group">
                    <div class="col-sm-10">
                        <input class='btn btn-success' type="submit" value="Voltar" onclick="SetAcao('Voltar',document.frmCarrinho)">
                    </div>
                </div>    
            </form>
        </div>
    </body>
</html>
