package dao;

import controller.ConnectionFactory;
import modelo.Senha;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SenhaDao {

    private final Connection con;

    public SenhaDao() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

        public void adicionarSenha(Senha senha) {
            String sql = "insert into senhas (chave_secreta) values (?)";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, senha.getChaveSecreta());
                stmt.execute();
                stmt.close();
                con.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public Senha getTodasSenhas() {
            String sql = "Select * from senhas";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                Senha senha = new Senha();
                while (rs.next()) {
                    senha.setId(rs.getInt("id"));
                    senha.setChaveSecreta(rs.getString("chave_secreta"));
                }
                stmt.close();
                con.close();
                return senha;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    public Senha obterSenhaPorId(Integer id) {
        String sql = "SELECT * FROM senhas WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Senha senha = new Senha();
            while (rs.next()) {
                senha.setId(rs.getInt("id"));
                senha.setChaveSecreta(rs.getString("chave_secreta"));

            }
            stmt.close();
            rs.close();
            con.close();
            return senha;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Senha> obterTodosSenhas() {
        String sql = "SELECT * FROM senhas";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Senha> senhas = new ArrayList<>();
            while (rs.next()) {
                Senha senha = new Senha();
                senha.setId(rs.getInt("id"));
                senha.setChaveSecreta(rs.getString("chave_secreta"));
                senhas.add(senha);
            }
            rs.close();
            stmt.close();
            con.close();
            return senhas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void atualizarSenhas(Senha senha, Integer id) {
        String sql = "update senhas set senha = ?  where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, senha.getChaveSecreta());
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deletarSenha(int id) throws SQLException {
        String sql = "DELETE FROM senhas WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    }




