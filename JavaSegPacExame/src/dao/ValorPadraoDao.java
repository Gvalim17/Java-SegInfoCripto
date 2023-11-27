package dao;

import controller.ConnectionFactory;
import modelo.ValorPadrao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValorPadraoDao {

    private final Connection con;

    public ValorPadraoDao() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

    public ValorPadrao obterValoresPadroesPorId(int id) {
        String sql = "SELECT * FROM valorespadroes WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            ValorPadrao valorPadrao = new ValorPadrao();
            while (rs.next()) {
                valorPadrao.setId(rs.getInt("id"));
                valorPadrao.setEstado(rs.getString("estado"));
                valorPadrao.setLimiteMax(rs.getInt("limite_max"));
                valorPadrao.setLimiteMin(rs.getInt("limite_min"));
                valorPadrao.setUnidade(rs.getString("unidade"));
            }
            stmt.close();
            rs.close();
            con.close();
            return valorPadrao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<ValorPadrao> obterTodosValoresPadroes() {
        String sql = "SELECT * FROM valorespadroes";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<ValorPadrao> vps = new ArrayList<>();
            while (rs.next()) {
                ValorPadrao valorPadrao = new ValorPadrao();
                valorPadrao.setId(rs.getInt("id"));
                valorPadrao.setEstado(rs.getString("estado"));
                valorPadrao.setLimiteMax(rs.getInt("limite_max"));
                valorPadrao.setLimiteMin(rs.getInt("limite_min"));
                valorPadrao.setUnidade(rs.getString("unidade"));
                vps.add(valorPadrao);
            }
            rs.close();
            stmt.close();
            con.close();
            return vps;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
