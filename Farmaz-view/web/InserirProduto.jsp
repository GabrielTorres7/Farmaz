<%-- 
    Document   : InserirProduto
    Created on : 20/11/2018, 21:19:36
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterEstadoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Estado"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.service.ManterEstado"%>
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
        <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h2>Cadastrar produto</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
        </div>
        
        <div class="container" style="padding:40px;">
            
            <form action="ServletWeb" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome do produto</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="nome" id="nome" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="receita">Possui receita?</label>
                    <div class="col-xs-9">    
                        <input class="radio-inline" type="radio" name="receita" value="sim">Sim<br>
                        <input class="radio-inline" type="radio" name="receita" value="nao">Não
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="telefone">Descrição</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="descricao" id="descricao" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">Laboratorio</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="laboratorio" id="laboratorio">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senha">Cadastro da Anvisa</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cadastro" id="cadastro">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="estoque">Estoque</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="estoque" id="estoque" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="preco">Preço</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="preco" id="preco">
                    </div>
                </div>
                
                <input type="hidden" name="acao" id="acao" value="InserirProduto">
                <%
                    request.getSession().setAttribute("ultima", "inserir");%>

                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <input class='btn btn-primary btn-block' type="submit" name="botao" id="botao" value="Inserir Produto">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <a href="/Farmaz-view/ServletWeb?acao=ListarProdutosFarmacia">
                            <input class='btn btn-primary btn-block' type="button" value="Cancelar">
                        </a>
                    </div>
                </div>
                
            </form>
        </div>
    </body>
</html>

