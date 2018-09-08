/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCidade;
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
public class ManterCidadeProxy implements ManterCidade{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterCidadeProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long cadastrarCidade(Cidade cidade) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "CadastrarCidade";
        pacoteDados = new PacoteDados(requisicao, cidade);
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
    public boolean atualizarCidade(Cidade cidade) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarCidade";
        pacoteDados = new PacoteDados(requisicao, cidade);
        
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
    public boolean deletarCidade(Long cidadeId) throws PersistenciaException {
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarCidade";
        pacoteDados = new PacoteDados(requisicao, cidadeId);
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
    public Cidade getCidadeById(Long cidadeId) throws PersistenciaException {
        PacoteDados pacoteDados;
        Cidade cidade = null;
        
        String requisicao = "GetCidadeById";
        pacoteDados = new PacoteDados(requisicao, cidadeId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            cidade = (Cidade) pacoteRecebido.getObjeto();
            return cidade;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Cidade getCidadeByNome(String nome) throws PersistenciaException {
        PacoteDados pacoteDados;
        Cidade cidade = null;
        
        String requisicao = "GetCidadeByNome";
        pacoteDados = new PacoteDados(requisicao, nome);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            cidade = (Cidade) pacoteRecebido.getObjeto();
            return cidade;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Cidade> getAll() throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Cidade> cidades = null;
        
        String requisicao = "GetAllCidades";
        pacoteDados = new PacoteDados(requisicao);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            cidades = (List<Cidade>) pacoteRecebido.getObjeto();
            return cidades;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
