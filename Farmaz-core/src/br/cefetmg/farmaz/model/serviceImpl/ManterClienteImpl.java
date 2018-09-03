/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCliente;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterClienteImpl implements ManterCliente{
    
    private final ClienteDAOImpl clienteDAO;

    public ManterClienteImpl(ClienteDAOImpl clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    @Override
    public Long cadastrarCliente(Cliente cliente) throws PersistenciaException, LogicaNegocioException {
        if (cliente == null) {
            throw new LogicaNegocioException("Cliente não pode ser nulo");
        }
        if (cliente.getNome() == null
                || cliente.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome do cliente não pode ser nulo");
        }
        if (cliente.getDocumentoIdentificacao() == null 
                || cliente.getDocumentoIdentificacao().isEmpty()) {
            throw new LogicaNegocioException("Documento de identificação do cliente não pode ser nulo");
        }
        if (cliente.getEmail() == null
                || cliente.getEmail().isEmpty()) {
            throw new LogicaNegocioException("O email do cliente não pode ser nulo");
        }
        if (cliente.getSenha() == null
                || cliente.getSenha().isEmpty()) {
            throw new LogicaNegocioException("A senha do cliente não pode ser nula");
        }
        return clienteDAO.insert(cliente);
    }

    @Override
    public boolean atualizarCliente(Cliente cliente) throws PersistenciaException, LogicaNegocioException {
        if (cliente == null) {
            throw new LogicaNegocioException("Cliente não pode ser nulo");
        }
        if (cliente.getId() == null) {
            throw new LogicaNegocioException("Id do cliente não pode ser nulo");
        }
        if (cliente.getNome() == null
                || cliente.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome do cliente não pode ser nulo");
        }
        if (cliente.getDocumentoIdentificacao() == null 
                || cliente.getDocumentoIdentificacao().isEmpty()) {
            throw new LogicaNegocioException("Documento de identificação do cliente não pode ser nulo");
        }
        if (cliente.getEmail() == null
                || cliente.getEmail().isEmpty()) {
            throw new LogicaNegocioException("O email do cliente não pode ser nulo");
        }
        if (cliente.getSenha() == null
                || cliente.getSenha().isEmpty()) {
            throw new LogicaNegocioException("A senha do cliente não pode ser nula");
        }
        return clienteDAO.update(cliente);
    }

    @Override
    public boolean deletarCliente(Long clienteId) throws PersistenciaException {
        if (clienteId == null) {
            throw new PersistenciaException("Id do cliente não pode ser nulo");
        }
        if (clienteDAO.getClienteById(clienteId) == null) {
            throw new PersistenciaException("O cliente com o id " + clienteId + "não existe");
        }
        return clienteDAO.remove(clienteId);
    }

    @Override
    public Cliente getClienteById(Long clienteId) throws PersistenciaException {
        if (clienteId == null) {
            throw new PersistenciaException("O id do cliente não pode ser nulo");
        }
        return clienteDAO.getClienteById(clienteId);
    }

    @Override
    public Cliente getClienteByEmail(String email) throws PersistenciaException {
        if (email == null || email.isEmpty()) {
            throw new PersistenciaException("O email do cliente não pode ser nulo");
        } 
        return clienteDAO.getClienteByEmail(email);
    }
    
    @Override
    public Cliente getClienteByEmailSenha(String email, String senha) throws PersistenciaException {
        if (email == null || email.isEmpty()) {
            throw new PersistenciaException("O email do cliente não pode ser nulo");
        }
        if (senha == null || senha.isEmpty()) {
            throw new PersistenciaException("A senha do cliente não pode ser nula");
        }
        return clienteDAO.getClienteByEmailSenha(email, senha);
    }
    
    @Override
    public List<Cliente> getAll() throws PersistenciaException {
        return clienteDAO.listAll();
    }
    
}
