/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterEstado;
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
public class ManterEstadoProxy implements ManterEstado{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterEstadoProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long cadastrarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "CadastrarEstado";
        pacoteDados = new PacoteDados(requisicao, estado);
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
    public boolean atualizarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarEstado";
        pacoteDados = new PacoteDados(requisicao, estado);
        
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
    public boolean deletarEstado(Long estadoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarEstado";
        pacoteDados = new PacoteDados(requisicao, estadoId);
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
    public Estado getEstadoById(Long estadoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        Estado estado = null;
        
        String requisicao = "GetEstadoById";
        pacoteDados = new PacoteDados(requisicao, estadoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            estado = (Estado) pacoteRecebido.getObjeto();
            return estado;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Estado getEstadoBySigla(String sigla) throws PersistenciaException {
        PacoteDados pacoteDados;
        Estado estado = null;
        
        String requisicao = "GetEstadoBySigla";
        pacoteDados = new PacoteDados(requisicao, sigla);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            estado = (Estado) pacoteRecebido.getObjeto();
            return estado;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Estado> getAll() throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Estado> estados = null;
        
        String requisicao = "GetAllEstados";
        pacoteDados = new PacoteDados(requisicao);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            estados = (List<Estado>) pacoteRecebido.getObjeto();
            return estados;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
