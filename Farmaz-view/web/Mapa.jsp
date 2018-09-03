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
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
	<title>Farmaz</title>
        
        <script type="text/javascript" language="JavaScript" src="js/script.js"></script>
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="http://maps.google.com/maps/api/js?key=AIzaSyBpCmWwIrHcvRWQ6loUb5NLB9_EQaEHzUo"></script>
    </head>
    <body>
        <div class="container">
            <center>
                <h2>Informações de Entrega</h2>
            </center>
            <form name="frmMapa" action="ServletWeb" method="Post" class="form-horizontal">
                <input type="hidden" name="ultimoJsp" value="Mapa">
                <input type='hidden' name='acao' value=''>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="quantidadeProduto">Quantidade</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="number" id="quantidadeProduto" name="quantidadeProduto" required>
                    </div>
                </div>
                
                <div class="form-group" style="padding: 10px 0 0; clear: both">
                    <div class="col-sm-10 col-sm-offset-2 embed-responsive embed-responsive-16by9">
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
                    <div class="col-sm-10 col-sm-offset-2">
                        <input class='btn btn-primary btn-block' type="submit" value="Finalizar Compra" onclick="SetAcao('FinalizarCompra',document.frmMapa)">
                    </div>
                </div>
                            
                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-2">
                        <input class='btn btn-success btn-block' type="submit" value="Adicionar ao Meu Carrinho" onclick="SetAcao('Adicionar ao Meu Carrinho',document.frmMapa)">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
