/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.proxy.ManterPedidoProxy;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class HistoricoVendas {
     public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {                    
            ManterPedidoProxy manterPedido = new ManterPedidoProxy();
            String idFarmacia = (String) request.getSession().getAttribute("cod_farmacia");
            List<Pedido> historico = manterPedido.getPedidosByFarmaciaIdAndStatus(Long.parseLong(idFarmacia), "C");
            
            request.setAttribute("Historico", historico);
            jsp = "/HistoricoVendas.jsp";

            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
