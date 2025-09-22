package repository.mysql;

import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlFactura_ProductoDAO implements Factura_ProductoDAO {
    private Connection conn;
    private static MySqlFactura_ProductoDAO instance;   // instancia única


    private MySqlFactura_ProductoDAO(Connection connection) {
        this.conn = connection;
    }



    public static synchronized MySqlFactura_ProductoDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new MySqlFactura_ProductoDAO(connection);
        }
        return instance;
    }

    public void insertFactura_producto(Factura_Producto factura_producto) {
        String query = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura_producto.getIdFactura());
            ps.setInt(2, factura_producto.getIdProducto());
            ps.setInt(3, factura_producto.getCantidad());
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
