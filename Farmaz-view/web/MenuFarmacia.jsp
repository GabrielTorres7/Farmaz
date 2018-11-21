<%-- 
    Document   : MenuFarmacia
    Created on : 19/11/2018, 21:43:52
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farmaz</title>
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta name="viewport" content="width=devide-width, initial-scale=1">
        
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <section class="container">
                <div class="navbar-header">
                    <button type="button" 
                        class="navbar-toggle collapsed"
                        data-toggle="collapse"
                        data-target="#movelMenu"
                        aria-expanded="false">
                        
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    
                    <a href="/Farmaz-view/ServletWeb?acao=ListarProdutosFarmacia" class="navbar-brand">
                        <img src="images/Farmaz_PILULA.png" height="38px" width="60px" >
                    </a>
                </div>
                
                <div class="collapse navbar-collapse pull-right" id="movelMenu">
                    <ul class="nav navbar-nav">
                        <li><a href="/Farmaz-view/ServletWeb?acao=ListarFaq">Ajuda</a></li>
                        <li><a href="#">Perfil</a></li>
                        <li><a href="/Farmaz-view/ServletWeb?acao=ListarPedidos">Pedidos</a></li>
                        <li><a href="#">Histórico de vendas</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/Farmaz-view/ServletWeb?acao=Sair">Sair</a></li>
                    </ul>
                </div>
            </section>
        </nav>
        
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
