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
        <link href="images/Farmaz_PILULA.png" rel="icon">

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
        <% ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();%>
        <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h1>Gerencie seus produtos,</h1>
                        <h2><%=manterFarmacia.getFarmaciaById(((String) request.getSession().getAttribute("cod_farmacia"))).getNome()%></h2>
                    </div>
                </div>
                <!-- row -->
            </div>
        </div>
        
        <div class="container">
             <div class="form-group">
                <div class="col-lg-10 col-lg-offset-1" style="padding: 40px;">
                    <input class='btn btn-primary btn-block' type="submit" value="Novo Produto" onclick="location.href='InserirProduto.jsp'">
                </div>
            </div>

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
                        <th>
                            Nome
                        </th>
                        <th>
                            Receita
                        </th>
                        <th>
                            Descricao
                        </th>
                        <th>
                            Laboratorio
                        </th>
                        <th>
                            Cadastro da Anvisa
                        </th>
                        <th>
                            Estoque
                        </th>
                        <th>
                            Preço
                        </th>
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
                    <br><br><br><br><br>
                <div>
                    <h2>Voce ainda não cadastrou nenhum produto!</h2>
                </div>

                <%}
                %>
            </form>
        </div>              
    </body>
</html>
