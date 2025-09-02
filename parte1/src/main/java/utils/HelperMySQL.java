package utils;

import entities.Factura;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelperMySQL {
  private Connection conn = null;

  public HelperMySQL() {
    String driver = "com.mysql.cj.jdbc.Driver";
    String uri = "jdbc:mysql://localhost:3306/ejemplo";

    try {
      Class.forName(driver).getDeclaredConstructor().newInstance();
    } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    try {
      conn = DriverManager.getConnection(uri, "root", "password");
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
    //
  }

  public void createTables() throws SQLException {
    //
  }

  public Iterable<CSVRecord> getData(String archivo) throws IOException {
    String path = "src/main/resources/" + archivo;
    Reader in = new FileReader(path);
    String[] header = {};
    CSVParser parser = CSVFormat.EXCEL.withHeader(header).parse(in);

    Iterable<CSVRecord> records = parser.getRecords();
    return records;
  }

  public void populateDB() throws SQLException {
    try {
      for (CSVRecord row : getData("factura.csv")) {
        if (row.size() >= 2) {
          String idFactura = row.get(0);
          String idCliente = row.get(1);

          if (!idFactura.isEmpty() && !idCliente.isEmpty()) {
            try {
              int idF = Integer.parseInt(idFactura);
              int idC = Integer.parseInt(idCliente);
              Factura factura = new Factura(idF, idC);
              insertFactura(idF, idC);
            } catch (NumberFormatException e) {
              System.err.println("Error de formato en datos de factura: " + e.getMessage());
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void insertFactura(int idFactura, int idC) throws SQLException {

  }
}
