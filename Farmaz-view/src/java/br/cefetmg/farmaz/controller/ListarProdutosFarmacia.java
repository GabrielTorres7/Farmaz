/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarProdutosFarmacia {
    
    public static String executa(HttpServletRequest request) {
        String jsp;
        
        try {    
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            List<Disponibilidade> listDisponibilidade;
            
            listDisponibilidade = manterDisponibilidade.getDisponibilidadeByFarmaciaId((String) request.getSession().getAttribute("cod_farmacia"));
            
            request.setAttribute("produtos", listDisponibilidade);
   
            jsp = "ListarProdutosFarmacia.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
