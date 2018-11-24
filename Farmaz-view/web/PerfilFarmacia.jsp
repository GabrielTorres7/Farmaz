<%-- 
    Document   : PerfilFarmacia
    Created on : 24/11/2018, 17:30:27
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.model.dominio.Estado"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterEstadoProxy"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterCidadeProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Farmacia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page ="MenuFarmacia.jsp"/>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <style>
            body{
                font-family: 'Raleway', sans-serif;
            }
        </style>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farmaz</title>
    </head>
    <body>
        <div class="container">
                   
            <center>
                <h2>Meu Perfil</h2>
            </center>
            <%
                Farmacia farmacia = (Farmacia)request.getAttribute("farmacia");%>
            
            <form action="ServletWeb" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome da farmácia</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="nome" id="nome" value="<%=farmacia.getNome()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="cadastro_prefeitura">Cadastro da prefeitura</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cadastro_prefeitura" id="cadastro_prefeitura" value="<%=farmacia.getCadastroPrefeitura()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="cnpj">CNPJ</label>
                    <div class="col-xs-9">    
                        <input class="form-control" type="text" name="cnpj" id="cnpj" value="<%=farmacia.getCnpj()%>" required
                            pattern="[0-9]{2}.[0-9]{3}.[0-9]{3}"
                            title="Digite o CNPJ no formato: nn.nnn.nnn">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">Email</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="email" name="email" id="email" value="<%=farmacia.getEmail()%>" readonly="true" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senha">Senha atual</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" name="senhaAtual" id="senhaAtual" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senha">Nova senha</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" name="senhaNova" id="senhaNova">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="cep">CEP</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="cep" id="cep" value="<%=farmacia.getCep()%>" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="rua">Rua</label>
                    
                    <div class="col-xs-5">
                        <input class="form-control" type="text" name="rua" id="rua" value="<%=farmacia.getRua()%>" required>
                    </div>
                    
                    <label class="col-sm-2 control-label" for="numero">Número</label>
                    <div class="col-xs-2">
                        <input class="form-control" type="number" name="numero" id="numero" size="5" value="<%=farmacia.getNumero()%>" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Bairro</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="bairro" id="bairro" value="<%=farmacia.getBairro()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Cidade</label>
                    <div class="col-xs-9">
                        <% ManterCidadeProxy manterCidade = new ManterCidadeProxy(); %>
                        <input class="form-control" type="text" name="cidade" id="cidade" value="<%=manterCidade.getCidadeById(farmacia.getCodCidade()).getNome()%>" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="estado">Estado</label>
                    <div class="col-xs-9">
                        <% ManterEstadoProxy mantemEstado = new ManterEstadoProxy(); %>
                        <select class="form-control" name="estado" id="estado" required>
                            <%
                                List<Estado> listEstado = (List<Estado>) mantemEstado.getAll();
                                String estadoId = mantemEstado.getEstadoById(farmacia.getCodUf()).getSigla();
                                for (Estado estado: listEstado) {
                                    if(estadoId.equals(estado.getSigla())){
                            %>
                            <option value="<%=estado.getSigla()%>" selected="selected"> <%=estado.getNome()%></option>
                            <%      }else{ %>
                            <option value="<%=estado.getSigla()%>"> <%=estado.getNome()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>
                        
                <input type="hidden" name="acao" id="acao" value="AtualizarPerfilFarmacia">

                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <input class='btn btn-primary btn-block' type="submit" name="botao" id="botao" value="Confirmar Alterações">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <a href="/Farmaz-view/ServletWeb?acao=ListarProdutosFarmacia">
                            <input class='btn btn-primary btn-block' type="button" value="Cancelar">
                        </a>
                    </div>
                </div>
                
            </form>
        </div>
        <jsp:include page ="Rodape.jsp"/>
    </body>
</html>
