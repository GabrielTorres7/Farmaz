<%-- 
    Document   : MeuCarrinho
    Created on : 19/08/2018, 13:50:08
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterFarmaciaProxy"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterProdutoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Farmacia"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Produto"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Disponibilidade"%>
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
        <link href="images/Farmaz_PILULA.png" rel="icon">

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
                        <h1>Todos os produtos selecionados aparecem aqui, no</h1>
                        <h2>Carrinho de compras</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
            </div>
        <div class="container">              
            <form name="frmCarrinho" method='post' action='ServletWeb' class="form-horizontal" style="padding:40px">
                <input type='hidden' name='table' value='MeuCarrinho'>
                <input type="hidden" name="ultimoJsp" value="Carrinho">
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <%
                    List<Disponibilidade> carrinho = (List<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
                    if(carrinho != null) {
                    %>
                <table class="table table-striped"> 
                    <tr>
                        <th>
                            Nome
                        </th>
                        <th>
                            Farmácia
                        </th>
                        <th>
                            Quantidade
                        </th>
                        <th>
                            Preço
                        </th>
                        <th>
                            &nbsp
                        </th>
                    </tr>
                    <%
                        Double total = 0.0;
                        Produto produto;
                        Farmacia farmacia;
                        String nomeProduto;
                        String nomeFarmacia;
                        ManterProdutoProxy manterProduto = new ManterProdutoProxy();
                        ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
                        
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
                                   <a href="/Farmaz-view/ServletWeb?acao=ExcluirProdutoCarrinho&cod=<%=item.getId()%>">
                                        <i class="glyphicon glyphicon-remove"></i>
                                   </a>
                                </td> 
      
                            </tr>
                            <%      request.getSession().setAttribute("Total", total);
                        }
                    %>
                </table>
                <div class="form-group">
                    <div class="col-sm-4">
                        <h3>Valor total: <b> <%=total%> </b></h3>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1" style="padding: 40px;">
                        <input class='btn btn-primary btn-block' type="submit" value="Finalizar Compra" onclick="SetAcao('FinalizarCompra',document.frmCarrinho)">
                    </div>
                </div>
                <% }else{%>
                <div class="col-lg-9 col-lg-offset-2">
                    <h2>Você ainda não adicionou nenhum produto ao carrinho!</h2>
                </div>
                    <% } %>              
            </form>
        </div>
    </body>
</html>
