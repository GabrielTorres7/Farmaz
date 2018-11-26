<%-- 
    Document   : listaProdutos
    Created on : 21/07/2018, 22:22:46
    Author     : Gabriel
--%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Produto"%>
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

        <!-- =======================================================
          Template Name: Spot
          Template URL: https://templatemag.com/spot-bootstrap-freelance-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->

    </head>
    <body>
        <!-- PRICING SECTION -->
        <div id="dg">
            <div class="container">

                
                    <h1><b>Selecione o produto</b>,</h1>
                    <h2>que nós procuraremos as melhores ofertas próximas de você</h2>
                    <br>
                    <div class="row centered" style="display: -webkit-  flex; display: -ms-flexbox; display: flex; overflow: hidden;">
                    <form name="frmProduto" method='post' action='/ServletWeb'>
                        <!-- GAMBS DO TORRES -->
                        <input type='hidden' name='acao' value=''>
                        <input type='hidden' name='cod' value=''>
                        <input type='hidden' name='table' value='Produto'>


                        <%
                            List<Produto> listProduto = (List<Produto>) request.getAttribute("produtos");
                            for (Produto produto : listProduto) {
                        %>
                        <div class="col-lg-4" style="flex: 1;">
                            <!-- START PRICING TABLE -->
                            <div class="pricing-option">
                                <div class="pricing-top" style="width: 360px; height: 220px">
                                    <span class="pricing-edition">
                                        <a href="/Farmaz-view/ServletWeb?acao=ListarFarmaciasComProduto&CodProduto=<%=produto.getId()%>"> <%=produto.getNome()%></a></span>
                                    </span>
                                    <span class="price">
                                        <%=produto.getDescricao()%>                                        

                                        <br><br>    
                                        <small>
                                            <% if (produto.isReceita() == true) {
                                                    out.print("Necessário receita médica");
                                                } else {
                                                    out.print("");
                                                }
                                            %>
                                        </small>
                                    </span>
                                </div>
                                <ul>
                                    <li> Cadastro da ANVISA: <%=produto.getCadastroAnvisa()%> </li>
                                    <li> Laboratório: <%=produto.getLaboratorio()%> </li>
                                </ul>
                                <a href="/Farmaz-view/ServletWeb?acao=ListarFarmaciasComProduto&CodProduto=<%=produto.getId()%>" class="pricing-signup" >Procurar Farmácia</a>
                            </div>

                            <!-- /pricing-option -->
                            <!-- END PRICING TABLE -->
                        </div><%  }%><!-- fecha for -->
                    </form>    
                    <!-- /col -->
                </div> 
            </div>  
        </div>            
        <!-- FOOTER -->
        <jsp:include page ="Rodape.jsp"/> 
    </body>
</html>
