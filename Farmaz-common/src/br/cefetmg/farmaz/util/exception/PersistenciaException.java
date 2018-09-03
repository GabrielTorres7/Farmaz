/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.exception;

/**
 *
 * @author Gabriel
 */
public class PersistenciaException extends Exception{
    
    public PersistenciaException(String message){
        super(message);
    }
    
    public PersistenciaException(Exception exception){
        super(exception);
    }
    
}
