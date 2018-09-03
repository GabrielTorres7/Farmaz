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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <style>
            body{
                font-family: 'Raleway', sans-serif;
            }
            a{
                position: relative;
            }
            a:hover{
                top:-2px;
                border-bottom:2px solid #999
            }
        </style>
        <script>
            function mostraCampo(){
              var inputTroco = document.getElementById('trocoDiv');
              var submit = document.getElementById('acao');
              
              inputTroco.style.display = "block";
              submit.style.display = "block";
            }
        </script>
        <title>Farmaz</title>
    </head>
    <body>
        <div class="container">
            <form action="ServletWeb" method="post" class="form-horizontal">
                
                <center>
                    <h3>Método de Pagamento</h3>
                </center>

                <div class="text-left">
                    <h4>
                        Total: <%=request.getSession().getAttribute("Total")%>
                    </h4>
                </div>

                <div class="col-sm-6">
                    <a onclick="mostraCampo()" href="#">
                        <img onclick="" title="Dinheiro" class="thumbnail" src="images/dinheiro.png" class="img-responsive" width="50%" >
                    </a>
                </div>


                <div class="col-sm-6">
                    <a href="#">
                        <img title="Cartão" class="thumbnail" src="images/cartao.png" class="img-responsive" width="50%" >
                    </a>
                </div>
                    
                <div style="display: none" class="form-group" id="trocoDiv">
                <label class="col-sm-2 control-label" for="troco">Troco para</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="troco" id="troco" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <input style="display: none" class='btn btn-success btn-block' type="submit" id="acao" name="acao" value="Fazer Pedido">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
