package repository.mysql;

import DAO.FacturaDAO;
import entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlFacturaDAO implements FacturaDAO {
    private Connection conn;
    private static MySqlFacturaDAO instance;   // instancia única


    private MySqlFacturaDAO(Connection connection) {
        this.conn = connection;
    }



    public static synchronized MySqlFacturaDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new MySqlFacturaDAO(connection);
        }
        return instance;
    }

    public void insertFactura(Factura factura) {
        String query = "INSERT INTO factura (idFactura, idCliente) VALUES(?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
