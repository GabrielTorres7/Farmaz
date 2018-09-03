/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterCidade {
    public Long cadastrarCidade(Cidade cidade) throws PersistenciaException, LogicaNegocioException;
    public boolean atualizarCidade(Cidade cidade) throws PersistenciaException, LogicaNegocioException;
    public boolean deletarCidade(Long cidadeId) throws PersistenciaException;
    public Cidade getCidadeById(Long cidadeId) throws PersistenciaException;
    public Cidade getCidadeByNome(String nome) throws PersistenciaException;
    public List<Cidade> getAll() throws PersistenciaException;
}
