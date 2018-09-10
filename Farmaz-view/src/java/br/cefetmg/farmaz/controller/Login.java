/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.proxy.ManterClienteProxy;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class Login {

    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            String email;
            String senha;

            if (request.getAttribute("tipo") != null && request.getAttribute("tipo").equals("cadastro")) {
                email = (String) request.getAttribute("email");
                senha = (String) request.getAttribute("senha");
            } else {
                email = request.getParameter("email");
                senha = request.getParameter("senha");
            }

            ManterClienteProxy manterCliente = new ManterClienteProxy();
            ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
            Cliente cliente = manterCliente.getClienteByEmailSenha(email, senha);
            Farmacia farmacia = manterFarmacia.getFarmaciaByEmailSenha(email, senha);

            if (cliente == null && farmacia == null) {
                String erro = "Cadastro não encontrado!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
            } else if (cliente != null) {
                request.getSession().setAttribute("cod_cliente", cliente.getId());
                jsp = ListarProdutosCliente.executa(request);
            } else if (farmacia != null) {
              //  jsp = ListarProdutosFarmacia.executa(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
