<%-- 
    Document   : ListarProdutosFarmacia
    Created on : 20/11/2018, 19:48:39
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterFarmaciaProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Farmacia"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterProdutoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Disponibilidade"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page ="MenuFarmacia.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Farmaz</title>

        <script src="js/script.js" type="text/javascript"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">   
            <% ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();%>
            <h3>Lista de Produtos - <%=manterFarmacia.getFarmaciaById(((String) request.getSession().getAttribute("cod_farmacia"))).getNome()%></h3>

            <a href='InserirProduto.jsp'>Novo Produto</a>

            <form name="frmProduto" method='post' action='/ServletWeb'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <input type='hidden' name='table' value='Produto'>
                <table class="table table-striped"> 
                    <tr class="info">
                        <td>
                            Nome
                        </td>
                        <td>
                            Receita
                        </td>
                        <td>
                            Descricao
                        </td>
                        <td>
                            Laboratorio
                        </td>
                        <td>
                            Cadastro da Anvisa
                        </td>
                        <td>
                            Estoque
                        </td>
                        <td>
                            Preço
                        </td>
                        <td>
                            Avaliação
                        </td>
                    </tr>
                    <%
                        ManterProdutoProxy manterProduto;
                        Produto produto;
                        List<Disponibilidade> listProduto = (List<Disponibilidade>) request.getAttribute("produtos");
                        if (listProduto != null) {
                            for (Disponibilidade disponibilidade : listProduto) {
                                manterProduto = new ManterProdutoProxy();
                                produto = manterProduto.getProdutoById(disponibilidade.getProdutoSeq());
                    %>
                    <tr>
                        <td>
                            <%=produto.getNome()%>
                        </td>
                        <td>
                            <% if (produto.isReceita() == true) {
                                    out.print("Possui");
                                } else {
                                    out.print("Não possui");
                                }
                            %>
                        </td>
                        <td>
                            <%=produto.getDescricao()%>
                        </td>
                        <td>
                            <%=produto.getLaboratorio()%>
                        </td>
                        <td>
                            <%=produto.getCadastroAnvisa()%>
                        </td>
                        <td>
                            <%=disponibilidade.getEstoque()%>
                        </td>
                        <td>
                            <%=disponibilidade.getPreco()%>
                        </td>
                        <td>
                            <%=disponibilidade.getAvaliacao()%>
                        </td>
                        <td>
                            <a href="/Farmaz-view/ServletWeb?acao=EditarProduto&CodProduto=<%=disponibilidade.getId()%>">
                                <i class="glyphicon glyphicon-edit"></i>
                            </a>
                        </td>
                        <td>
                            <a href="/Farmaz-view/ServletWeb?acao=ExcluirProduto&CodProduto=<%=disponibilidade.getId()%>">
                                <i class="glyphicon glyphicon-remove"></i>
                            </a>
                        </td>
                    </tr>
                    <%  }
                        }else{%>   
                        
                            <h2>
                                Voce ainda não cadastrou nenhum produto!
                            </h2>
                        
                        <%}
                            %>
                </table>
            </form>
        </div>              
    </body>
</html>
