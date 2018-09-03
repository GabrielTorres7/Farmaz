/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ProdutoDAO {
    public Long insert(Produto produto) throws PersistenciaException;
    public boolean update(Produto produto) throws PersistenciaException;
    public boolean remove(Long produtoId) throws PersistenciaException;
    public Produto getProdutoById(Long produtoId) throws PersistenciaException;
    public List<Produto> listAll() throws PersistenciaException;
    public Produto getProdutoByNome(String nomeProduto) throws PersistenciaException;
}
