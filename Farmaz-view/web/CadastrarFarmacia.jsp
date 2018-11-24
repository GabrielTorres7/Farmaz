<%-- 
    Document   : TelaCadastroFarmacia
    Created on : 23/07/2018, 19:10:05
    Author     : Gabriel
--%>

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
                            <div class="name">Nome da farmácia</div>
                            <div class="value">
                                <input class="input--style-6" type="text" name="nome" id="nome" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Cadastro da prefeitura</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="cadastro_prefeitura" id="nome" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">CNPJ</div>
                            <div class="value">
                                <input class="input--style-6" type="text" name="cnpj" id="cnpj" required
                                       pattern="[0-9]{2}.[0-9]{3}.[0-9]{3}"
                                       title="Digite o CNPJ no formato: nn.nnn.nnn">
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
                        <input type="hidden" name="acao" id="acao" value="CadastraFarmacia">
                    </form>
                </div>
                <div class="card-footer">
                    <button class="btn btn--radius-2 btn--blue-2" type="submit" name="botao" id="botao">Finalizar cadastro</button>
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
