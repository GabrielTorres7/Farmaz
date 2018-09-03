/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.PedidoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.serviceImpl.ManterPedidoImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class HistoricoCompras {
    
     public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {                    
            ManterPedidoImpl manterPedido = new ManterPedidoImpl(PedidoDAOImpl.getInstance());
            Long idUser = (Long) request.getSession().getAttribute("cod_cliente");
            List<Pedido> historico = manterPedido.getPedidosByClienteId(idUser);
            
            if(historico == null){
                String erro = "Você ainda não realizou nenhum pedido!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
            }else{
                request.setAttribute("Historico", historico);
                jsp = "/HistoricoCompras.jsp";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
