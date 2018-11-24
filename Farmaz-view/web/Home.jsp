<%-- 
    Document   : Home
    Created on : 20/11/2018, 21:11:36
    Author     : User
--%>

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
        <link href="images/Farmaz_PILULA.png" rel="icon">

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

    <body>
        <div id="headerwrap">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-8 col-lg-offset-2">
                        <h1>Seus produtos farmacêuticos de <b>AZ</b>.</h1>
                        <h2>tudo na palma da sua mão</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
            <!-- container -->
        </div>
        
        <!-- white container -->
        <div class="container w" style="padding-bottom: 35px">
            <div class="sign-up-modal">

                <div class="logo-container" >
                </div>
                <form action="ServletWeb" method="post" class="details">
                    <div class="input-container">
                        <input class="col-sm-12 email-input with-placeholder" type="email" id="email" name="email" placeholder="Seu Email" />
                    </div>
                    <div class="input-container">
                        <input class="col-sm-12 email-input with-placeholder" type="password" id="senha" name="senha" placeholder="Sua Senha" />
                    </div>
                    
                    <input type="hidden" id="acao" name="acao" value="Login">
                    
                    <input id="sign-up-button" type="submit" value="Entrar">

                    <p>Novo usuário? <a href="CadastrarCliente.jsp">Registre-se aqui</a>
                    <p>Quer ser nosso parceiro? <a href="CadastrarFarmacia.jsp">Cadastre sua farmácia aqui</a>


                </form>
            </div>
            <!-- sign-up-modal -->
        </div>    


        <!-- Backgroung image -->
        <div id="dg">
            <div class="container">
                <div class="row centered">
                    <div class="row centered">
                        <br><br>
                        <div class="col-lg-6">
                            <i class="fa fa-heart"></i>
                            <h4>Ofertamos saúde</h4>
                            <p>Nós do Farmaz ofertamos saúde, de modo a facilitar sua vida, deixando tudo mais <b>filtrado</b>, <b>rápido</b> e <b>seguro</b> .</p>
                        </div>
                        <!-- col-lg-6 -->

                        <div class="col-lg-6">
                            <i class="fa fa-shopping-cart"></i>
                            <h4>E-COMMERCE</h4>
                            <p>Nós possuimos uma parceira com pag-seguro, <b>facilitando 100% sua vida</b> .</p>
                        </div>
                        <!-- col-lg-6 -->

                    </div>
                    <!-- row -->
                    <br>
                    <div class="row centered">
                        <br><br>
                        <div class="col-lg-6">
                            <i class="fa fa-desktop"></i>
                            <h4>MOBILE & WEB DESIGN</h4>
                            <p>Nossa aplicação deseja ofertar o melhor produto, por isso você pode acessar do seu Desktop ou Celular.</p>
                        </div>
                        <!-- col-lg-4 -->

                        <div class="col-lg-6">
                            <i class="fa fa-cogs"></i>
                            <h4>Implementação Distribuída</h4>
                            <p>Para atender todos os nossos clientes de maneira eficaz, nós adotamos um sistema de distribuição via protocolo UDP</p>
                        </div>
                        <!-- col-lg-4 -->

                    </div>
                    <!-- row -->
                </div>
                <!-- row -->
            </div>
            <!-- container -->
        </div>
        <!-- BG -->

        <!-- FOOTER -->
        <jsp:include page ="Rodape.jsp"/> 

        <div id="copyrights">
            <div class="container">
                <p>
                    &copy; Copyrights <strong>Farmaz</strong>. Todos direitos reservados
                </p>
                <div class="credits">
                    <!--
                      You are NOT allowed to delete the credit link to TemplateMag with free version.
                      You can delete the credit link only if you bought the pro version.
                      Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/spot-bootstrap-freelance-template/
                      Licensing information: https://templatemag.com/license/
                    -->
                    Template fornecido pelo Spot, visite <a href="https://templatemag.com/">TemplateMag</a>
                </div>
            </div>
        </div>

        <!-- JavaScript Libraries -->
        <script src="lib/jquery/jquery.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <script src="lib/php-mail-form/validate.js"></script>
        <script src="lib/chart/chart.js"></script>

        <!-- Template Main Javascript File -->
        <script src="js/main.js"></script>
    </body>
</html>
