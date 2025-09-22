package factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import lombok.NoArgsConstructor;
import repository.mysql.MySqlClienteDAO;
import repository.mysql.MySqlFacturaDAO;
import repository.mysql.MySqlFactura_ProductoDAO;
import repository.mysql.MySqlProductoDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLDAOFactory extends AbstractFactory {
     private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URI = "jdbc:mysql://localhost:3308/Entregable1";
    private static  String user = "root";
    private static  String password = "";
    private static Connection conn;
    private static   volatile MySQLDAOFactory instance;

    private MySQLDAOFactory() {
        try {

            conn = DriverManager.getConnection(URI, user, password);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static MySQLDAOFactory getInstance() {
        if (instance == null) {
            synchronized (MySQLDAOFactory.class) {
                if (instance == null) {
                    instance = new MySQLDAOFactory();
                }
            }

        }
        return instance;
    }

    public static Connection createConnection() {
        // Si ya existe la conexión, la retornamos
        if (conn != null) {
            return conn;
        }

        try {
            // Cargar el driver de MySQL
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchMethodException | SecurityException |
                 ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            // Intentar la conexión con la base de datos
            conn = DriverManager.getConnection(URI, user, password);
            conn.setAutoCommit(false); // deshabilitamos autocommit si usamos transacciones
            System.out.println("Conectado correctamente a la base de datos");
        } catch (SQLException e) {
            // Mostrar error si falla la conexión
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
            e.printStackTrace();
            // Opcional: salir del programa si la conexión es crítica
            System.exit(1);
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
        return MySqlClienteDAO.getInstance(createConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return MySqlFacturaDAO.getInstance(createConnection());
    }

    @Override
    public Factura_ProductoDAO getFPDAO() {
        return  MySqlFactura_ProductoDAO.getInstance(createConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return MySqlProductoDAO.getInstance(createConnection());
    }
}
