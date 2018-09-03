/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCliente;
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
public class ManterClienteProxy implements ManterCliente{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterClienteProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long cadastrarCliente(Cliente cliente) throws PersistenciaException, LogicaNegocioException {

        PacoteDados pacoteDados;
        
        String requisicao = "CadastrarCliente";
        pacoteDados = new PacoteDados(requisicao, cliente);
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
    public boolean atualizarCliente(Cliente cliente) throws PersistenciaException, LogicaNegocioException {
        
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarCliente";
        pacoteDados = new PacoteDados(requisicao, cliente);
        
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
    public boolean deletarCliente(Long clienteId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarCliente";
        pacoteDados = new PacoteDados(requisicao, clienteId);
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
    public Cliente getClienteById(Long clienteId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        Cliente cliente = null;
        
        String requisicao = "GetClienteById";
        pacoteDados = new PacoteDados(requisicao, clienteId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            cliente = (Cliente) pacoteRecebido.getObjeto();
            return cliente;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Cliente getClienteByEmail(String email) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        Cliente cliente = null;
        
        String requisicao = "GetClienteByEmail";
        pacoteDados = new PacoteDados(requisicao, email);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            cliente = (Cliente) pacoteRecebido.getObjeto();
            return cliente;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Cliente getClienteByEmailSenha(String email, String senha) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        Cliente cliente = null;
        
        String requisicao = "GetClienteByEmailSenha";
        String[] dados = new String[2];
        dados[0] = email;
        dados[1] = senha;
        
        pacoteDados = new PacoteDados(requisicao, dados);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            cliente = (Cliente) pacoteRecebido.getObjeto();
            return cliente;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Cliente> getAll() throws PersistenciaException {
        
        PacoteDados pacoteDados;
        List<Cliente> clientes = null;
        
        String requisicao = "GetAll";
        pacoteDados = new PacoteDados(requisicao);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            clientes = (List<Cliente>) pacoteRecebido.getObjeto();
            return clientes;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}