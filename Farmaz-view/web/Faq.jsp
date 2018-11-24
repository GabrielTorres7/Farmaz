<%-- 
    Document   : Faq
    Created on : 02/09/2018, 00:08:43
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Spot - Bootstrap Freelance Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicons -->
        <link href="img/favicon.png" rel="icon">
        <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">

        <!-- Bootstrap CSS File -->
        <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Libraries CSS Files -->
        <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <!-- Main Stylesheet File -->
        <link href="css/style.css" rel="stylesheet">


        <title>Farmaz FAQ</title>
    </head>
    <body>

        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <!-- bootstrap -->
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <!-- chamada da função -->
        <script type="text/javascript">
            $('#faqModal').on('hidden.bs.modal', function () {
                alert("Hello! I am an alert box!");
               
              })
        </script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#faqModal').modal('show');
            })
        </script>
        
        
    </div>

    <div class="modal fade" id="faqModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel" style="text-align: center">Perguntas frequentes</h4>
                </div>
                <div class="modal-body">
                    <div class="row centered">
                        <h3><b> O que é o Farmaz?</b></h3>
                        <p>Farmaz é um aplicativo que fornece ao usuário uma maneira de suprir
                            sua demanda por produtos farmacêuticos de forma rápida e prática, 
                            através da conexão direta entre cliente e estabelecimento.<br>
                            Além disso, o Farmaz permite que você veja e compare
                            os melhores preços e rotas de entrega, se adequando a sua necessidade</p>

                        <h3><b>Quais produtos posso comprar através do Farmaz?</b> </h3>
                        <p> No Farmaz é possivel encontrar produtos comuns de se encontrar em farmácias,
                            como por exemplo : Medicamentos e remédios, produtos de beleza, nutrição, higiene
                            pessoal, entre diversas outras opções.
                        </p>
                        <h3><b>Qual o prazo de entrega?</b> </h3>
                        <p> O prazo de entrega vai variar de acordo com alguns fatores como a
                            distância entre seu local e o estabelecimento comercial,transito, entre outros.
                            O Farmaz disponibiliza um cálculo aproximado ao realizar a compra.
                        </p>
                        <h3><b>Quais formas de pagamento são aceitas?</b> </h3>
                        <p> São aceitas transações em dinheiro pagas diretamente ao entregador e cartões
                            de crédito diversos através do PagSeguro.
                        </p>
                        <h3><b>O Farmaz se responsabiliza pela venda e entrega dos produtos?</b> </h3>
                        <p> O sistema Farmaz não se responsabiliza diretamente, uma vez que fazemos a aproximação
                            entre o cliente e a farmácia e, após isso, eventuais problemas são de responsababilidade do estabelecimento comercial.
                        </p>
                    </div>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <a href="javascript:window.history.go(-1)">Voltar</a>


    <!-- Bibliotecas JS -->
    <script src="lib/jquery/jquery.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="lib/php-mail-form/validate.js"></script>
    <script src="lib/chart/chart.js"></script>

</body>
</html>
