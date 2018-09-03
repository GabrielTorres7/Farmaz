/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ClienteDAO {
    public Long insert(Cliente cliente) throws PersistenciaException;
    public boolean update(Cliente cliente) throws PersistenciaException;
    public boolean remove(Long clienteId) throws PersistenciaException;
    public Cliente getClienteById(Long clienteId) throws PersistenciaException;
    public Cliente getClienteByEmail(String email) throws PersistenciaException;
    public Cliente getClienteByEmailSenha(String email, String senha) throws PersistenciaException;
    public List<Cliente> listAll() throws PersistenciaException;
}
