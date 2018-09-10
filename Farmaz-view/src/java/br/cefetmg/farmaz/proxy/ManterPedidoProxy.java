/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterPedido;
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
public class ManterPedidoProxy implements ManterPedido{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterPedidoProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long criarPedido(Pedido pedido) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "CriarPedido";
        pacoteDados = new PacoteDados(requisicao, pedido);
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
    public boolean atualizarPedido(Pedido pedido) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarPedido";
        pacoteDados = new PacoteDados(requisicao, pedido);
        
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
    public boolean deletarPedido(Long pedidoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarPedido";
        pacoteDados = new PacoteDados(requisicao, pedidoId);
        
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
    public Pedido getPedidoById(Long pedidoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        Pedido pedido = null;
        
        String requisicao = "GetPedidoById";
        pacoteDados = new PacoteDados(requisicao, pedidoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            pedido = (Pedido) pacoteRecebido.getObjeto();
            return pedido;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Pedido> getPedidosByClienteId(Long clienteId) throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Pedido> pedidos = null;
        
        String requisicao = "GetPedidosByClienteId";
        pacoteDados = new PacoteDados(requisicao, clienteId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            pedidos = (List<Pedido>) pacoteRecebido.getObjeto();
            return pedidos;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaId(Long farmaciaId) throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Pedido> pedidos = null;
        
        String requisicao = "GetPedidosByFarmaciaId";
        pacoteDados = new PacoteDados(requisicao, farmaciaId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            pedidos = (List<Pedido>) pacoteRecebido.getObjeto();
            return pedidos;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Pedido> getPedidosByClienteIdAndStatus(Long clienteId, char status) throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Pedido> pedidos = null;
        
        String requisicao = "GetPedidosByClienteIdAndStatus";
        Object[] dados = new String[2];
        dados[0] = clienteId;
        dados[1] = status;
        
        pacoteDados = new PacoteDados(requisicao, dados);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            pedidos = (List<Pedido>) pacoteRecebido.getObjeto();
            return pedidos;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaIdAndStatus(Long farmaciaId, char status) throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Pedido> pedidos = null;
        
        String requisicao = "GetPedidosByFarmaciaIdAndStatus";
        Object[] dados = new String[2];
        dados[0] = farmaciaId;
        dados[1] = status;
        
        pacoteDados = new PacoteDados(requisicao, dados);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            pedidos = (List<Pedido>) pacoteRecebido.getObjeto();
            return pedidos;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
