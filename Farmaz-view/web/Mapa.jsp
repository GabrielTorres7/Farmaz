<%-- 
    Document   : Mapa
    Created on : 25/07/2018, 22:44:25
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page ="MenuCliente.jsp"/>
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
        
        <script type="text/javascript" language="JavaScript" src="js/script.js"></script>

        <!-- =======================================================
          Template Name: Spot
          Template URL: https://templatemag.com/spot-bootstrap-freelance-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->
        
        <script src="http://maps.google.com/maps/api/js?key=AIzaSyBpCmWwIrHcvRWQ6loUb5NLB9_EQaEHzUo"></script>
    </head>
    <body>
        <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h1>Estamos quase acabando, escolha a</h1>
                        <h2>quantidade de produtos e se deseja comprar algo mais</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
        </div>
        <div class="container">
            <form name="frmMapa" action="ServletWeb" method="Post" class="form-horizontal">
                <input type="hidden" name="ultimoJsp" value="Mapa">
                <input type='hidden' name='acao' value=''>
                
                <div class="form-group" style="padding-top: 40px;">
                    <label class="col-lg-1 control-label" for="quantidadeProduto">Quantidade</label>
                    <div class="col-lg-10 ">
                        <input class="form-control" type="number" id="quantidadeProduto" name="quantidadeProduto" required>
                    </div>
                </div>
                
                <div class="form-group" style="padding: 10px 0 0; clear: both">
                    <div class="col-lg-10 col-lg-offset-1 embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item"
                            id="map"
                            width="942"
                            height="450"
                            frameborder="0" style="border:0"
                            src="<%="https://www.google.com/maps/embed/v1/directions?key=AIzaSyBpCmWwIrHcvRWQ6loUb5NLB9_EQaEHzUo&origin=" + request.getAttribute("enderecoFarmacia")+"&destination="+request.getAttribute("enderecoCliente")+"&avoid=tolls&mode=driving"%>" allowfullscreen>
                        </iframe>
                    </div>
                </div>
                <input type="hidden" id="disponibilidadeId" name="disponibilidadeId" value="<%=request.getAttribute("disponibilidadeId")%>">
                
                 <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class='btn btn-sucess btn-block' style=" " type="submit" value="Adicionar ao Meu Carrinho" onclick="SetAcao('Adicionar ao Meu Carrinho',document.frmMapa)">
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class='btn btn-primary btn-block' type="submit" value="Finalizar Compra" onclick="SetAcao('FinalizarCompra',document.frmMapa)">
                    </div>
                </div>
                            
               
            </form>
        </div>
    </body>
</html>
