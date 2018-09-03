/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterFarmacia {
    public Long cadastrarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException;
    public boolean atualizarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException;
    public boolean deletarFarmacia(String farmaciaId) throws PersistenciaException;
    public Farmacia getFarmaciaById(String farmaciaId) throws PersistenciaException;
    public Farmacia getFarmaciaByEmail(String email) throws PersistenciaException;
    public Farmacia getFarmaciaByEmailSenha(String email, String senha) throws PersistenciaException;
    public List<Farmacia> listAll() throws PersistenciaException;
}
