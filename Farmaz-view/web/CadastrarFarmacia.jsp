<%-- 
    Document   : TelaCadastroFarmacia
    Created on : 23/07/2018, 19:10:05
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                <h2>Formulário de cadastro</h2>
            </center>
            
            <form action="ServletWeb" method="post" class="form-horizontal">

                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome da farmácia</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="nome" id="nome" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="cadastro_prefeitura">Cadastro da prefeitura</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cadastro_prefeitura" id="cadastro_prefeitura" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="cnpj">CNPJ</label>
                    <div class="col-xs-9">    
                        <input class="form-control" type="text" name="cnpj" id="cnpj" required
                            pattern="[0-9]{2}.[0-9]{3}.[0-9]{3}"
                            title="Digite o CNPJ no formato: nn.nnn.nnn">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">Email</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="email" name="email" id="email" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senha">Senha</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" name="senha" id="senha" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="cep">CEP</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cep" id="cep" required
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
                        <input class="form-control" type="text" name="cidade" id="cidade" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="estado">Estado</label>
                    <div class="col-xs-9">
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

                <div class="form-group">
                        <div class="col-xs-9 col-sm-offset-2">
                            <input class='btn btn-primary btn-block' type="submit" name="botao" id="botao" value="Finalizar Cadastro">
                        </div>
                </div>
            </form>   
        </div>    
    </body>
</html>
