/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.CidadeDAO;
import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.util.bd.ManterConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class CidadeDAOImpl implements CidadeDAO{
    
    private static CidadeDAOImpl cidadeDAO = null;

    private CidadeDAOImpl() {
    }

    public static CidadeDAOImpl getInstance() {
        if (cidadeDAO == null) {
            cidadeDAO = new CidadeDAOImpl();
        }
        return cidadeDAO;
    }
    
    @Override
    public Long insert(Cidade cidade) throws PersistenciaException {
        if (cidade == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long cidadeId = null;

        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "INSERT INTO cidade (cod_uf, nome)"
                    + "    VALUES (?, ?) RETURNING cod_cidade";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cidade.getUfId());
            pstmt.setString(2, cidade.getNome());
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                cidadeId = rs.getLong("cod_cidade");
                cidade.setCidadeId(cidadeId);
            }
            
            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FarmaciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

        return cidadeId;
    }

    @Override
    public boolean update(Cidade cidade) throws PersistenciaException {
        try {

            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "UPDATE cidade "
                    + " SET cod_uf = ?, "
                    + "     nome = ? "
                    + " WHERE cod_cidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cidade.getUfId());
            pstmt.setString(2, cidade.getNome());
            pstmt.setLong(3, cidade.getCidadeId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public boolean remove(Long cidadeId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM cidade WHERE cod_cidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, cidadeId);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Cidade getCidadeById(Long cidadeId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM cidade WHERE cod_cidade = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cidadeId);
            ResultSet rs = pstmt.executeQuery();

            Cidade cidade = null;
            if (rs.next()) {
                cidade = new Cidade();
                cidade.setCidadeId(cidadeId);
                cidade.setUfId(rs.getLong("cod_uf"));
                cidade.setNome(rs.getString("nome"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cidade;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public Cidade getCidadeByNome(String nome) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM cidade WHERE nome = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Cidade cidade = null;
            if (rs.next()) {
                cidade = new Cidade();
                cidade.setCidadeId(rs.getLong("cod_cidade"));
                cidade.setUfId(rs.getLong("cod_uf"));
                cidade.setNome(rs.getString("nome"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cidade;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public List<Cidade> listAll() throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM cidade ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Cidade> listAll = null;
            Cidade cidade = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    cidade = new Cidade();
                    cidade.setCidadeId(rs.getLong("cod_cidade"));
                    cidade.setUfId(rs.getLong("cod_uf"));
                    cidade.setNome(rs.getString("nome"));
                    listAll.add(cidade);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
}
