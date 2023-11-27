package dao;

import controller.ConnectionFactory;
import modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    private Connection con;

    public PacienteDao() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

    public void adicionarPaciente(Paciente paciente) {
        String sql = "insert into pacientes (nome,cpf) values (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente obterPacientePorId(Integer id) {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Paciente paciente = new Paciente();
            while (rs.next()) {
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
            }
            stmt.close();
            rs.close();
            con.close();
            return paciente;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Paciente> obterTodosPacientes() {
        String sql = "SELECT * FROM pacientes";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Paciente> pacientes = new ArrayList<>();
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                pacientes.add(paciente);
            }
            rs.close();
            stmt.close();
            con.close();
            return pacientes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void atualizarPaciente(Paciente paciente, Integer id) {
        String sql = "update pacientes set nome = ? ,cpf = ? where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setInt(3, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deletarPaciente(int id) throws SQLException {
        String sql = "DELETE FROM Paciente WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
