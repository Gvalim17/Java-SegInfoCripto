package dao;

import controller.ConnectionFactory;
import modelo.Hemoglobina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HemoglobinaDao {

    private Connection con;

    public HemoglobinaDao() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

    public void adicionarHemoglobina(Hemoglobina hemoglobina) {
        String sql = "insert into hemoglobina (resultado) values (?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hemoglobina.getResultado());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Hemoglobina obterHemoglobinaPorId(Integer id) {
        String sql = "SELECT * FROM Hemoglobina WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Hemoglobina hemoglobina = new Hemoglobina();
            while (rs.next()) {
                hemoglobina.setId(rs.getInt("id"));
                hemoglobina.setResultado(rs.getString("resultado"));

            }
            stmt.close();
            rs.close();
            con.close();
            return hemoglobina;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Hemoglobina> obterTodasHemoglobinas() {
        String sql = "SELECT * FROM Hemoglobina";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Hemoglobina> hemoglobinas = new ArrayList<>();
            while (rs.next()) {
                Hemoglobina hemoglobina = new Hemoglobina();
                hemoglobina.setId(rs.getInt("id"));
                hemoglobina.setResultado(rs.getString("resultado"));
                hemoglobinas.add(hemoglobina);
            }
            rs.close();
            stmt.close();
            con.close();
            return hemoglobinas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void atualizarHemoglobina(Hemoglobina hemoglobina, Integer id) {
        String sql = "update hemoglobina set resultado = ?  where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hemoglobina.getResultado());
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deletarHemoglobina(int id) throws SQLException {
        String sql = "DELETE FROM Hemoglobina WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
