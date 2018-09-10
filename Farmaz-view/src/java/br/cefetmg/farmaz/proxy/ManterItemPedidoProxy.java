/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterItemPedido;
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
public class ManterItemPedidoProxy implements ManterItemPedido{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterItemPedidoProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long inserirItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "InserirItemPedido";
        pacoteDados = new PacoteDados(requisicao, itemPedido);
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
    public boolean atualizarItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarItemPedido";
        pacoteDados = new PacoteDados(requisicao, itemPedido);
        
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
    public boolean deletarItemPedido(Long itemPedidoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarItemPedido";
        pacoteDados = new PacoteDados(requisicao, itemPedidoId);
        
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
    public ItemPedido getItemPedidoById(Long itemPedidoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        ItemPedido itemPedido = null;
        
        String requisicao = "GetItemPedidoById";
        pacoteDados = new PacoteDados(requisicao, itemPedidoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            itemPedido = (ItemPedido) pacoteRecebido.getObjeto();
            return itemPedido;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException {
        PacoteDados pacoteDados;
        List<ItemPedido> itensPedido = null;
        
        String requisicao = "GetItensPedidoByPedidoId";
        pacoteDados = new PacoteDados(requisicao, pedidoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            itensPedido = (List<ItemPedido>) pacoteRecebido.getObjeto();
            return itensPedido;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
