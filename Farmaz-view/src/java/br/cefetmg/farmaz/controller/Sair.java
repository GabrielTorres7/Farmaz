/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class Sair {
    
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            request.getSession().invalidate();

            jsp = "Login.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
