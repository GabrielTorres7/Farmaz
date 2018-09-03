/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterItemPedido {
    public Long inserirItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException;
    public boolean atualizarItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException;
    public boolean deletarItemPedido(Long itemPedidoId) throws PersistenciaException;
    public ItemPedido getItemPedidoById(Long itemPedidoId) throws PersistenciaException;
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException;
}
