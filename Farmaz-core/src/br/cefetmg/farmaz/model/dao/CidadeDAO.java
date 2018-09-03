/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface CidadeDAO {
    public Long insert(Cidade cidade) throws PersistenciaException;
    public boolean update(Cidade cidade) throws PersistenciaException;
    public boolean remove(Long cidadeId) throws PersistenciaException;
    public Cidade getCidadeById(Long cidadeId) throws PersistenciaException;
    public Cidade getCidadeByNome(String nome) throws PersistenciaException;
    public List<Cidade> listAll() throws PersistenciaException;
}
