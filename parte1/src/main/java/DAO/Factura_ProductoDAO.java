package DAO;

import entities.Factura_Producto;
import entities.Producto;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface Factura_ProductoDAO {
  public void insertFactura_producto(Factura_Producto factura_producto);
}

