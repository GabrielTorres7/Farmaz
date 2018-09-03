/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.DisponibilidadeDAO;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
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
 * @author PEDRO HENRIQUE FODÃO
 */

public class DisponibilidadeDAOImpl implements DisponibilidadeDAO{
    
    private static DisponibilidadeDAOImpl disponibilidadeDAO = null;
    
    private DisponibilidadeDAOImpl(){}
    
    public static DisponibilidadeDAOImpl getInstance() {
        if (disponibilidadeDAO == null) {
            disponibilidadeDAO = new DisponibilidadeDAOImpl();
        }
        return disponibilidadeDAO;
    }
    
    
    @Override
    public Long insert(Disponibilidade disponibilidade) throws PersistenciaException {
        if (disponibilidade == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }
        
        Long DisponibilidadeId = null;
        
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "INSERT INTO Disponibilidade (seq_produto, cadastro_prefeitura, estoque, preco, avaliacao) "
                    + "    VALUES (?, ?, ?, ?, ?) RETURNING seq_disponibilidade";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, disponibilidade.getProdutoSeq());
            pstmt.setString(2, disponibilidade.getFarmaciaCadastro());
            pstmt.setDouble(3, disponibilidade.getEstoque());
            pstmt.setDouble(4, disponibilidade.getPreco());
            pstmt.setInt(5, disponibilidade.getAvaliacao());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                DisponibilidadeId = rs.getLong("seq_disponibilidade");
                disponibilidade.setId(DisponibilidadeId);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DisponibilidadeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

        return DisponibilidadeId;              
    }

    @Override
    public boolean update(Disponibilidade disponibilidade) throws PersistenciaException {
        try {

            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "UPDATE disponibilidade "
                    + " SET seq_produto = ?, "
                    + "     cadastro_prefeitura = ? "
                    + "     estoque = ? "
                    + "     preco = ? "
                    + "     avaliacao = ? "
                    + " WHERE seq_disponibilidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, disponibilidade.getProdutoSeq());
            pstmt.setString(2, disponibilidade.getFarmaciaCadastro());
            pstmt.setDouble(3, disponibilidade.getEstoque());
            pstmt.setDouble(4, disponibilidade.getPreco());
            pstmt.setInt(5, disponibilidade.getAvaliacao());
            pstmt.setLong(6, disponibilidade.getId());
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
    public boolean remove(Long disponibilidadeId) throws PersistenciaException {
         try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM disponibilidade WHERE seq_disponibilidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, disponibilidadeId);
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
    public Disponibilidade getDisponibilidadeById(Long disponibilidadeId) throws PersistenciaException {
         try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM disponibilidade WHERE seq_disponibilidade = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, disponibilidadeId);
            ResultSet rs = pstmt.executeQuery();

            Disponibilidade disponibilidade = null;
            if (rs.next()) {
                disponibilidade = new Disponibilidade();
                disponibilidade.setId(disponibilidadeId);
                disponibilidade.setProdutoSeq(rs.getLong("seq_produto"));
                disponibilidade.setFarmaciaCadastro(rs.getString("cadastro_prefeitura"));
                disponibilidade.setEstoque(rs.getInt("estoque"));
                disponibilidade.setPreco(rs.getDouble("preco"));
                disponibilidade.setAvaliacao(rs.getInt("avaliacao"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return disponibilidade;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Disponibilidade> getDisponibilidadeByProdutoId(Long produtoId) throws PersistenciaException {
         try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM disponibilidade WHERE seq_produto = ?";            

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, produtoId);
            ResultSet rs = pstmt.executeQuery();            
            
            ArrayList<Disponibilidade> listAll = null;
            Disponibilidade disponibilidade = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    disponibilidade = new Disponibilidade();
                    disponibilidade.setId(rs.getLong("seq_disponibilidade"));
                    disponibilidade.setProdutoSeq(rs.getLong("seq_produto"));
                    disponibilidade.setFarmaciaCadastro(rs.getString("cadastro_prefeitura"));
                    disponibilidade.setEstoque(rs.getInt("estoque"));
                    disponibilidade.setPreco(rs.getDouble("preco"));
                    disponibilidade.setAvaliacao(rs.getInt("avaliacao"));
                    listAll.add(disponibilidade);
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

    @Override
    public List<Disponibilidade> getDisponibilidadeByFarmaciaId(String farmaciaId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM disponibilidade WHERE cadastro_prefeitura = ?";            

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, farmaciaId);
            ResultSet rs = pstmt.executeQuery();            
            
            ArrayList<Disponibilidade> listAll = null;
            Disponibilidade disponibilidade = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    disponibilidade = new Disponibilidade();
                    disponibilidade.setId(rs.getLong("seq_disponibilidade"));
                    disponibilidade.setProdutoSeq(rs.getLong("seq_produto"));
                    disponibilidade.setFarmaciaCadastro(rs.getString("cadastro_prefeitura"));
                    disponibilidade.setEstoque(rs.getInt("estoque"));
                    disponibilidade.setPreco(rs.getDouble("preco"));
                    disponibilidade.setAvaliacao(rs.getInt("avaliacao"));
                    listAll.add(disponibilidade);
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
  
