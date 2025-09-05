import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import DTO.ProductoDTO;
import factory.AbstractFactory;
import utils.HelperMySQL;

public class Main {
    public static void main(String[] args) throws Exception {
        HelperMySQL helperMySQL = new HelperMySQL();

        helperMySQL.dropTables();
        helperMySQL.createTables();
        helperMySQL.populateDB();
        helperMySQL.closeConnection();

        AbstractFactory factory = AbstractFactory.getAbstractFactory(1);
        ClienteDAO clienteDAO = factory.getClienteDAO();
        FacturaDAO facturaDAO = factory.getFacturaDAO();
        ProductoDAO productoDAO = factory.getProductoDAO();
        Factura_ProductoDAO factura_productoDAO = factory.getFPDAO();

        System.out.println("Producto que más recaudó: ");
        ProductoDTO productoDTO = productoDAO.getTopProductDTO();
        System.out.println(productoDTO);

    }
}