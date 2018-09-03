/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.EstadoDAO;
import br.cefetmg.farmaz.model.dominio.Estado;
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
public class EstadoDAOImpl implements EstadoDAO{
    
    private static EstadoDAOImpl estadoDAO = null;

    private EstadoDAOImpl() {
    }

    public static EstadoDAOImpl getInstance() {
        if (estadoDAO == null) {
            estadoDAO = new EstadoDAOImpl();
        }
        return estadoDAO;
    }
    
    @Override
    public Long insert(Estado estado) throws PersistenciaException {
        if (estado == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long estadoId = null;

        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "INSERT INTO uf (sigla, nome)"
                    + "    VALUES (?, ?) RETURNING cod_uf";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, estado.getSigla());
            pstmt.setString(2, estado.getNome());
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                estadoId = rs.getLong("cod_uf");
                estado.setId(estadoId);
            }
            
            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FarmaciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

        return estadoId;
    }

    @Override
    public boolean update(Estado estado) throws PersistenciaException {
        try {

            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "UPDATE uf "
                    + " SET sigla = ?, "
                    + "     nome = ? "
                    + " WHERE cod_uf = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, estado.getSigla());
            pstmt.setString(2, estado.getNome());
            pstmt.setLong(3, estado.getId());
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
    public boolean remove(Long estadoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM uf WHERE cod_uf = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, estadoId);
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
    public Estado getEstadoById(Long estadoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM uf WHERE cod_uf = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, estadoId);
            ResultSet rs = pstmt.executeQuery();

            Estado estado = null;
            if (rs.next()) {
                estado = new Estado();
                estado.setId(estadoId);
                estado.setSigla(rs.getString("sigla"));
                estado.setNome(rs.getString("nome"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return estado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public Estado getEstadoBySigla(String sigla) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM uf WHERE sigla = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, sigla);
            ResultSet rs = pstmt.executeQuery();

            Estado estado = null;
            if (rs.next()) {
                estado = new Estado();
                estado.setId(rs.getLong("cod_uf"));
                estado.setSigla(rs.getString("sigla"));
                estado.setNome(rs.getString("nome"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return estado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public List<Estado> listAll() throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM uf ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Estado> listAll = null;
            Estado estado = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    estado = new Estado();
                    estado.setId(rs.getLong("cod_uf"));
                    estado.setSigla(rs.getString("sigla"));
                    estado.setNome(rs.getString("nome"));
                    listAll.add(estado);
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
