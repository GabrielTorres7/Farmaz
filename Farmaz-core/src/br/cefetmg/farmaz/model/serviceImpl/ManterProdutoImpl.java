/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterProduto;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterProdutoImpl implements ManterProduto{
    
    private final ProdutoDAOImpl produtoDAO;

    public ManterProdutoImpl(ProdutoDAOImpl produtoDAO) {
        this.produtoDAO = produtoDAO;
    }
    
    @Override
    public Long cadastrarProduto(Produto produto) throws PersistenciaException, LogicaNegocioException {
        if (produto == null) {
            throw new LogicaNegocioException("Produto não pode ser nulo");
        }
        if (produto.getNome() == null
                || produto.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome do produto não pode ser nulo");
        }
        if (produto.getDescricao() == null 
                || produto.getDescricao().isEmpty()) {
            throw new LogicaNegocioException("Descricao do produto não pode ser nulo");
        }
        
        return produtoDAO.insert(produto);
    }

    @Override
    public boolean atualizarProduto(Produto produto) throws PersistenciaException, LogicaNegocioException {
        if (produto == null) {
            throw new LogicaNegocioException("Produto não pode ser nulo");
        }
        if (produto.getId() == null) {
            throw new LogicaNegocioException("Id do produto não pode ser nulo");
        }
        if (produto.getNome() == null
                || produto.getNome().isEmpty()) {
            throw new LogicaNegocioException("Nome do produto não pode ser nulo");
        }
        if (produto.getDescricao() == null 
                || produto.getDescricao().isEmpty()) {
            throw new LogicaNegocioException("Descricao do produto não pode ser nulo");
        }
        
        return produtoDAO.update(produto);
    }

    @Override
    public boolean deletarProduto(Long produtoId) throws PersistenciaException {
        if (produtoId == null) {
            throw new PersistenciaException("Id do produto não pode ser nulo");
        }
        if (produtoDAO.getProdutoById(produtoId) == null) {
            throw new PersistenciaException("O produto com o id " + produtoId + "não existe");
        }
        return produtoDAO.remove(produtoId);
    }

    @Override
    public Produto getProdutoById(Long produtoId) throws PersistenciaException {
        if (produtoId == null) {
            throw new PersistenciaException("O id do produto não pode ser nulo");
        }
        return produtoDAO.getProdutoById(produtoId);
    }

    @Override
    public List<Produto> listAll() throws PersistenciaException {
        return produtoDAO.listAll();
    }

    @Override
    public Produto getProdutoByNome(String nomeProduto) throws PersistenciaException {
        if (nomeProduto == null) {
            throw new PersistenciaException("O nome do produto não pode ser nulo");
        }
        return produtoDAO.getProdutoByNome(nomeProduto);
    }
    
}
