/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterDisponibilidadeImpl implements ManterDisponibilidade{
    
    private final DisponibilidadeDAOImpl disponibilidadeDAO;

    public ManterDisponibilidadeImpl(DisponibilidadeDAOImpl disponibilidadeDAO) {
        this.disponibilidadeDAO = disponibilidadeDAO;
    }
    
    @Override
    public Long inserirDisponibilidade(Disponibilidade disponibilidade) throws PersistenciaException, LogicaNegocioException {
        if (disponibilidade == null) {
            throw new LogicaNegocioException("Disponibilidade não pode ser nula");
        }
        if (disponibilidade.getProdutoSeq() == null) {
            throw new LogicaNegocioException("Id do produto de disponibilidade não pode ser nulo");
        }
        if (disponibilidade.getFarmaciaCadastro() == null
                || disponibilidade.getFarmaciaCadastro().isEmpty()) {
            throw new LogicaNegocioException("Id da farmácia de disponibilidade não pode ser nulo");
        }
        
        return disponibilidadeDAO.insert(disponibilidade);
    }

    @Override
    public boolean atualizarDisponibilidade(Disponibilidade disponibilidade) throws PersistenciaException, LogicaNegocioException {
        if (disponibilidade == null) {
            throw new LogicaNegocioException("Disponibilidade não pode ser nula");
        }
        if (disponibilidade.getId() == null){
            throw new LogicaNegocioException("Id de disponibilidade não pode ser nulo");
        }
        if (disponibilidade.getProdutoSeq() == null) {
            throw new LogicaNegocioException("Id do produto de disponibilidade não pode ser nulo");
        }
        if (disponibilidade.getFarmaciaCadastro() == null
                || disponibilidade.getFarmaciaCadastro().isEmpty()) {
            throw new LogicaNegocioException("Id da farmácia de disponibilidade não pode ser nulo");
        }
        
        return disponibilidadeDAO.update(disponibilidade);
    }

    @Override
    public boolean deletarDisponibilidade(Long disponibilidadeId) throws PersistenciaException {
        if (disponibilidadeId == null) {
            throw new PersistenciaException("Id de disponibilidade não pode ser nulo");
        }
        if (disponibilidadeDAO.getDisponibilidadeById(disponibilidadeId) == null) {
            throw new PersistenciaException("A disponibilidade com o id " + disponibilidadeId + "não existe");
        }
        return disponibilidadeDAO.remove(disponibilidadeId);
    }

    @Override
    public Disponibilidade getDisponibilidadeById(Long disponibilidadeId) throws PersistenciaException {
        if (disponibilidadeId == null) {
            throw new PersistenciaException("O id de disponibilidade não pode ser nulo");
        }
        return disponibilidadeDAO.getDisponibilidadeById(disponibilidadeId);
    }

    @Override
    public List<Disponibilidade> getDisponibilidadeByProdutoId(Long produtoId) throws PersistenciaException {
        if (produtoId == null) {
            throw new PersistenciaException("O id do produto de disponibilidade não pode ser nulo");
        }
        return disponibilidadeDAO.getDisponibilidadeByProdutoId(produtoId);
    }

    @Override
    public List<Disponibilidade> getDisponibilidadeByFarmaciaId(String farmaciaId) throws PersistenciaException {
        if (farmaciaId == null
                || farmaciaId.isEmpty()) {
            throw new PersistenciaException("O id da farmacia de disponibilidade não pode ser nulo");
        }
        return disponibilidadeDAO.getDisponibilidadeByFarmaciaId(farmaciaId);
    }
    
}
