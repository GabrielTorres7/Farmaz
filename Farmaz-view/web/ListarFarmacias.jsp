<%-- 
    Document   : ListarFarmacias
    Created on : 24/07/2018, 15:49:04
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterEstadoProxy"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterCidadeProxy"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.cefetmg.farmaz.model.service.ManterEstado"%>
<%@page import="br.cefetmg.farmaz.model.service.ManterCidade"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Disponibilidade"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Farmacia"%>
<%@page import="java.util.List"%>
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

        <!-- =======================================================
          Template Name: Spot
          Template URL: https://templatemag.com/spot-bootstrap-freelance-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->
    </head>
    <body>
        <script src="http://maps.google.com/maps/api/js?key=AIzaSyBpCmWwIrHcvRWQ6loUb5NLB9_EQaEHzUo"></script>
        <div class="container">   
            <h3>Lista de Farmácias - Selecione pelo nome</h3>

            <form name="frmFarmacia" method='post' action='/ServletWeb'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <input type='hidden' name='table' value='Farmacia'>
                <table class="table table-striped"> 
                    <tr>
                        <td>
                            Nome
                        </td>
                        <td>
                            Cnpj
                        </td>
                        <td>
                            Bairro
                        </td>
                        <td>
                            Rua
                        </td>
                        <td>
                            Número
                        </td>
                        <td>
                            Preço
                        </td>
                        <td>
                            Distância
                        </td>
                        <td>
                            Tempo estimado de entrega
                        </td>
                    </tr>
                    <%
                        List<Farmacia> listFarmacia = (List<Farmacia>) request.getAttribute("farmacias");
                        ManterCidadeProxy manterCidade = new ManterCidadeProxy();
                        ManterEstadoProxy manterEstado = new ManterEstadoProxy();
                        List<Disponibilidade> listDisponibilidade = (List<Disponibilidade>) request.getAttribute("disponibilidade");
                        Farmacia farmacia;
                        
                        for (int i=0; i<listFarmacia.size(); i++) {
                            farmacia = new Farmacia();
                            farmacia = listFarmacia.get(i);
                           
                    %>
                    
                            <tr>
                                <td>
                                    <a href="/Farmaz-view/ServletWeb?acao=MostrarMapa&DisponibilidadeId=<%=listDisponibilidade.get(i).getId()%>"><%=farmacia.getNome()%>
                                </td>
                                <td>
                                    <%=farmacia.getCnpj()%>
                                </td>
                                <td>
                                    <%=farmacia.getBairro()%>
                                </td>
                                <td>
                                    <%=farmacia.getRua()%>
                                </td>
                                <td>
                                    <%=farmacia.getNumero()%>
                                </td>
                                <td>
                                    <%=listDisponibilidade.get(i).getPreco()%>                                    
                                </td>
                                 <td>
                                    <div><span id='<%="distancia"+i%>'>&nbsp;</span></div>
                                </td>
                                 <td>
                                    <div><span id='<%="tempo"+i%>'>&nbsp;</span></div>                                    
                                </td>
                            </tr>
                    <%  } %>
                </table>
                
            </form>
        </div>   
        <script type="text/javascript">
            <%
            for(int i=0; i<listFarmacia.size(); i++){
                %>
                    CalculaDistancia("<%= listFarmacia.get(i).getRua()+", "+listFarmacia.get(i).getNumero()+" - "+listFarmacia.get(i).getBairro()
                                            +", "+manterCidade.getCidadeById(listFarmacia.get(i).getCodCidade()).getNome()+" - "+manterEstado.getEstadoById(listFarmacia.get(i).getCodUf()).getSigla()%>", <%=i%>);      
            
            <%}%>
            
            function CalculaDistancia(destino, i) {
                //Instanciar o DistanceMatrixService
                var service = new google.maps.DistanceMatrixService();
                //executar o DistanceMatrixService
                service.getDistanceMatrix(
                    {
                        //Origem
                        origins: ["<%=(String) request.getAttribute("enderecoCliente") %>"],
                        //Destino
                        destinations: [destino],
                        //Modo (DRIVING | WALKING | BICYCLING)
                        travelMode: google.maps.TravelMode.DRIVING,
                        //Sistema de medida (METRIC | IMPERIAL)
                        unitSystem: google.maps.UnitSystem.METRIC
                        //Vai chamar o callback
                    }, callback);
                    function callback(response, status) {
                        //Verificar o Status
                        if (status !== google.maps.DistanceMatrixStatus.OK)
                            //Se o status não for "OK"
                            alert('Parametros do maps incorretos.');
                        else {
                            //Se o status for OK
                            //Endereço de origem = response.originAddresses
                            //Endereço de destino = response.destinationAddresses
                            //Distância = response.rows[0].elements[0].distance.text
                            //Duração = response.rows[0].elements[0].duration.text
                            $("#distancia"+i).html(response.rows[0].elements[0].distance.text);
                            $("#tempo"+i).html(response.rows[0].elements[0].duration.text);
                        }
                    }  
            }
        </script>
    </body>
</html>
