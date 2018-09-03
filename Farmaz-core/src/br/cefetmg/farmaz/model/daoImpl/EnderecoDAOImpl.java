/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.EnderecoDAO;
import br.cefetmg.farmaz.model.dominio.Endereco;
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
public class EnderecoDAOImpl implements EnderecoDAO{
    
    private static EnderecoDAOImpl enderecoDAO = null;
    
    private EnderecoDAOImpl(){}
    
    public static EnderecoDAOImpl getInstance() {
        if (enderecoDAO == null) {
            enderecoDAO = new EnderecoDAOImpl();
        }
        return enderecoDAO;
    }
    
    @Override
    public Long insert(Endereco endereco) throws PersistenciaException {
        if (endereco == null){
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }
        
        Long enderecoId = null;
        
        try {
            Connection connection = ManterConexao.getInstance().getConnection();
            
            String sql = "INSERT INTO endereco (seq_cliente, cod_cidade, "
                    + "cod_uf, cep, bairro, rua, numero, complemento) " +
                         "    VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING seq_endereco";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, endereco.getClienteId());
            pstmt.setLong(2, endereco.getCodCidade());
            pstmt.setLong(3, endereco.getCodUf());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getBairro());
            pstmt.setString(6, endereco.getRua());
            pstmt.setInt(7, endereco.getNumero());
            if (endereco.getComplemento() != null){
                pstmt.setString(8, endereco.getComplemento());
            }else{
                pstmt.setNull(8, java.sql.Types.NULL);
            }
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                enderecoId = rs.getLong("seq_endereco");
                endereco.setEnderecoId(enderecoId);
            }

            rs.close();
            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
        
        return enderecoId;
    }

    @Override
    public boolean update(Endereco endereco) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();
            
            String sql = "UPDATE endereco " +
                           " SET seq_cliente = ?, " +
                           "     cod_cidade = ? " +
                           "     cod_uf = ? " +
                           "     cep = ? " +
                           "     bairro = ? " +
                           "     rua = ? " +
                           "     numero = ? " +
                           "     complemento = ? " + 
                         " WHERE seq_endereco = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, endereco.getClienteId());
            pstmt.setLong(2, endereco.getCodCidade());
            pstmt.setLong(3, endereco.getCodUf());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getBairro());
            pstmt.setString(6, endereco.getRua());
            pstmt.setInt(7, endereco.getNumero());
            if (endereco.getComplemento() != null){
                pstmt.setString(8, endereco.getComplemento());
            }else{
                pstmt.setNull(8, java.sql.Types.NULL);
            }
            pstmt.setLong(9, endereco.getEnderecoId());
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
    public boolean remove(Long enderecoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM endereco WHERE seq_endereco = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, enderecoId);
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
    public Endereco getEnderecoById(Long enderecoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM endereco WHERE seq_endereco = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, enderecoId);
            ResultSet rs = pstmt.executeQuery();

            Endereco endereco = null;
            if (rs.next()) {
                endereco = new Endereco();
                endereco.setEnderecoId(enderecoId);
                endereco.setClienteId(rs.getLong("seq_cliente"));
                endereco.setCodCidade(rs.getLong("cod_cidade"));
                endereco.setCodUf(rs.getLong("cod_uf"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setComplemento(rs.getString("complemento"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return endereco;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Endereco> getEnderecosByClienteId(Long clienteId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM endereco WHERE seq_cliente = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, clienteId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Endereco> listEnderecos = null;
            Endereco endereco = null;

            if (rs.next()) {
                listEnderecos = new ArrayList<>();
                do {
                    endereco = new Endereco();
                    endereco.setEnderecoId(rs.getLong("seq_endereco"));
                    endereco.setClienteId(rs.getLong("seq_cliente"));
                    endereco.setCodCidade(rs.getLong("cod_cidade"));
                    endereco.setCodUf(rs.getLong("cod_uf"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setRua(rs.getString("rua"));
                    endereco.setNumero(rs.getInt("numero"));
                    endereco.setComplemento(rs.getString("complemento"));
                    listEnderecos.add(endereco);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listEnderecos;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
}
