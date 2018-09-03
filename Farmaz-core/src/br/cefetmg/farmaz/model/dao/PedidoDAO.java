/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface PedidoDAO {
    public Long insert(Pedido pedido) throws PersistenciaException;
    public boolean update(Pedido pedido) throws PersistenciaException;
    public boolean remove(Long pedidoId) throws PersistenciaException;
    public Pedido getPedidoById(Long pedidoId) throws PersistenciaException;
    public List<Pedido> getPedidosByClienteId(Long clienteId) throws PersistenciaException;
    public List<Pedido> getPedidosByFarmaciaId(Long farmaciaId) throws PersistenciaException;
    public List<Pedido> getPedidosByClienteIdAndStatus(Long clienteId, char status) throws PersistenciaException;
    public List<Pedido> getPedidosByFarmaciaIdAndStatus(Long farmaciaId, char status) throws PersistenciaException;
} 
