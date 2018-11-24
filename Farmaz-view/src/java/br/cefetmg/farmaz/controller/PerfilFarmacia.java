/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author User
 */
public class PerfilFarmacia {
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
            Farmacia farmacia;

            farmacia = manterFarmacia.getFarmaciaById((String) request.getSession().getAttribute("cod_farmacia"));

            request.setAttribute("farmacia", farmacia);
            
            jsp = "PerfilFarmacia.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
