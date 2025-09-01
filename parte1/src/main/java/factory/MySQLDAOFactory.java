package factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;

import java.sql.Connection;

public class MySQLDAOFactory extends AbstractFactory {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String uri = "jdbc:mysql://localhost:3306/ejemplo";
    private static String user = "root";
    private static String password = "password";
    private static Connection conn;
    private static MySQLDAOFactory instance = null;
    public static MySQLDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }

    public MySQLDAOFactory() {

    }

    @Override
    public ClienteDAO getClienteDAO() {
        return null;
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return null;
    }

    @Override
    public Factura_ProductoDAO getFPDAO() {
        return null;
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return null;
    }
}
