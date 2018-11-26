<%-- 
    Document   : FinalizarCompra
    Created on : 01/09/2018, 21:44:15
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
        <link href="images/icone.png" rel="icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">

        <!-- Bootstrap CSS File -->
        <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Libraries CSS Files -->
        <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <!-- Main Stylesheet File -->
        <link href="css/style.css" rel="stylesheet">

        <script type="text/javascript" language="JavaScript" src="js/script.js"></script>
        
        <!-- gambs do torres batata -->
        <script>
            function mostraCampo() {
                var inputTroco = document.getElementById('trocoDiv');
                var submit = document.getElementById('acao');

                inputTroco.style.display = "block";
                submit.style.display = "block";
            }
        </script>
        <!-- =======================================================
          Template Name: Spot
          Template URL: https://templatemag.com/spot-bootstrap-freelance-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->
    </head>
    <body>
        <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h1>Para finalizar sua compra,</h1>
                        <h2>Escolha o método de pagamento</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
        </div>    
        <div class="container">
            <div class="row centered" >

                <div class="col-lg-10 col-lg-offset-1" style="padding-bottom:60px;padding-top:40px;">
                    <h2>
                        Total da compra : <b><%=request.getSession().getAttribute("Total")%> reais</b>
                    </h2>
                </div>
            </div>        
            <form action="ServletWeb" method="post" class="form-horizontal">

                <div class="row centered">
                    <div class="col-lg-6">
                        <a onclick="mostraCampo()" href="#" id="dinheiro">
                            <img onclick="" title="Dinheiro" class="image-wrapper" src="images/dinheiro.png" class="img-responsive" >
                        </a>
                    </div>
                    <div class="col-lg-6">
                        <a href="#">
                            <img title="Cartão" class="image-wrapper" src="images/cartao.png" class="img-responsive"  >
                        </a>
                    </div>
                </div>  

                <div style="display: block; padding-top: 60px;" class="form-group" id="trocoDiv">
                    <label class="col-sm-2 control-label" for="troco">Troco para</label>
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" type="text" name="troco" id="troco" required>
                    </div>
                </div>

                <div class="form-group" style="padding-top: 20px;">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input style="display: none" class='btn btn-primary btn-block' type="submit" id="acao" name="acao" value="Fazer Pedido">
                    </div>
                </div>
            </form>
        </div>
        <!-- FOOTER -->
        <jsp:include page ="Rodape.jsp"/> 
    </body>
</html>
