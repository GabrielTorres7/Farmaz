/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.PedidoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterPedido;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterPedidoImpl implements ManterPedido {
    
    private final PedidoDAOImpl pedidoDAO;

    public ManterPedidoImpl(PedidoDAOImpl pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }
    
    @Override
    public Long criarPedido(Pedido pedido) throws PersistenciaException, LogicaNegocioException {
        if (pedido == null) {
            throw new LogicaNegocioException("Pedido não pode ser nulo");
        }
        if (pedido.getClienteId() == null) {
            throw new LogicaNegocioException("Id do cliente não pode ser nulo");
        }
        if (pedido.getFarmaciaId() == null) {
            throw new LogicaNegocioException("Id da farmacia não pode ser nulo");
        }
        if (pedido.getDataHora() == null) {
            throw new LogicaNegocioException("A data do pedido não pode ser nula");
        }
        if (pedido.getIdtStatus() == '\0') {
            throw new LogicaNegocioException("O status do pedido não pode ser nulo");
        }
        
        return pedidoDAO.insert(pedido);
    }

    @Override
    public boolean atualizarPedido(Pedido pedido) throws PersistenciaException, LogicaNegocioException {
        if (pedido == null) {
            throw new LogicaNegocioException("Pedido não pode ser nulo");
        }
        if (pedido.getPedidoId() == null) {
            throw new LogicaNegocioException("Id do pedido não pode ser nulo");
        }
        if (pedido.getClienteId() == null) {
            throw new LogicaNegocioException("Id do cliente não pode ser nulo");
        }
        if (pedido.getFarmaciaId() == null) {
            throw new LogicaNegocioException("Id da farmacia não pode ser nulo");
        }
        if (pedido.getDataHora() == null) {
            throw new LogicaNegocioException("A data do pedido não pode ser nula");
        }
        if (pedido.getIdtStatus() == '\0') {
            throw new LogicaNegocioException("O status do pedido não pode ser nulo");
        }
        
        return pedidoDAO.update(pedido);
    }

    @Override
    public boolean deletarPedido(Long pedidoId) throws PersistenciaException {
        if (pedidoId == null) {
            throw new PersistenciaException("Id do pedido não pode ser nulo");
        }
        if (pedidoDAO.getPedidoById(pedidoId) == null) {
            throw new PersistenciaException("O pedido com o id " + pedidoId + "não existe");
        }
        return pedidoDAO.remove(pedidoId);
    }

    @Override
    public Pedido getPedidoById(Long pedidoId) throws PersistenciaException {
        if (pedidoId == null) {
            throw new PersistenciaException("O id do pedido não pode ser nulo");
        }
        return pedidoDAO.getPedidoById(pedidoId);
    }

    @Override
    public List<Pedido> getPedidosByClienteId(Long clienteId) throws PersistenciaException {
        if (clienteId == null) {
            throw new PersistenciaException("O id do cliente não pode ser nulo");
        }
        return pedidoDAO.getPedidosByClienteId(clienteId);
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaId(Long farmaciaId) throws PersistenciaException {
        if (farmaciaId == null) {
            throw new PersistenciaException("O id da farmacia não pode ser nulo");
        }
        return pedidoDAO.getPedidosByFarmaciaId(farmaciaId);
    }

    @Override
    public List<Pedido> getPedidosByClienteIdAndStatus(Long clienteId, char status) throws PersistenciaException {
        if (clienteId == null) {
            throw new PersistenciaException("O id do cliente não pode ser nulo");
        }
        if (status == '\0') {
            throw new PersistenciaException("O status do pedido não pode ser nulo");
        }
        return pedidoDAO.getPedidosByClienteIdAndStatus(clienteId, status);
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaIdAndStatus(Long farmaciaId, char status) throws PersistenciaException {
        if (farmaciaId == null) {
            throw new PersistenciaException("O id da farmacia não pode ser nulo");
        }
        if (status == '\0') {
            throw new PersistenciaException("O status do pedido não pode ser nulo");
        }
        return pedidoDAO.getPedidosByFarmaciaIdAndStatus(farmaciaId, status);
    }
    
}
