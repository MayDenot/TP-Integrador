package DAO;

import entities.Factura;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class FacturaDAO {
  private Connection conn;

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
