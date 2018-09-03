/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterProduto {
    public Long cadastrarProduto(Produto produto) throws PersistenciaException, LogicaNegocioException;
    public boolean atualizarProduto(Produto produto) throws PersistenciaException, LogicaNegocioException;
    public boolean deletarProduto(Long produtoId) throws PersistenciaException;
    public Produto getProdutoById(Long produtoId) throws PersistenciaException;
    public List<Produto> listAll() throws PersistenciaException;
    public Produto getProdutoByNome(String nomeProduto) throws PersistenciaException;
}
