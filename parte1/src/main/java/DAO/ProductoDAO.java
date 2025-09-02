package DAO;

import entities.Producto;
import lombok.AllArgsConstructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class ProductoDAO {
  private Connection conn;

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
}
