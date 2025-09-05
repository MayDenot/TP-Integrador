package factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class MySQLDAOFactory extends AbstractFactory {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URI = "jdbc:mysql://localhost:3306/Entregable1";
    private static String user = "root";
    private static String password = "";
    private static Connection conn;
    private static MySQLDAOFactory instance = null;

    public static MySQLDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }

        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(URI, user, password);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(createConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAO(createConnection());
    }

    @Override
    public Factura_ProductoDAO getFPDAO() {
        return new Factura_ProductoDAO(createConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoDAO(createConnection());
    }
}
