/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterCidadeProxy;
import br.cefetmg.farmaz.proxy.ManterEstadoProxy;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class CadastrarFarmacia {
    
     public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {
            Cidade cidadeDominio = new Cidade();
            Estado estadoDominio = new Estado();
            Farmacia farmacia;
            ManterFarmaciaProxy manterFarmacia;
            ManterCidadeProxy manterCidade = new ManterCidadeProxy();
            ManterEstadoProxy manterEstado = new ManterEstadoProxy();
                            
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cadastroPrefeitura = request.getParameter("cadastro_prefeitura");
            String cnpj = request.getParameter("cnpj");
            String senha = request.getParameter("senha");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            String numero = request.getParameter("numero");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            
            estadoDominio = manterEstado.getEstadoBySigla(estado);
            Long cidadeId = null;
            
            if(manterCidade.getCidadeByNome(cidade) == null){
                cidadeDominio.setNome(cidade);
                cidadeDominio.setUfId(estadoDominio.getId());
                cidadeId = manterCidade.cadastrarCidade(cidadeDominio);
            }else{
                cidadeDominio = manterCidade.getCidadeByNome(cidade);
            }
            
            farmacia = new Farmacia();
            farmacia.setNome(nome);
            farmacia.setEmail(email);
            farmacia.setSenha(senha);
            farmacia.setCadastroPrefeitura(cadastroPrefeitura);
            farmacia.setCnpj(cnpj);
            farmacia.setCep(cep);
            farmacia.setRua(rua);
            farmacia.setNumero(Integer.parseInt(numero));
            farmacia.setBairro(bairro);
            farmacia.setCodUf(estadoDominio.getId());
            if(cidadeId == null)
                farmacia.setCodCidade(cidadeDominio.getCidadeId());
            else
                farmacia.setCodCidade(cidadeId);
                
            manterFarmacia = new ManterFarmaciaProxy();
            manterFarmacia.cadastrarFarmacia(farmacia);
            
            request.setAttribute("email", email);
            request.setAttribute("senha", senha);
            request.setAttribute("tipo", "cadastro");
            
            jsp = Login.executa(request);

        } catch (PersistenciaException | LogicaNegocioException ex) {
             System.out.println(ex);
             jsp = "";
        }
        
        return jsp;

    }
}
