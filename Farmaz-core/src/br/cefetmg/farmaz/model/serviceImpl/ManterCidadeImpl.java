/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.CidadeDAOImpl;
import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCidade;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterCidadeImpl implements ManterCidade{
    
    private final CidadeDAOImpl cidadeDAO;

    public ManterCidadeImpl(CidadeDAOImpl cidadeDAO) {
        this.cidadeDAO = cidadeDAO;
    }
    
    @Override
    public Long cadastrarCidade(Cidade cidade) throws PersistenciaException, LogicaNegocioException {
        if (cidade == null) {
            throw new LogicaNegocioException("Cidade não pode ser nula");
        }
        if (cidade.getUfId() == null) {
            throw new LogicaNegocioException("Estado da cidade não pode ser nulo");
        }
        if (cidade.getNome() == null 
                || cidade.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome da cidade não pode ser nulo");
        }
        
        return cidadeDAO.insert(cidade);
    }

    @Override
    public boolean atualizarCidade(Cidade cidade) throws PersistenciaException, LogicaNegocioException {
        if (cidade == null) {
            throw new LogicaNegocioException("Cidade não pode ser nula");
        }
        if (cidade.getCidadeId()== null) {
            throw new LogicaNegocioException("Id da cidade não pode ser nulo");
        }
        if (cidade.getUfId() == null) {
            throw new LogicaNegocioException("Estado da cidade não pode ser nulo");
        }
        if (cidade.getNome() == null 
                || cidade.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome da cidade não pode ser nulo");
        }
        
        return cidadeDAO.update(cidade);
    }

    @Override
    public boolean deletarCidade(Long cidadeId) throws PersistenciaException {
        if (cidadeId == null) {
            throw new PersistenciaException("Id da cidade não pode ser nulo");
        }
        if (cidadeDAO.getCidadeById(cidadeId) == null) {
            throw new PersistenciaException("A cidade com o id " + cidadeId + "não existe");
        }
        return cidadeDAO.remove(cidadeId);
    }

    @Override
    public Cidade getCidadeById(Long cidadeId) throws PersistenciaException {
        if (cidadeId == null) {
            throw new PersistenciaException("O id da cidade não pode ser nulo");
        }
        return cidadeDAO.getCidadeById(cidadeId);
    }
    
    @Override
    public Cidade getCidadeByNome(String nome) throws PersistenciaException {
        if (nome == null
                || nome.isEmpty()) {
            throw new PersistenciaException("O nome da cidade não pode ser nulo");
        }
        return cidadeDAO.getCidadeByNome(nome);
    }
    
    @Override
    public List<Cidade> getAll() throws PersistenciaException {
        return cidadeDAO.listAll();
    }
    
}
