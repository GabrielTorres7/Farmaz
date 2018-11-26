<%-- 
    Document   : TelaCadastro
    Created on : 22/07/2018, 17:20:08
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterCidadeProxy"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterEstadoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Estado"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.service.ManterEstado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <body style="margin-top: 0px;">
        <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-9 col-lg-offset-2">
                        <h1>Seja um de nossos membros,</h1>
                        <h2>Cadastre-se aqui</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
        </div>

        <div class="container">     
            <form action="ServletWeb" method="post" class="form-horizontal">
                <div>
                    <h2>Dados Pessoais</h2>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="nome" id="nome" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="email">Email</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="email" id="email"  required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="documento">Documento de identificação</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="documento" id="documento"  required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="telefone">Telefone</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="telefone" id="telefone" required>
                    </div>
                </div>
                
                    <div class="form-group">
                    <label class="col-sm-2 control-label" for="senhaAtual">Senhal</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" name="senha" id="senha" required>
                    </div>
                </div>
                
                <div style="padding-top:5px;">
                    <h2>Endereço</h2>
                </div>
                    
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="cep">CEP</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="cep" id="cep" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="rua">Rua</label>
                    
                    <div class="col-xs-5">
                        <input class="form-control" type="text" name="rua" id="rua" required>
                    </div>
                    
                    <label class="col-sm-2 control-label" for="numero">Número</label>
                    <div class="col-xs-2">
                        <input class="form-control" type="number" name="numero" id="numero" size="5" required>
                    </div>
                </div>
                
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Bairro</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="bairro" id="bairro" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Cidade</label>
                    <div class="col-xs-9">
                        <% ManterCidadeProxy manterCidade = new ManterCidadeProxy(); %>
                        <input class="form-control" type="text" name="cidade" id="cidade" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="estado">Estado</label>
                    <div class="col-xs-9">
                        <% ManterEstadoProxy mantemEstado = new ManterEstadoProxy(); %>
                        <select class="form-control" name="estado" id="estado" required>
                            <%
                                List<Estado> listEstado = (List<Estado>) mantemEstado.getAll();
                                for (Estado estado: listEstado) {
                            %>
                            <option value="<%=estado.getSigla()%>"> <%=estado.getNome()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                        
                <input type="hidden" name="acao" id="acao" value="CadastraCliente">

                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2" style="padding-top: 10px;">
                        <input class='btn btn-primary btn-block'  type="submit" name="botao" id="botao" value="Cadastrar">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2" style="padding-top: 5px;">
                        <a href="Home.jsp">
                            <input class='btn btn-primary btn-block'  type="button" value="Cancelar">
                        </a>
                    </div>
                </div>
        </div>
        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Main JS-->
        <script src="js/global.js"></script>
        
        <jsp:include page ="Rodape.jsp"/>
    </body>
</html>
