<%-- 
    Document   : SobreNos
    Created on : 21/11/2018, 21:28:57
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
        <div id="blue">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-8 col-lg-offset-2">
                        <h1>Nossa equipe de desenvolvimento.</h1>
                        <h2>Who are those guys ?</h2>
                    </div>
                </div>
                <!-- row -->
            </div>
            <!-- container -->
        </div>

        <div class="container w">
            <div class="row centered"     style="display: flex;
  width: 100%;">
                <br><br>
                <div class="col-lg-3" style="flex: 1;">
                    <!--A IMAGEM TEM QUE SER 150px/150px -->
                    <img class="img-circle" src="images/perfil1.png" width="110" height="110" alt="">
                    <h4>Allan Barbosa</h4>
                    <p>Desenvolvedor Front-end, Designer nas horas vagas e melhor Quarter-Back do  meu bairro   </p>
                    <p><a href="https://github.com/AllanBarbo">@AllanBarbo</a></p>
                </div>
                <!-- col-lg-3 -->

                <div class="col-lg-3" style="flex: 1;">
                    <img class="img-circle"  src="images/perfil2.png" width="110" height="110" alt="">
                    <h4>Arthur Codama</h4>
                    <p>Eu sou fofo. As vezes finjo ser desenvolvedor Back-end e melhor tracer de inf.</p>
                    <p><a href="https://github.com/ArthurCodama">@ArthurCodama</a></p>
                </div>
                <!-- col-lg-3 -->

                <div class="col-lg-3" style="flex: 1;">
                    <img class="img-circle"  src="images/perfil3.png" width="110" height="110" alt="">
                    <h4>Gabriel Torres</h4>
                    <p>Apaixonado por tecnologia, desenvolvimento e futebol</p>
                    <p><a href="https://github.com/GabrielTorres7">@GabrielTorres7</a></p>
                </div>
                <!-- col-lg-3 -->

                <div class="col-lg-3" style="flex: 1;">
                    <img class="img-circle"  src="images/perfil4.png" width="110" height="110" alt="">
                    <h4>Hiago Martins</h4>
                    <p>Desenvolvedor full stack, jogador casual de videogames e admirador de culinária e gastronomia</p>
                    <p><a href="https://github.com/Hiagozeroum">@Hiagozeroum</a></p>
                </div>
                <!-- col-lg-3 -->
                
                <div class="col-lg-3" style="flex: 1;">
                    <img class="img-circle"  src="images/perfil5.png" width="110" height="110" alt="">
                    <h4>Pedro Henrique</h4>
                    <p>Programador back-end que prefere ler sobre política/economia e sonha, todo dia, em nunca mais encostar em uma IDE.</p>
                    <p><a href="https://github.com/PedroHenriqueV">@PedroHenriqueV</a></p>
                </div>
                <!-- col-lg-3 -->

            </div>
            <!-- row -->
            <br>
            <br>
        </div>
        <!-- container -->


        <!-- PORTFOLIO SECTION -->
        <div id="dg">
            <div class="container">
                <div class="row centered">
                    <h3><b>Nossas habilidades</b></h3>
                    <br>

                    <!-- First Chart -->
                    <div class="col-lg-4">
                        <canvas id="canvas" height="130" width="130"></canvas>
                        <br>
                        <p><b>Design & Marca</b></p>
                        <p><small>O Farmaz possui um logo simples, objetivo e minimalista, assim como o nosso site </small></p>
                    </div>
                    <!-- /col-lg-3 -->

                    <!-- Second Chart -->
                    <div class="col-lg-4">
                        <canvas id="canvas2" height="130" width="130"></canvas>
                        <br>
                        <p><b>Desenvolvimento WEB</b></p>
                        <p><small>Nos demos aula na programação para web com site bonito, minimalista e responsivo</small></p>
                    </div>
                    <!-- /col-lg-3 -->

                    <!-- Third Chart -->
                    <div class="col-lg-4">
                        <canvas id="canvas3" height="130" width="130"></canvas>
                        <br>
                        <p><b>Back-end</b></p>
                        <p><small>Banco de dados postgres, Back-end em java usando servlets</small></p>
                    </div>
                    <!-- /col-lg-3 -->



                </div>
                <!-- row -->
            </div>
            <!-- container -->
        </div>
        
        
        <!-- Template Main Javascript File -->
        <script src="js/main.js"></script>
        
    </body>
</html>
