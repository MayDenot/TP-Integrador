package DAO;

import entities.Cliente;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class ClienteDAO {
  private Connection conn;

  public void insertCliente(Cliente cliente) {
    String query = "INSERT INTO cliente(idCliente, nombre, email) VALUES(?, ?, ?)";
    PreparedStatement ps = null;

    try {
      ps = conn.prepareStatement(query);
      ps.setInt(1, cliente.getIdCliente());
      ps.setString(2, cliente.getNombre());
      ps.setString(3, cliente.getEmail());
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
