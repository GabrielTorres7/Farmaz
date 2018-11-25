/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Pedido;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.farmaz.proxy.ManterPedidoProxy;

/**
 *
 * @author Gabriel
 */
public class AlterarStatusPedido {
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            ManterPedidoProxy manterPedido = new ManterPedidoProxy();
            Pedido pedido = new Pedido();
            
            String status = request.getParameter("status");
            String pedidoId = request.getParameter("cod");
            
            Long pedidoIdL = Long.parseLong(pedidoId);
            pedido = manterPedido.getPedidoById(pedidoIdL);
            
            if(status.equals("F"))
                pedido.setIdtStatus('F');
            else if(status.equals("P"))
                pedido.setIdtStatus('P');
            else if(status.equals("A"))
                pedido.setIdtStatus('A');
            else if(status.equals("C"))
                pedido.setIdtStatus('C');
            
            manterPedido.atualizarPedido(pedido);
            
            jsp = ListarPedidos.executa(request);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
