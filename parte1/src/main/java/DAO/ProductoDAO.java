package DAO;

import DTO.ProductoDTO;
import entities.Producto;
import lombok.AllArgsConstructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface ProductoDAO {

  public void insertProducto(Producto producto);
  public ProductoDTO getTopProductDTO();


}
