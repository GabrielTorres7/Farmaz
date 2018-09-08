/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
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
public class ManterDisponibilidadeProxy implements ManterDisponibilidade{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterDisponibilidadeProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long inserirDisponibilidade(Disponibilidade disponibilidade) throws PersistenciaException, LogicaNegocioException {

        PacoteDados pacoteDados;
        
        String requisicao = "InserirDisponibilidade";
        pacoteDados = new PacoteDados(requisicao, disponibilidade);
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
    public boolean atualizarDisponibilidade(Disponibilidade disponibilidade) throws PersistenciaException, LogicaNegocioException {
        
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarDisponibilidade";
        pacoteDados = new PacoteDados(requisicao, disponibilidade);
        
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
    public boolean deletarDisponibilidade(Long disponibilidadeId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarDisponibilidade";
        pacoteDados = new PacoteDados(requisicao, disponibilidadeId);
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
    public Disponibilidade getDisponibilidadeById(Long disponibilidadeId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        Disponibilidade disponibilidade = null;
        
        String requisicao = "GetDisponibilidadeById";
        pacoteDados = new PacoteDados(requisicao, disponibilidadeId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            disponibilidade = (Disponibilidade) pacoteRecebido.getObjeto();
            return disponibilidade;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Disponibilidade> getDisponibilidadeByProdutoId(Long produtoId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        List<Disponibilidade> produtos = null;
        
        String requisicao = "GetDisponibilidadeByProdutoId";
        pacoteDados = new PacoteDados(requisicao, produtoId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            produtos = (List<Disponibilidade>) pacoteRecebido.getObjeto();
            return produtos;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Disponibilidade> getDisponibilidadeByFarmaciaId(String farmaciaId) throws PersistenciaException {
        
        PacoteDados pacoteDados;
        List<Disponibilidade> produtosFarmacia = null;
        
        String requisicao = "GetDisponibilidadeByFarmaciaId";
        pacoteDados = new PacoteDados(requisicao, farmaciaId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            produtosFarmacia = (List<Disponibilidade>) pacoteRecebido.getObjeto();
            return produtosFarmacia;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
