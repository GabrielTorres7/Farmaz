/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterEndereco;
import br.cefetmg.farmaz.util.PacoteDados;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ManterEnderecoProxy implements ManterEndereco{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterEnderecoProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long inserirEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "InserirEndereco";
        pacoteDados = new PacoteDados(requisicao, endereco);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
          
            return (Long) pacoteRecebido.getObjeto();
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean atualizarEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarEndereco";
        pacoteDados = new PacoteDados(requisicao, endereco);
        
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            if("T".equals(pacoteRecebido.getRequisicao()))
                return true;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deletarEndereco(Long enderecoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarEndereco";
        pacoteDados = new PacoteDados(requisicao, enderecoId);
        
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            if("T".equals(pacoteRecebido.getRequisicao()))
                return true;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Endereco getEnderecoById(Long enderecoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        Endereco endereco = null;
        
        String requisicao = "GetEnderecoById";
        pacoteDados = new PacoteDados(requisicao, enderecoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            endereco = (Endereco) pacoteRecebido.getObjeto();
            return endereco;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Endereco> getEnderecosByClienteId(Long clienteId) throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Endereco> enderecos = null;
        
        String requisicao = "GetEnderecosByClienteId";
        pacoteDados = new PacoteDados(requisicao, clienteId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            enderecos = (List<Endereco>) pacoteRecebido.getObjeto();
            return enderecos;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
