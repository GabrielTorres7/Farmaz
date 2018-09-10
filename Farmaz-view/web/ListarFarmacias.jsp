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
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Farmaz</title>
        <script src="js/jquery.js" type="text/javascript"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
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
                        <td>
                            Avaliacao
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
                                    <div><span id="<%="distancia"+i%>">&nbsp;</span></div>
                                </td>
                                 <td>
                                    <div><span id="<%="tempo"+i%>">&nbsp;</span></div>                                    
                                </td>
                                <td>
                                    <%=listDisponibilidade.get(i).getAvaliacao()%>
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
                    +", "+manterCidade.getCidadeById(listFarmacia.get(i).getCodCidade()).getNome()+" - "+manterEstado.getEstadoById(listFarmacia.get(i).getCodUf()).getSigla()%>");
                  
            function CalculaDistancia(destino) {
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
            }
            //Tratar o retorno do DistanceMatrixService
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
                    $('#<%="distancia"+i%>').html(response.rows[0].elements[0].distance.text);
                    $('#<%="tempo"+i%>').html(response.rows[0].elements[0].duration.text);
                }
            }
                     
            <% }  %>
        </script>
    </body>
</html>
