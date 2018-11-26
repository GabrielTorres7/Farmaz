<%-- 
    Document   : ListarProdutosFarmacia
    Created on : 20/11/2018, 19:48:39
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterFarmaciaProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Farmacia"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterProdutoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Disponibilidade"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Produto"%>
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

        <!-- =======================================================
          Template Name: Spot
          Template URL: https://templatemag.com/spot-bootstrap-freelance-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->
    </head>
    <body>
        <div class="container">   
            <% ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();%>
            <h3>Lista de Produtos - <%=manterFarmacia.getFarmaciaById(((String) request.getSession().getAttribute("cod_farmacia"))).getNome()%></h3>

            <a href='InserirProduto.jsp'>Novo Produto</a>

            <form name="frmProduto" method='post' action='/ServletWeb'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <input type='hidden' name='table' value='Produto'>
                <%ManterProdutoProxy manterProduto;
                    Produto produto;
                    List<Disponibilidade> listProduto = (List<Disponibilidade>) request.getAttribute("produtos");
                    if (listProduto != null) { %>
                <table class="table table-striped"> 
                    <tr class="info">
                        <td>
                            Nome
                        </td>
                        <td>
                            Receita
                        </td>
                        <td>
                            Descricao
                        </td>
                        <td>
                            Laboratorio
                        </td>
                        <td>
                            Cadastro da Anvisa
                        </td>
                        <td>
                            Estoque
                        </td>
                        <td>
                            Preço
                        </td>
                    </tr>
                    <%

                        for (Disponibilidade disponibilidade : listProduto) {
                            manterProduto = new ManterProdutoProxy();
                            produto = manterProduto.getProdutoById(disponibilidade.getProdutoSeq());
                    %>
                    <tr>
                        <td>
                            <%=produto.getNome()%>
                        </td>
                        <td>
                            <% if (produto.isReceita() == true) {
                                    out.print("Possui");
                                } else {
                                    out.print("Não possui");
                                }
                            %>
                        </td>
                        <td>
                            <%=produto.getDescricao()%>
                        </td>
                        <td>
                            <%=produto.getLaboratorio()%>
                        </td>
                        <td>
                            <%=produto.getCadastroAnvisa()%>
                        </td>
                        <td>
                            <%=disponibilidade.getEstoque()%>
                        </td>
                        <td>
                            <%=disponibilidade.getPreco()%>
                        </td>
                        <td>
                            <a href="/Farmaz-view/ServletWeb?acao=EditarProduto&CodProduto=<%=disponibilidade.getId()%>">
                                <i class="glyphicon glyphicon-edit"></i>
                            </a>
                        </td>
                        <td>
                            <a href="/Farmaz-view/ServletWeb?acao=ExcluirProduto&CodProduto=<%=disponibilidade.getId()%>">
                                <i class="glyphicon glyphicon-remove"></i>
                            </a>
                        </td>
                    </tr>                    
                    <%  } %>
                </table>
                <%
                    } else {%>   
                <div>
                    <h2>Voce ainda não cadastrou nenhum produto!</h2>
                </div>

                <%}
                %>
            </form>
        </div>              
    </body>
</html>
