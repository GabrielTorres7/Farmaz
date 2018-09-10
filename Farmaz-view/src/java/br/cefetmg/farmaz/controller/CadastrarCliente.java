/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Cidade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterCidadeProxy;
import br.cefetmg.farmaz.proxy.ManterClienteProxy;
import br.cefetmg.farmaz.proxy.ManterEnderecoProxy;
import br.cefetmg.farmaz.proxy.ManterEstadoProxy;

/**
 *
 * @author Gabriel
 */
public class CadastrarCliente {
 
    public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {
            Endereco endereco;
            Cliente cliente;
            Estado estadoDominio = new Estado();
            Cidade cidadeDominio = new Cidade();
            ManterClienteProxy manterCliente;
            ManterEnderecoProxy manterEndereco = new ManterEnderecoProxy();
            ManterCidadeProxy manterCidade = new ManterCidadeProxy();
            ManterEstadoProxy manterEstado = new ManterEstadoProxy();

            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String documentoIdentificacao = request.getParameter("documento_identificacao");
            String telefone = request.getParameter("telefone");
            String senha = request.getParameter("senha");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            String numero = request.getParameter("numero");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            
            estadoDominio = manterEstado.getEstadoBySigla(estado);
            Long cidadeId = null;
            
            if(manterCidade.getCidadeByNome(cidade) == null){
                cidadeDominio.setNome(cidade);
                cidadeDominio.setUfId(estadoDominio.getId());
                cidadeId = manterCidade.cadastrarCidade(cidadeDominio);
            }else{
                cidadeDominio = manterCidade.getCidadeByNome(cidade);
            }
            
            cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setDocumentoIdentificacao(documentoIdentificacao);
            cliente.setNumeroTelefone(telefone);
            cliente.setSenha(senha);

            manterCliente = new ManterClienteProxy();
            Long clienteId = manterCliente.cadastrarCliente(cliente);
            
            endereco = new Endereco();
            endereco.setClienteId(clienteId);
            if(cidadeId == null)
                endereco.setCodCidade(cidadeDominio.getCidadeId());
            else
                endereco.setCodCidade(cidadeId);
            
            endereco.setCodUf(estadoDominio.getId());
            endereco.setCep(cep);
            endereco.setBairro(bairro);
            endereco.setRua(rua);
            endereco.setNumero(Integer.parseInt(numero));
            
            manterEndereco.inserirEndereco(endereco);
            
            request.setAttribute("email", email);
            request.setAttribute("senha", senha);
            request.setAttribute("tipo", "cadastro");
            
            jsp = Login.executa(request);

        } catch (PersistenciaException | LogicaNegocioException ex) {
             System.out.println(ex);
             jsp = "";
        }
        
        return jsp;

    }
}