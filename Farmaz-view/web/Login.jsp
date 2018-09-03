<%-- 
    Document   : Login
    Created on : 17/07/2018, 23:06:42
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <style>
            body{
                font-family: 'Raleway', sans-serif;
            }
        </style>

        
        <title>Farmaz</title>
    </head>
    <body>
        <div class="container">
             <div class = "col-sm-11 col-sm-offset-4">
                <img src="images/Farmaz_SLOGAN.png" class="img-responsive" width="38%" >
                <br>
            </div>
            
            <form action="ServletWeb" method="post" class="form-horizontal">                  
                <div class="form-group has-feedback">
                    <label class="col-sm-2 control-label" for="email">Email</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" id="email" name="email" placeholder="Seu email" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senha">Senha</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" id="senha" name="senha" placeholder="Sua senha" required>
                    </div>
                </div>

                <input type="hidden" id="acao" name="acao" value="Login">
                
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-6">
                        <input class='btn btn-primary' type="submit" id="botao" name="botao">
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-2">
                        <p class="text-center">Novo usuário?<a href="CadastrarCliente.jsp"> Registre-se aqui</a></p>
                        <p class="text-center">Quer ser nosso parceiro?<a href="CadastrarFarmacia.jsp"> Cadastre sua farmácia aqui</a></p>
                    </div>
                </div>
            </form>      
        </div>
    </body>
</html>
