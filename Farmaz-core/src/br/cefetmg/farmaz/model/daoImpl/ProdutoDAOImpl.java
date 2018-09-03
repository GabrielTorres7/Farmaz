/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.ProdutoDAO;
import br.cefetmg.farmaz.model.dominio.Produto;
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
public class ProdutoDAOImpl implements ProdutoDAO{
    
    private static ProdutoDAOImpl produtoDAO = null;
    
    private ProdutoDAOImpl(){}
    
    public static ProdutoDAOImpl getInstance() {
        if (produtoDAO == null) {
            produtoDAO = new ProdutoDAOImpl();
        }
        return produtoDAO;
    }
    
    @Override
    public Long insert(Produto produto) throws PersistenciaException {
        if (produto == null){
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }
        
        Long produtoId = null;
        
        try {
            Connection connection = ManterConexao.getInstance().getConnection();
            
            String sql = "INSERT INTO produto (nome, receita, descricao, laboratorio, cadastro_anvisa) " +
                         "    VALUES (?, ?, ?, ?, ?) RETURNING seq_produto";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, produto.getNome());
            pstmt.setBoolean(2, produto.isReceita());
            pstmt.setString(3, produto.getDescricao());
            if (produto.getLaboratorio()!= null){
                pstmt.setString(4, produto.getLaboratorio());
            }else{
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            if (produto.getCadastroAnvisa() != null){
                pstmt.setString(5, produto.getCadastroAnvisa());
            }else{
                pstmt.setNull(5, java.sql.Types.NULL);
            }
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                produtoId = rs.getLong("seq_produto");
                produto.setId(produtoId);
            }

            rs.close();
            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
        
        return produtoId;
    }

    @Override
    public boolean update(Produto produto) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();
            
            String sql = "UPDATE produto " +
                           " SET nome = ?, " +
                           "     receita = ? " +
                           "     descricao = ? " +
                           "     laboratorio = ? " +
                           "     cadastro_anvisa = ? " +
                         " WHERE seq_produto = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, produto.getNome());
            pstmt.setBoolean(2, produto.isReceita());
            pstmt.setString(3, produto.getDescricao());
            if (produto.getLaboratorio()!= null){
                pstmt.setString(4, produto.getLaboratorio());
            }else{
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            if (produto.getCadastroAnvisa() != null){
                pstmt.setString(5, produto.getCadastroAnvisa());
            }else{
                pstmt.setNull(5, java.sql.Types.NULL);
            }
            pstmt.setLong(6, produto.getId());
            pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e); 
        }
        
        return true;
    }

    @Override
    public boolean remove(Long produtoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM produto WHERE seq_produto = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, produtoId);
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
    public Produto getProdutoById(Long produtoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM produto WHERE seq_produto = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, produtoId);
            ResultSet rs = pstmt.executeQuery();

            Produto produto = null;
            if (rs.next()) {
                produto = new Produto();
                produto.setId(produtoId);
                produto.setNome(rs.getString("nome"));
                produto.setReceita(rs.getBoolean("receita"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setLaboratorio(rs.getString("laboratorio"));
                produto.setCadastroAnvisa(rs.getString("cadastro_anvisa"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return produto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Produto> listAll() throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM produto ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Produto> listAll = null;
            Produto produto = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    produto = new Produto();
                    produto.setId(rs.getLong("seq_produto"));
                    produto.setNome(rs.getString("nome"));
                    produto.setReceita(rs.getBoolean("receita"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setLaboratorio(rs.getString("laboratorio"));
                    produto.setCadastroAnvisa(rs.getString("cadastro_anvisa"));
                    listAll.add(produto);
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
    public Produto getProdutoByNome(String nomeProduto) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM produto WHERE nome = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nomeProduto);
            ResultSet rs = pstmt.executeQuery();

            Produto produto = null;
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getLong("seq_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setReceita(rs.getBoolean("receita"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setLaboratorio(rs.getString("laboratorio"));
                produto.setCadastroAnvisa(rs.getString("cadastro_anvisa"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return produto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
}
