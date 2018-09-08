/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.proxy;

import br.cefetmg.farmaz.client.ClienteDistribuicao;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
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
public class ManterFarmaciaProxy implements ManterFarmacia{
    
    private final ClienteDistribuicao clienteDistribuicao;

    public ManterFarmaciaProxy() throws SocketException, UnknownHostException {
        this.clienteDistribuicao = ClienteDistribuicao.getInstance();
    }
    
    @Override
    public Long cadastrarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "CadastrarFarmacia";
        pacoteDados = new PacoteDados(requisicao, farmacia);
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
    public boolean atualizarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException {
        PacoteDados pacoteDados;
        
        String requisicao = "AtualizarFarmacia";
        pacoteDados = new PacoteDados(requisicao, farmacia);
        
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
    public boolean deletarFarmacia(String farmaciaId) throws PersistenciaException {
        PacoteDados pacoteDados;
        
        String requisicao = "DeletarFarmacia";
        pacoteDados = new PacoteDados(requisicao, farmaciaId);
        
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
    public Farmacia getFarmaciaById(String farmaciaId) throws PersistenciaException {
        PacoteDados pacoteDados;
        Farmacia farmacia = null;
        
        String requisicao = "GetFarmaciaById";
        pacoteDados = new PacoteDados(requisicao, farmaciaId);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            farmacia = (Farmacia) pacoteRecebido.getObjeto();
            return farmacia;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Farmacia getFarmaciaByEmail(String email) throws PersistenciaException {
        PacoteDados pacoteDados;
        Farmacia farmacia = null;
        
        String requisicao = "GetFarmaciaByEmail";
        pacoteDados = new PacoteDados(requisicao, email);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            farmacia = (Farmacia) pacoteRecebido.getObjeto();
            return farmacia;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Farmacia getFarmaciaByEmailSenha(String email, String senha) throws PersistenciaException {
        PacoteDados pacoteDados;
        Farmacia farmacia = null;
        
        String requisicao = "GetFarmaciaByEmailSenha";
        String[] dados = new String[2];
        dados[0] = email;
        dados[1] = senha;
        
        pacoteDados = new PacoteDados(requisicao, dados);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            farmacia = (Farmacia) pacoteRecebido.getObjeto();
            return farmacia;
            
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Farmacia> listAll() throws PersistenciaException {
        PacoteDados pacoteDados;
        List<Farmacia> farmacias = null;
        
        String requisicao = "GetAllFarmacias";
        pacoteDados = new PacoteDados(requisicao);
        try {
            PacoteDados pacoteRecebido = clienteDistribuicao.requisicao(pacoteDados);
            
            farmacias = (List<Farmacia>) pacoteRecebido.getObjeto();
            return farmacias;
   
        } catch (IOException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterClienteProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
