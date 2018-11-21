/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.proxy.ManterPedidoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarPedidos {
    
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            ManterPedidoProxy manterPedido = new ManterPedidoProxy();
            String farmaciaId = (String) request.getSession().getAttribute("cod_farmacia");
            
            List<Pedido> listPedido = manterPedido.getPedidosByFarmaciaId(Long.parseLong(farmaciaId));
            
            if(listPedido != null){
                for(int i=0; i<listPedido.size(); i++){
                    if(listPedido.get(i).getIdtStatus() == 'C')
                        listPedido.remove(i);
                }
            }
            request.setAttribute("pedidos", listPedido);
            
            jsp = "ListarPedidos.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
