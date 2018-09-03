/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface EnderecoDAO {
    public Long insert(Endereco endereco) throws PersistenciaException;
    public boolean update(Endereco endereco) throws PersistenciaException;
    public boolean remove(Long enderecoId) throws PersistenciaException;
    public Endereco getEnderecoById(Long enderecoId) throws PersistenciaException;
    public List<Endereco> getEnderecosByClienteId(Long clienteId) throws PersistenciaException;
}
