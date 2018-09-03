/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterCliente {    
    public Long cadastrarCliente(Cliente cliente) throws PersistenciaException, LogicaNegocioException;
    public boolean atualizarCliente(Cliente cliente) throws PersistenciaException, LogicaNegocioException;
    public boolean deletarCliente(Long clienteId) throws PersistenciaException;
    public Cliente getClienteById(Long clienteId) throws PersistenciaException;
    public Cliente getClienteByEmail(String email) throws PersistenciaException;
    public Cliente getClienteByEmailSenha(String email, String Senha) throws PersistenciaException;
    public List<Cliente> getAll() throws PersistenciaException;
}
