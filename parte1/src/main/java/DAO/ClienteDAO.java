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


public interface ClienteDAO {
  public void insertCliente(Cliente cliente);
  public List<ClienteDTO> getClientsBilled();


}
