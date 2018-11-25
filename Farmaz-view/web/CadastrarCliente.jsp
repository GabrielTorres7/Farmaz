<%-- 
    Document   : TelaCadastro
    Created on : 22/07/2018, 17:20:08
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterEstadoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Estado"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.service.ManterEstado"%>
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

            <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
        <!-- Main CSS-->
        <link href="css/main.css" rel="stylesheet" media="all">
        <style>
            body{
                font-family: 'Raleway', sans-serif;
            }
        </style>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farmaz</title>
    </head>
    <body>
        <div class="page-wrapper bg-dark p-t-100 p-b-50">
        <div class="wrapper wrapper--w900">
            <div class="card card-6">
                <div class="card-heading">
                    <h2 class="title">Formulário de cadastro</h2>
                </div>
                <div class="card-body">
                    <form action="ServletWeb" method="post" >
                        <div class="form-row">
                            <div class="name">Nome completo</div>
                            <div class="value">
                                <input class="input--style-6" type="text" name="nome" id="nome" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Documento de identificação</div>
                            <div class="value">
                                <input class="input--style-6" type="text" name="documento_identificacao" id="cnpj" required
                                       pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
                                       title="Digite o CPF no formato: nnn.nnn.nnn-nn">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="email" name="email" placeholder="exemplo@email.com">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Senha</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="password" name="senha" id="senha" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">CEP</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="cep" id="cep" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Rua</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="rua" id="rua" required>
                                </div>
                            </div>
                            <div class="name">Número</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="number" name="numero" id="numero" size="5" style="max-width:100px;" required>
                                </div>
                            </div>
                        </div>
                         <div class="form-row">
                            <div class="name">Bairro</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="bairro" id="bairro" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Cidade</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="cidade" id="cidade" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Estado</div>
                            <div class="value">
                                <select class="form-control" name="estado" id="estado" required>
                                    <option value="AC">Acre</option>
                                    <option value="MG">Minas Gerais</option>
                                    <option value="SP">São Paulo</option>
                                    <option value="RJ">Rio de Janeiro</option>
                                    <option value="BA">Bahia</option>
                                    <option value="RS">Rio Grande do Sul</option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="acao" id="acao" value="CadastraCliente">
                        <div class="card-footer">
                            <input class="btn btn--radius-2 btn--blue-2" type="submit" name="botao" value="Finalizar cadastro" id="botao">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Main JS-->
    <script src="js/global.js"></script>
    </body>
</html>
