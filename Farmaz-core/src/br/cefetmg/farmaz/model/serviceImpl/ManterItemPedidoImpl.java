/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.ItemPedidoDAOImpl;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterItemPedido;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterItemPedidoImpl implements ManterItemPedido{
    
    private final ItemPedidoDAOImpl itemPedidoDAO;

    public ManterItemPedidoImpl(ItemPedidoDAOImpl itemPedidoDAO) {
        this.itemPedidoDAO = itemPedidoDAO;
    }
    
    @Override
    public Long inserirItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException {
        if (itemPedido == null) {
            throw new LogicaNegocioException("Item Pedido não pode ser nulo");
        }
        if (itemPedido.getPedidoId() == null) {
            throw new LogicaNegocioException("Id do pedido de item pedido não pode ser nulo");
        }
        if (itemPedido.getProdutoId() == null) {
            throw new LogicaNegocioException("Id do produto de item pedido não pode ser nulo");
        }
        if (itemPedido.getQuantidade() == 0) {
            throw new LogicaNegocioException("Quantidade de itens no pedido não pode ser zero");
        }
        return itemPedidoDAO.insert(itemPedido);
    }

    @Override
    public boolean atualizarItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException {
        if (itemPedido == null) {
            throw new LogicaNegocioException("Item Pedido não pode ser nulo");
        }
        if (itemPedido.getItemPedidoId() == null) {
            throw new LogicaNegocioException("Id do item pedido não pode ser nulo");
        }
        if (itemPedido.getPedidoId() == null) {
            throw new LogicaNegocioException("Id do pedido de item pedido não pode ser nulo");
        }
        if (itemPedido.getProdutoId() == null) {
            throw new LogicaNegocioException("Id do produto de item pedido não pode ser nulo");
        }
        if (itemPedido.getQuantidade() == 0) {
            throw new LogicaNegocioException("Quantidade de itens no pedido não pode ser zero");
        }
        return itemPedidoDAO.update(itemPedido);
    }

    @Override
    public boolean deletarItemPedido(Long itemPedidoId) throws PersistenciaException {
        if (itemPedidoId == null) {
            throw new PersistenciaException("Id do item pedido não pode ser nulo");
        }
        if (itemPedidoDAO.getItemPedidoById(itemPedidoId) == null) {
            throw new PersistenciaException("O item pedido com o id " + itemPedidoId + "não existe");
        }
        return itemPedidoDAO.remove(itemPedidoId);
    }

    @Override
    public ItemPedido getItemPedidoById(Long itemPedidoId) throws PersistenciaException {
        if (itemPedidoId == null) {
            throw new PersistenciaException("O id do item pedido não pode ser nulo");
        }
        return itemPedidoDAO.getItemPedidoById(itemPedidoId);
    }

    @Override
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException {
        if (pedidoId == null) {
            throw new PersistenciaException("O id do pedido de item pedido não pode ser nulo");
        }
        return itemPedidoDAO.getItensPedidoByPedidoId(pedidoId);
    }
    
}
