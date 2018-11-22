/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.proxy.ManterClienteProxy;
import br.cefetmg.farmaz.proxy.ManterEnderecoProxy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class PerfilCliente {
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            ManterClienteProxy manterCliente = new ManterClienteProxy();
            ManterEnderecoProxy manterEndereco = new ManterEnderecoProxy();
            Cliente cliente;
            Endereco endereco;

            cliente = manterCliente.getClienteById( (Long) request.getSession().getAttribute("cod_cliente"));
            endereco = manterEndereco.getEnderecosByClienteId(cliente.getId()).get(0);

            request.setAttribute("cliente", cliente);
            request.setAttribute("endereco", endereco);

            jsp = "PerfilCliente.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
