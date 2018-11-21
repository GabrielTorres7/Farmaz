/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ExcluirProduto {
    
    public static String executa(HttpServletRequest request) {
        
        String jsp = "";
        
        try {
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            
            manterDisponibilidade.deletarDisponibilidade(Long.parseLong(request.getParameter("CodProduto")));
            
            jsp = ListarProdutosFarmacia.executa(request);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
