<%-- 
    Document   : EditarProduto
    Created on : 21/11/2018, 15:20:28
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.model.dominio.Disponibilidade"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Produto"%>
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
                   
            <center>
                <h2>Editar produto</h2>
            </center>
            <%
                Produto produto = (Produto)request.getAttribute("produto");
                Disponibilidade disponibilidade = (Disponibilidade)request.getAttribute("disponibilidade");%>
                
            <form action="ServletWeb" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome do produto</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="nome" id="nome" value="<%=produto.getNome()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="receita">Possui receita?</label>
                    <div class="col-xs-9">
                        <% if(produto.isReceita()){ %>
                        <input class="form-control" type="radio" name="receita" value="sim" checked>Sim<br>
                            <input class="form-control" type="radio" name="receita" value="nao">Não
                        <%} else{ %>
                            <input class="form-control" type="radio" name="receita" value="sim">Sim<br>
                            <input class="form-control" type="radio" name="receita" value="nao" checked>Não
                        <%} %>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="telefone">Descrição</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="descricao" id="descricao" value="<%=produto.getDescricao()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">Laboratorio</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="laboratorio" id="laboratorio" value="<%=produto.getLaboratorio()%>">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senha">Cadastro da Anvisa</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cadastro" id="cadastro" value="<%=produto.getCadastroAnvisa()%>">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="estoque">Estoque</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="estoque" id="estoque" value="<%=disponibilidade.getEstoque()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="preco">Preço</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="preco" id="preco" value="<%=disponibilidade.getPreco()%>">
                    </div>
                </div>
                
                <input type="hidden" name="acao" id="acao" value="InserirProduto">
                <%
                    request.getSession().setAttribute("ultima", "editar");
                    request.getSession().setAttribute("codDisponibilidade", disponibilidade.getId()); %>

                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <input class='btn btn-primary btn-block' type="submit" name="botao" id="botao" value="Confirmar Alterações">
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
