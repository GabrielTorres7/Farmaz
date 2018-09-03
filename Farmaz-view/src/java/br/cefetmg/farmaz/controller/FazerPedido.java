/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.ItemPedidoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.PedidoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.serviceImpl.ManterItemPedidoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterPedidoImpl;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class FazerPedido {
    
     public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {
            String troco = request.getParameter("troco");
            
            if(Double.parseDouble(troco) < (double) request.getSession().getAttribute("Total")){
                String erro = "Troco não pode ser menor que valor do pedido!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
            }else{
                Pedido pedido = new Pedido();
                ItemPedido itemPedido = new ItemPedido();
                ManterItemPedidoImpl manterItemPedido = new ManterItemPedidoImpl(ItemPedidoDAOImpl.getInstance());
                ManterPedidoImpl manterPedido = new ManterPedidoImpl(PedidoDAOImpl.getInstance());
                List<Disponibilidade> carrinho = (List<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
                Long pedidoId;

                pedido.setClienteId((Long) request.getSession().getAttribute("cod_cliente"));
                pedido.setFarmaciaId(carrinho.get(0).getFarmaciaCadastro());
                pedido.setDataHora(new Date());
                pedido.setIdtStatus('F');
                pedido.setTroco(Integer.parseInt(request.getParameter("troco")));
                pedido.setValor((double) request.getSession().getAttribute("Total"));

                pedidoId = manterPedido.criarPedido(pedido);

                for(Disponibilidade disp: carrinho){
                    itemPedido.setPedidoId(pedidoId);
                    itemPedido.setProdutoId(disp.getProdutoSeq());
                    itemPedido.setQuantidade(disp.getEstoque());
                    manterItemPedido.inserirItemPedido(itemPedido);
                }

                request.getSession().removeAttribute("Total");
                request.getSession().removeAttribute("MeuCarrinho");
                request.removeAttribute("disponibilidadeId");

                jsp=ListarProdutosCliente.executa(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
