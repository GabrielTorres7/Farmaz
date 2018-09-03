/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.EstadoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterEstado;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterEstadoImpl implements ManterEstado{
    
    private final EstadoDAOImpl estadoDAO;

    public ManterEstadoImpl(EstadoDAOImpl estadoDAO) {
        this.estadoDAO = estadoDAO;
    }
    
    @Override
    public Long cadastrarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException {
        if (estado == null) {
            throw new LogicaNegocioException("Estado não pode ser nulo");
        }
        if (estado.getSigla() == null
                || estado.getSigla().isEmpty()) {
            throw new LogicaNegocioException("Sigla do estado não pode ser nula");
        }
        if (estado.getNome() == null 
                || estado.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome do estado não pode ser nulo");
        }
        
        return estadoDAO.insert(estado);
    }

    @Override
    public boolean atualizarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException {
        if (estado == null) {
            throw new LogicaNegocioException("Estado não pode ser nulo");
        }
        if (estado.getId()== null) {
            throw new LogicaNegocioException("Id do estado não pode ser nulo");
        }
        if (estado.getSigla() == null
                || estado.getSigla().isEmpty()) {
            throw new LogicaNegocioException("Sigla do estado não pode ser nula");
        }
        if (estado.getNome() == null 
                || estado.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome do estado não pode ser nulo");
        }
        
        return estadoDAO.update(estado);
    }

    @Override
    public boolean deletarEstado(Long estadoId) throws PersistenciaException {
        if (estadoId == null) {
            throw new PersistenciaException("Id do estado não pode ser nulo");
        }
        if (estadoDAO.getEstadoById(estadoId) == null) {
            throw new PersistenciaException("O estado com o id " + estadoId + "não existe");
        }
        return estadoDAO.remove(estadoId);
    }

    @Override
    public Estado getEstadoById(Long estadoId) throws PersistenciaException {
        if (estadoId == null) {
            throw new PersistenciaException("O id do estado não pode ser nulo");
        }
        return estadoDAO.getEstadoById(estadoId);
    }

    @Override
    public Estado getEstadoBySigla(String sigla) throws PersistenciaException {
        if (sigla == null
                || sigla.isEmpty()) {
            throw new PersistenciaException("A sigla estado não pode ser nula");
        }
        return estadoDAO.getEstadoBySigla(sigla);
    }

    @Override
    public List<Estado> getAll() throws PersistenciaException {
        return estadoDAO.listAll();
    }
    
}
