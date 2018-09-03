/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.FarmaciaDAOImpl;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterFarmaciaImpl implements ManterFarmacia{
    
    private final FarmaciaDAOImpl farmaciaDAO;

    public ManterFarmaciaImpl(FarmaciaDAOImpl farmaciaDAO) {
        this.farmaciaDAO = farmaciaDAO;
    }
    
    @Override
    public Long cadastrarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException {
        if (farmacia == null) {
            throw new LogicaNegocioException("Farmacia não pode ser nula");
        }
        if (farmacia.getCadastroPrefeitura() == null) {
            throw new LogicaNegocioException("Id da farmacia não pode ser nulo");
        }
        if (farmacia.getCodCidade() == null) {
            throw new LogicaNegocioException("Cidade da farmacia não pode ser nula");
        }
        if (farmacia.getCnpj() == null 
                || farmacia.getCnpj().isEmpty()) {
            throw new LogicaNegocioException("Cnpj da farmacia não pode ser nulo");
        }
        if (farmacia.getNome() == null
                || farmacia.getNome().isEmpty()) {
            throw new LogicaNegocioException("O nome da farmacia não pode ser nulo");
        }
        if (farmacia.getBairro() == null
                || farmacia.getBairro().isEmpty()) {
            throw new LogicaNegocioException("O bairro da farmacia não pode ser nulo");
        }
        if (farmacia.getRua() == null
                || farmacia.getRua().isEmpty()) {
            throw new LogicaNegocioException("A rua da farmacia não pode ser nula");
        }
        if (farmacia.getEmail() == null
                || farmacia.getEmail().isEmpty()) {
            throw new LogicaNegocioException("O email da farmacia não pode ser nulo");
        }
        if (farmacia.getSenha() == null
                || farmacia.getSenha().isEmpty()) {
            throw new LogicaNegocioException("A senha da farmacia não pode ser nula");
        }
        
        return farmaciaDAO.insert(farmacia);
    }

    @Override
    public boolean atualizarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException {
        if (farmacia == null) {
            throw new LogicaNegocioException("Farmacia não pode ser nula");
        }
        if (farmacia.getCadastroPrefeitura() == null) {
            throw new LogicaNegocioException("Id da farmacia não pode ser nulo");
        }
        if (farmacia.getCodCidade() == null) {
            throw new LogicaNegocioException("Cidade da farmacia não pode ser nula");
        }
        if (farmacia.getCnpj() == null 
                || farmacia.getCnpj().isEmpty()) {
            throw new LogicaNegocioException("Cnpj da farmacia não pode ser nulo");
        }
        if (farmacia.getNome() == null
                || farmacia.getNome().isEmpty()) {
            throw new LogicaNegocioException("O nome da farmacia não pode ser nulo");
        }
        if (farmacia.getBairro() == null
                || farmacia.getBairro().isEmpty()) {
            throw new LogicaNegocioException("O bairro da farmacia não pode ser nulo");
        }
        if (farmacia.getRua() == null
                || farmacia.getRua().isEmpty()) {
            throw new LogicaNegocioException("A rua da farmacia não pode ser nula");
        }
        if (farmacia.getEmail() == null
                || farmacia.getEmail().isEmpty()) {
            throw new LogicaNegocioException("O email da farmacia não pode ser nulo");
        }
        if (farmacia.getSenha() == null
                || farmacia.getSenha().isEmpty()) {
            throw new LogicaNegocioException("A senha da farmacia não pode ser nula");
        }
        
        return farmaciaDAO.update(farmacia);
    }

    @Override
    public boolean deletarFarmacia(String farmaciaId) throws PersistenciaException {
        if (farmaciaId == null) {
            throw new PersistenciaException("Id da farmacia não pode ser nulo");
        }
        if (farmaciaDAO.getFarmaciaById(farmaciaId) == null
                || farmaciaId.isEmpty()) {
            throw new PersistenciaException("A farmacia com o id " + farmaciaId + "não existe");
        }
        return farmaciaDAO.remove(farmaciaId);
    }

    @Override
    public Farmacia getFarmaciaById(String farmaciaId) throws PersistenciaException {
        if (farmaciaId == null
                || farmaciaId.isEmpty()) {
            throw new PersistenciaException("O id da farmacia não pode ser nulo");
        }
        return farmaciaDAO.getFarmaciaById(farmaciaId);
    }
    
    @Override
    public Farmacia getFarmaciaByEmail(String email) throws PersistenciaException {
        if (email == null || email.isEmpty()) {
            throw new PersistenciaException("O email da farmacia não pode ser nulo");
        } 
        return farmaciaDAO.getFarmaciaByEmail(email);
    }

    @Override
    public Farmacia getFarmaciaByEmailSenha(String email, String senha) throws PersistenciaException {
        if (email == null || email.isEmpty()) {
            throw new PersistenciaException("O email da farmacia não pode ser nulo");
        }
        if (senha == null || senha.isEmpty()) {
            throw new PersistenciaException("A senha da farmacia não pode ser nula");
        }
        return farmaciaDAO.getFarmaciaByEmailSenha(email, senha);
    }
    
    @Override
    public List<Farmacia> listAll() throws PersistenciaException {
        return farmaciaDAO.listAll();
    }
    
}
