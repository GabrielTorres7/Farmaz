/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.util.PacoteDados;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ManterProdutoProxy implements ManterProduto{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterProdutoProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long cadastrarProduto(Produto produto) throws PersistenciaException, LogicaNegocioException {

        PacoteDados pacoteDados;
        
        String requisicao = "CadastrarProduto";
        pacoteDados = new PacoteDados(requisicao, produto);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
          
            return (Long) pacoteRecebido.getObjeto();
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean atualizarProduto(Produto produto) throws PersistenciaException, LogicaNegocioException {
        
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarProduto";
        pacoteDados = new PacoteDados(requisicao, produto);
        
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            if("T".equals(pacoteRecebido.getRequisicao()))
                return true;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deletarProduto(Long produtoId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarProduto";
        pacoteDados = new PacoteDados(requisicao, produtoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
          
            if("T".equals(pacoteRecebido.getRequisicao()))
                return true;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Produto getProdutoById(Long produtoId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        Produto produto = null;
        
        String requisicao = "GetProdutoById";
        pacoteDados = new PacoteDados(requisicao, produtoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            produto = (Produto) pacoteRecebido.getObjeto();
            return produto;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Produto getProdutoByNome(String nome) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        Produto produto = null;
        
        String requisicao = "GetProdutoByNome";
        pacoteDados = new PacoteDados(requisicao, nome);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            produto = (Produto) pacoteRecebido.getObjeto();
            return produto;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Produto> listAll() throws PersistenciaException {
        
        PacoteDados pacoteDados;
        List<Produto> produtos = null;
        
        String requisicao = "GetAllProdutos";
        pacoteDados = new PacoteDados(requisicao);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            produtos = (List<Produto>) pacoteRecebido.getObjeto();
            return produtos;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
