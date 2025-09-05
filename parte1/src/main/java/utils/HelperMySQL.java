package utils;

import entities.Cliente;
import entities.Factura;
import entities.Factura_Producto;
import entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
  private Connection conn = null;

  public HelperMySQL() {
    String driver = "com.mysql.cj.jdbc.Driver";
    String uri = "jdbc:mysql://localhost:3306/Entregable1";

    try {
      Class.forName(driver).getDeclaredConstructor().newInstance();
    } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    try {
      conn = DriverManager.getConnection(uri, "root", "");
      conn.setAutoCommit(false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void closeConnection() {
    if (conn != null) {
      try {
        conn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void dropTables() throws SQLException {
    String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
    this.conn.prepareStatement(dropFactura_Producto).execute();
    this.conn.commit();

    String dropFactura = "DROP TABLE IF EXISTS factura";
    this.conn.prepareStatement(dropFactura).execute();
    this.conn.commit();

    String dropProducto = "DROP TABLE IF EXISTS producto";
    this.conn.prepareStatement(dropProducto).execute();
    this.conn.commit();

    String dropCliente = "DROP TALBE IF EXISTS cliente";
    this.conn.prepareStatement(dropCliente).execute();
    this.conn.commit();
  }

  public void createTables() throws SQLException {
    String tablaCliente = "CREATE TABLE IF NOT EXISTS cliente ( " +
                          "idCliente INT PRIMARY KEY, " +
                          "nombre VARCHAR(50) NOT NULL, " +
                          "email VARCHAR(50) NOT NULL " +
                          ");";
    this.conn.prepareStatement(tablaCliente).execute();
    this.conn.commit();

    String tablaFactura = "CREATE TABLE IF NOT EXIST factura ( " +
                          "idFacura INT PRIMARY KEY, " +
                          "idCliente INT NOT NULL, " +
                          "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente) " +
                          ");";
    this.conn.prepareStatement(tablaFactura).execute();
    this.conn.commit();

    String tablaProducto = "CREATE TABLE IF NOT EXIST producto ( " +
                           "idProducto INT PRIMARY KEY, " +
                           "nombre VARCHAR(50) NOT NULL, )" +
                           "valor FLOAT NOT NULL " +
                           ");";
    this.conn.prepareStatement(tablaProducto).execute();
    this.conn.commit();

    String tablaFactura_Producto = "CREATE TABLE IF NOT EXIST factura_producto( " +
                                   "idFactura INT NOT NULL, " +
                                   "idProducto INT NOT NULL, " +
                                   "cantidad INT NOT NULL" +
                                   "PRIMARY KEY(idFactura, idProducto), " +
                                   "FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                                   "FOREIGN KEY (idProducto) REFERENCES producto(idProducto) )" +
                                   ");";
    this.conn.prepareStatement(tablaFactura_Producto).execute();
    this.conn.commit();
  }

  public Iterable<CSVRecord> getData(String archivo) throws IOException {
    String path = "src/main/resources/" + archivo;
    Reader in = new FileReader(path);
    String[] header = {};
    CSVParser parser = CSVFormat.EXCEL.withHeader(header).parse(in);

    Iterable<CSVRecord> records = parser.getRecords();
    return records;
  }

  public void populateDB() throws Exception {
    try {
      for (CSVRecord row : getData("clientes.csv")) {
        if (row.size() >= 3) {
          String idCliente = row.get(0);

          if (!idCliente.isEmpty()) {
            try {
              int idClienteInt = Integer.parseInt(idCliente);
              Cliente cliente = new Cliente(idClienteInt, row.get(1), row.get(2));
              insertCliente(cliente, conn);
            } catch (NumberFormatException e) {
              System.err.println("Error de formato en datos de Cliente");
            }
          }
        }
      }

      for (CSVRecord row : getData("productos.csv")) {
        if (row.size() >= 3) {
          String idProducto = row.get(0);
          String valor = row.get(2);

          if (!idProducto.isEmpty() && !valor.isEmpty()) {
            try {
              int idProductoInt = Integer.parseInt(idProducto);
              float valorFloat = Float.parseFloat(valor);
              Producto producto = new Producto(idProductoInt, row.get(1), valorFloat);
              insertProducto(producto, conn);
            } catch (NumberFormatException e) {
              System.err.println("Error de formato en datos de Producto");
            }
          }
        }
      }

      for (CSVRecord row : getData("facturas.csv")) {
        if (row.size() >= 2) {
          String idFactura = row.get(0);
          String idCliente = row.get(1);

          if (!idFactura.isEmpty() && !idCliente.isEmpty()) {
            try {
              int idF = Integer.parseInt(idFactura);
              int idC = Integer.parseInt(idCliente);
              Factura factura = new Factura(idF, idC);
              insertFactura(factura, conn);
            } catch (NumberFormatException e) {
              System.err.println("Error de formato en datos de Factura" );
            }
          }
        }
      }

      for (CSVRecord row : getData("facturas-productos.csv")) {
        String idFactura = row.get(0);
        String idProducto = row.get(1);
        String cantidad = row.get(2);

        if (!idFactura.isEmpty() && !idProducto.isEmpty() && !cantidad.isEmpty()) {
          try {
            int idF = Integer.parseInt(idFactura);
            int idP = Integer.parseInt(idProducto);
            int cant = Integer.parseInt(cantidad);
            Factura_Producto factura_producto = new Factura_Producto(idF, idP, cant);
            insertFacturaProducto(factura_producto, conn);
          } catch (NumberFormatException e) {
            System.err.println("Error de formato en datos de Factura-Producto");
          }
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private int insertFacturaProducto(Factura_Producto facturaProducto, Connection conn) throws Exception {
    String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(insert);
    try {
      ps = conn.prepareStatement(insert);
      ps.setInt(1, facturaProducto.getIdFactura());
      ps.setInt(2, facturaProducto.getIdProducto());
      ps.setInt(3, facturaProducto.getCantidad());
      if (ps.executeUpdate() == 0) {
        throw new Exception("Error al insertar factura producto");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePsAndCommit(conn, ps);
    }
    return 0;
  }

  private int insertProducto(Producto producto, Connection conn) throws Exception {
    String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(insert);
    try {
      ps = conn.prepareStatement(insert);
      ps.setInt(1, producto.getIdProducto());
      ps.setString(2, producto.getNombre());
      ps.setFloat(3, producto.getValor());
      if (ps.executeUpdate() == 0) {
        throw new Exception("Error al insertar el producto");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePsAndCommit(conn, ps);
    }
    return 0;
  }

  private int insertCliente(Cliente cliente, Connection conn) throws Exception {
    String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
    PreparedStatement ps = null;
    try {
      ps = conn.prepareStatement(insert);
      ps.setInt(1, cliente.getIdCliente());
      ps.setString(2, cliente.getNombre());
      ps.setString(3, cliente.getEmail());
      if (ps.executeUpdate() == 0) {
        throw new Exception("No se pudo insertar");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePsAndCommit(conn, ps);
    }
    return 0;
  }

  private int insertFactura(Factura factura, Connection conn) throws Exception {
    String insert = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
    PreparedStatement ps = null;
    try {
      ps = conn.prepareStatement(insert);
      ps.setInt(1, factura.getIdFactura());
      ps.setInt(2, factura.getIdCliente());
      if (ps.executeUpdate() == 0) {
        throw new Exception("No se pudo insertar");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePsAndCommit(conn, ps);
    }
    return 0;
  }

  private void closePsAndCommit(Connection conn, PreparedStatement ps) {
    if (conn != null) {
      try {
        ps.close();
        conn.commit();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
