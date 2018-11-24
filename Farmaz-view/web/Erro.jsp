<%-- 
    Document   : Erro
    Created on : 22/07/2018, 21:24:13
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String erro;
    erro = (String) request.getAttribute("erro");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <title>Erro</title>
    </head>
    <body>
         <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <!-- bootstrap -->
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <!-- chamada da função -->
        <script type="text/javascript">
            $(document).ready(function () {
                $('#faqModal').modal('show');
            })
        </script>
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <!-- bootstrap -->
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <!-- chamada da função -->
        <script type="text/javascript">
           $(document).ready(function () {
                $('#faqModal').on('hidden.bs.modal', function () {
                window.history.back();
            })
            })
        </script>
        <div class="modal fade" id="faqModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel" style="text-align: center">Perguntas frequentes  </h4>
                </div>
                <div class="modal-body">
                    <div class="row centered">
                        <h1>Erro:</h1>
                        <h2><%=erro%></h2>
                        <!-- Removendo botao ja que n tem o Faq.jsp
                        <form action="Faq.jsp">
                            <input type="submit" value="Precisa de ajuda?" />
                        </form>
                        -->
                    </div>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->


    <!-- Bibliotecas JS -->
    <script src="lib/jquery/jquery.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="lib/php-mail-form/validate.js"></script>
    <script src="lib/chart/chart.js"></script>
    </body>
</html>
