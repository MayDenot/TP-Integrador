package DAO;

import DTO.ClienteDTO;
import entities.Cliente;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public List<ClienteDTO> getClientsBilled() {
    List<ClienteDTO> clientes = new ArrayList<>();

    String query = "SELECT SUM(fp.cantidad * p.valor) AS facturacion, " +
            "c.idCliente, c.nombre " +
            "FROM cliente c " +
            "JOIN factura f ON c.idCliente = f.idCliente " +
            "JOIN factura_producto fp ON f.idFactura = fp.idFactura " +
            "JOIN producto p ON p.idProducto = fp.idProducto " +
            "GROUP BY c.idCliente, c.nombre " +
            "ORDER BY facturacion DESC";

    PreparedStatement ps = null;
    ClienteDTO cliente = null;
    ResultSet rs = null;

    try {
      ps = conn.prepareStatement(query);
      rs = ps.executeQuery();

      while (rs.next()) {
        Integer idCliente = rs.getInt("idCliente");
        String nombre = rs.getString("nombre");
        Integer facturacion = rs.getInt("facturacion");
        cliente = new ClienteDTO(idCliente, nombre, facturacion);
        clientes.add(cliente);
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
    return clientes;
  }
}
