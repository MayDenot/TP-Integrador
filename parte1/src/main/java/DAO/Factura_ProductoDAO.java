package DAO;

import entities.Factura_Producto;
import entities.Producto;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class Factura_ProductoDAO {
  private Connection conn;

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

