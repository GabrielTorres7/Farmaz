/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.proxy.ManterCidadeProxy;
import br.cefetmg.farmaz.proxy.ManterClienteProxy;
import br.cefetmg.farmaz.proxy.ManterEnderecoProxy;
import br.cefetmg.farmaz.proxy.ManterEstadoProxy;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class AtualizarPerfilCliente {
    
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String documento = request.getParameter("documento");
            String telefone = request.getParameter("telefone");
            String senhaAtual = request.getParameter("senhaAtual");
            String senhaNova = request.getParameter("senhaNova");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            
            ManterClienteProxy manterCliente = new ManterClienteProxy();
            ManterEnderecoProxy manterEndereco = new ManterEnderecoProxy();
            ManterEstadoProxy manterEstado = new ManterEstadoProxy();
            ManterCidadeProxy manterCidade = new ManterCidadeProxy();
            
            Cliente cliente = new Cliente();
            Endereco endereco = new Endereco();
            Cidade cidadeDominio = new Cidade();
            
            cliente.setId((Long) request.getSession().getAttribute("cod_cliente"));
            cliente.setNome(nome);
            cliente.setDocumentoIdentificacao(documento);
            cliente.setEmail(email);
            cliente.setNumeroTelefone(telefone);
            if(manterCliente.getClienteByEmailSenha(email, senhaAtual) == null
                    || !Objects.equals(manterCliente.getClienteByEmailSenha(email, senhaAtual).getId(), cliente.getId())){
                String erro = "Senha inválida!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
                return jsp;
            }else{
                if(senhaNova.equals(null) || senhaNova.equals("")){
                    cliente.setSenha(senhaAtual);
                }else{
                    cliente.setSenha(senhaNova);
                }
            }
            manterCliente.atualizarCliente(cliente);
            
            endereco.setClienteId(cliente.getId());
            endereco.setBairro(bairro);
            endereco.setCep(cep);
            endereco.setEnderecoId(manterEndereco.getEnderecosByClienteId(cliente.getId()).get(0).getEnderecoId());
            endereco.setRua(rua);
            endereco.setNumero(numero);
            endereco.setCodUf(manterEstado.getEstadoBySigla(estado).getId());
            if(manterCidade.getCidadeByNome(cidade) == null){
                cidadeDominio.setNome(cidade);
                cidadeDominio.setUfId(manterEstado.getEstadoBySigla(estado).getId());
                manterCidade.cadastrarCidade(cidadeDominio);
            }else{
                cidadeDominio = manterCidade.getCidadeByNome(cidade);
            }
            endereco.setCodCidade(cidadeDominio.getCidadeId());
            
            manterEndereco.atualizarEndereco(endereco);
            
            jsp = ListarProdutosCliente.executa(request);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
