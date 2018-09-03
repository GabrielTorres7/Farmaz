/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterEstado {
    public Long cadastrarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException;
    public boolean atualizarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException;
    public boolean deletarEstado(Long estadoId) throws PersistenciaException;
    public Estado getEstadoById(Long estadoId) throws PersistenciaException;
    public Estado getEstadoBySigla(String sigla) throws PersistenciaException;
    public List<Estado> getAll() throws PersistenciaException;   
}
