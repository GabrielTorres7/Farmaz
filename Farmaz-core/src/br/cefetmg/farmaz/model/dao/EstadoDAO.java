/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface EstadoDAO {
    public Long insert(Estado estado) throws PersistenciaException;
    public boolean update(Estado estado) throws PersistenciaException;
    public boolean remove(Long estadoId) throws PersistenciaException;
    public Estado getEstadoById(Long estadoId) throws PersistenciaException;
    public Estado getEstadoBySigla(String sigla) throws PersistenciaException;
    public List<Estado> listAll() throws PersistenciaException;
}
