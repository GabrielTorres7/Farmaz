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
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <style>
            body{
                font-family: 'Raleway', sans-serif;
            }
        </style>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farmaz</title>
    </head>
    <body>
        <div class="container">
            <div class = "col-sm-11 col-sm-offset-5">
                <img src="images/Farmaz_PILULA.png" class="img-responsive" width="15%" >
            </div>
            
            <center>
                <h2>Formulário de cadastro de produto</h2>
            </center>
         
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
                        <input class="form-control" type="radio" name="receita" value="sim">Sim<br>
                        <input class="form-control" type="radio" name="receita" value="nao">Não
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

