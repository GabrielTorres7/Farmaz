/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.util.bd;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class ManterConexao {
    
    private static ManterConexao conexao;
    private static ConnectionFactory cf;

    private ManterConexao() {
         ManterConexao.cf = new ConexaoPostgreSQL();
    }

    public static ManterConexao getInstance() {
        if(conexao == null)
            conexao = new ManterConexao();

        return conexao;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        return ManterConexao.cf.getConnection();
    }
}
