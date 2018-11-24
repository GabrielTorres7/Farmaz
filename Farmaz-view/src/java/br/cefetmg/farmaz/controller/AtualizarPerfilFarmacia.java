/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.proxy.ManterCidadeProxy;
import br.cefetmg.farmaz.proxy.ManterEstadoProxy;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class AtualizarPerfilFarmacia {
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cadastro = request.getParameter("cadastro_prefeitura");
            String cnpj = request.getParameter("cnpj");
            String senhaAtual = request.getParameter("senhaAtual");
            String senhaNova = request.getParameter("senhaNova");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            
            ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
            ManterEstadoProxy manterEstado = new ManterEstadoProxy();
            ManterCidadeProxy manterCidade = new ManterCidadeProxy();
            
            Farmacia farmacia = new Farmacia();
            Cidade cidadeDominio = new Cidade();
            
            if(manterFarmacia.getFarmaciaById(cadastro) != null 
                    && !manterFarmacia.getFarmaciaById(cadastro).getCadastroPrefeitura().equals((String) request.getSession().getAttribute("cod_farmacia"))){
                String erro = "Farmacia já existente com esse cadastro!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
                return jsp;
            }
            if(!cadastro.equals((String) request.getSession().getAttribute("cod_farmacia"))){
                request.getSession().setAttribute("cod_farmacia", cadastro);
            }
            farmacia.setCadastroPrefeitura(cadastro);
            farmacia.setNome(nome);
            farmacia.setCnpj(cnpj);
            farmacia.setEmail(email);
            farmacia.setBairro(bairro);
            farmacia.setCep(cep);
            farmacia.setRua(rua);
            farmacia.setNumero(numero);
            farmacia.setCodUf(manterEstado.getEstadoBySigla(estado).getId());
            if(manterCidade.getCidadeByNome(cidade) == null){
                cidadeDominio.setNome(cidade);
                cidadeDominio.setUfId(manterEstado.getEstadoBySigla(estado).getId());
                manterCidade.cadastrarCidade(cidadeDominio);
            }else{
                cidadeDominio = manterCidade.getCidadeByNome(cidade);
            }
            farmacia.setCodCidade(cidadeDominio.getCidadeId());
                        
            if(manterFarmacia.getFarmaciaByEmailSenha(email, senhaAtual) == null
                    || !(manterFarmacia.getFarmaciaByEmailSenha(email, senhaAtual).getCadastroPrefeitura().equals(farmacia.getCadastroPrefeitura()))){
                String erro = "Senha inválida!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
                return jsp;
            }else{
                if(senhaNova.equals(null) || senhaNova.equals("")){
                    farmacia.setSenha(senhaAtual);
                }else{
                    farmacia.setSenha(senhaNova);
                }
            }
            manterFarmacia.atualizarFarmacia(farmacia);
            
            jsp = ListarProdutosFarmacia.executa(request);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
