<%-- 
    Document   : PerfilCliente
    Created on : 21/11/2018, 19:56:21
    Author     : Gabriel
--%>

<%@page import="br.cefetmg.farmaz.proxy.ManterCidadeProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Estado"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.proxy.ManterEstadoProxy"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Endereco"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page ="MenuCliente.jsp"/>
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
                Cliente cliente = (Cliente)request.getAttribute("cliente");
                Endereco endereco = (Endereco)request.getAttribute("endereco");%>
            
            
            <form action="ServletWeb" method="post" class="form-horizontal">
                <div>
                    <h2>Dados Pessoais</h2>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="nome" id="nome" value="<%=cliente.getNome()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="email">Email</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="email" id="email" value="<%=cliente.getEmail()%>" readonly="true" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="documento">Documento de identificação</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="documento" id="documento" value="<%=cliente.getDocumentoIdentificacao()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="telefone">Telefone</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="telefone" id="telefone" value="<%=cliente.getNumeroTelefone()%>" required>
                    </div>
                </div>
                
                    <div class="form-group">
                    <label class="col-sm-2 control-label" for="senhaAtual">Senha atual</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" name="senhaAtual" id="senhaAtual" required>
                    </div>
                </div>
                    
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senhaNova">Nova senha</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" name="senhaNova" id="senhaNova">
                    </div>
                </div>
                
                <div>
                    <h2>Endereço</h2>
                </div>
                    
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="cep">CEP</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="number" name="cep" id="cep" value="<%=endereco.getCep() %>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="rua">Rua</label>
                    
                    <div class="col-xs-5">
                        <input class="form-control" type="text" name="rua" id="rua" value="<%=endereco.getRua()%>" required>
                    </div>
                    
                    <label class="col-sm-2 control-label" for="numero">Número</label>
                    <div class="col-xs-2">
                        <input class="form-control" type="number" name="numero" id="numero" size="5" value="<%=endereco.getNumero()%>" required>
                    </div>
                </div>
                
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Bairro</label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="bairro" id="bairro" value="<%=endereco.getBairro()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Cidade</label>
                    <div class="col-xs-9">
                        <% ManterCidadeProxy manterCidade = new ManterCidadeProxy(); %>
                        <input class="form-control" type="text" name="cidade" id="cidade" value="<%=manterCidade.getCidadeById(endereco.getCodCidade()).getNome()%>" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="estado">Estado</label>
                    <div class="col-xs-9">
                        <% ManterEstadoProxy mantemEstado = new ManterEstadoProxy(); %>
                        <select class="form-control" name="estado" id="estado" required>
                            <%
                                List<Estado> listEstado = (List<Estado>) mantemEstado.getAll();
                                String estadoId = mantemEstado.getEstadoById(endereco.getCodUf()).getSigla();
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
                        
                <input type="hidden" name="acao" id="acao" value="AtualizarPerfilCliente">

                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <input class='btn btn-primary btn-block' type="submit" name="botao" id="botao" value="Confirmar Alterações">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-9 col-sm-offset-2">
                        <a href="/Farmaz-view/ServletWeb?acao=Voltar">
                            <input class='btn btn-primary btn-block' type="button" value="Cancelar">
                        </a>
                    </div>
                </div>
                
            </form>
        </div>
        <jsp:include page ="Rodape.jsp"/>
    </body>
</html>
