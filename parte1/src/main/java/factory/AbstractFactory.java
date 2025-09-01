package factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import entities.Factura_Producto;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;

    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract Factura_ProductoDAO getFPDAO();
    public abstract ProductoDAO getProductoDAO();

    public static AbstractFactory getAbstractFactory(int whichFactory) {
        switch(whichFactory) {
            case MYSQL_JDBC: {
                return MySQLDAOFactory.getInstance();
            }
            default:
                return null;
        }
    }

}
