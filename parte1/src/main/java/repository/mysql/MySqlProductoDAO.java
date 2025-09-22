package repository.mysql;

import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import DTO.ProductoDTO;
import entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlProductoDAO implements ProductoDAO {
    private Connection conn;
    private static MySqlProductoDAO instance;   // instancia única


    private MySqlProductoDAO(Connection connection) {
        this.conn = connection;
    }



    public static synchronized MySqlProductoDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new MySqlProductoDAO(connection);
        }
        return instance;
    }

    public void insertProducto(Producto producto) {
        String query = "INSERT INTO producto(idProducto, nombre, valor) VALUES(?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
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

    public ProductoDTO getTopProductDTO() {
        String query = "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                "FROM producto p " +
                "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1";

        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductoDTO productoDTO = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                Integer idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                Integer recaudacion = rs.getInt("recaudacion");

                productoDTO = new ProductoDTO(idProducto, nombre, recaudacion);
            }

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
        return productoDTO;
    }
}
