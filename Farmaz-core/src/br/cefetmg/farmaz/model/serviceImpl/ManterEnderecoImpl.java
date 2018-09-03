/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.EnderecoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterEndereco;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterEnderecoImpl implements ManterEndereco{
    
    private final EnderecoDAOImpl enderecoDAO;

    public ManterEnderecoImpl(EnderecoDAOImpl enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    @Override
    public Long inserirEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException {
        if (endereco == null) {
            throw new LogicaNegocioException("Endereco não pode ser nulo");
        }
        if (endereco.getClienteId() == null) {
            throw new LogicaNegocioException("id do cliente do endereco não pode ser nulo");
        }
        if (endereco.getCodCidade() == null) {
            throw new LogicaNegocioException("Codigo da cidade do endereco não pode ser nulo");
        }
        if (endereco.getCodUf() == null) {
            throw new LogicaNegocioException("Codigo da uf do endereco não pode ser nulo");
        }
        if (endereco.getBairro() == null
                || endereco.getBairro().isEmpty()) {
            throw new LogicaNegocioException("O bairro do endereco não pode ser nulo");
        }
        if (endereco.getRua() == null
                || endereco.getRua().isEmpty()) {
            throw new LogicaNegocioException("A rua do endereco não pode ser nula");
        }
        
        return enderecoDAO.insert(endereco);
    }

    @Override
    public boolean atualizarEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException {
        if (endereco == null) {
            throw new LogicaNegocioException("Endereco não pode ser nulo");
        }
        if (endereco.getEnderecoId() == null) {
            throw new LogicaNegocioException("Id do endereco não pode ser nulo");
        }
        if (endereco.getClienteId() == null) {
            throw new LogicaNegocioException("id do cliente do endereco não pode ser nulo");
        }
        if (endereco.getCodCidade() == null) {
            throw new LogicaNegocioException("Codigo da cidade do endereco não pode ser nulo");
        }
        if (endereco.getCodUf() == null) {
            throw new LogicaNegocioException("Codigo da uf do endereco não pode ser nulo");
        }
        if (endereco.getBairro() == null
                || endereco.getBairro().isEmpty()) {
            throw new LogicaNegocioException("O bairro do endereco não pode ser nulo");
        }
        if (endereco.getRua() == null
                || endereco.getRua().isEmpty()) {
            throw new LogicaNegocioException("A rua do endereco não pode ser nula");
        }
        
        return enderecoDAO.update(endereco);
    }

    @Override
    public boolean deletarEndereco(Long enderecoId) throws PersistenciaException {
        if (enderecoId == null) {
            throw new PersistenciaException("Id do endereco não pode ser nulo");
        }
        if (enderecoDAO.getEnderecoById(enderecoId) == null) {
            throw new PersistenciaException("O endereco com o id " + enderecoId + "não existe");
        }
        return enderecoDAO.remove(enderecoId);
    }

    @Override
    public Endereco getEnderecoById(Long enderecoId) throws PersistenciaException {
        if (enderecoId == null) {
            throw new PersistenciaException("O id do endereco não pode ser nulo");
        }
        return enderecoDAO.getEnderecoById(enderecoId);
    }

    @Override
    public List<Endereco> getEnderecosByClienteId(Long clienteId) throws PersistenciaException {
        if (clienteId == null) {
            throw new PersistenciaException("O id do cliente não pode ser nulo");
        }
        return enderecoDAO.getEnderecosByClienteId(clienteId);
    }
    
}
