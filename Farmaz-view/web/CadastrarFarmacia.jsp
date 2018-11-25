<%-- 
    Document   : TelaCadastroFarmacia
    Created on : 23/07/2018, 19:10:05
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.model.dominio.Estado"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterEstadoProxy"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterCidadeProxy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Farmaz</title>

    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Main CSS-->
    <link href="css/main.css" rel="stylesheet" media="all">
    </head>
    <body style="margin-top: 0px;">
        <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-9 col-lg-offset-2">
                        <h1>Seja um de nossos parceiros,</h1>
                        <h2>Cadastre sua farmácia</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
        </div>

        <div class="container">     
            <form action="ServletWeb" method="post" class="form-horizontal">
                <div>
                    <h2>Dados da Farmácia</h2>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome da farmácia</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="nome" id="nome" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="email">Cadastro da prefeitura</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cadastro_prefeitura" id="cadastro_prefeitura"  required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="email">CNPJ</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cnpj" id="cnpj"  required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="documento">Email</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="email" name="email" id="email"  required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="telefone">Senha</label>
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
                        <a href="/Farmaz-view/ServletWeb?acao=Voltar">
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
    

    <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Main JS-->
    <script src="js/global.js"></script>
    </body>
</html>
