package dao;

import controller.ConnectionFactory;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDao {

    private final Connection con;

    public UsuarioDao() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

    public void adicionarUsuario(Usuario usuario) {
        String sql = "insert into usuarios (login, senha) values (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getTodosUsuarios() {
        String sql = "Select * from usuarios";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            while (rs.next()) {
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
            stmt.close();
            con.close();
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario obterUsuarioPorLogin(String login) {
        String sql = "SELECT * FROM usuarios WHERE login = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            while (rs.next()) {
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
            stmt.close();
            rs.close();
            con.close();
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Usuario> obterTodosUsuarios() {
        String sql = "SELECT * FROM usuarios";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
            con.close();
            return usuarios;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void atualizarUsuarios(Usuario usuario, String login) {
        String sql = "update usuarios set senha = ?  where login = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getSenha());
            stmt.setString(2, login);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deletarUsuario(String login) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE login = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.executeUpdate();
        }
    }
}
